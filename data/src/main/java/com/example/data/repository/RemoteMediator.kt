package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.data.database.PixelsDao
import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.database.entity.PhotosWithLikedStatus
import com.example.data.data.database.entity.RemoteKeysEntity
import com.example.data.data.network.PixelsApi
import com.example.data.data.network.mappers.toEntity
import com.example.data.data.network.models.PhotoDTO


//TODO -> вынести в UI вместе с пагером
@OptIn(ExperimentalPagingApi::class)
class RemoteMediator(
    private val api: PixelsApi,
    private val pixelsDao: PixelsDao,
    private val remoteKeyDao: RemoteKeysDao,
    private val query: String
) : RemoteMediator<Int, PhotosWithLikedStatus>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotosWithLikedStatus>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        try {
            val response = if (query.isEmpty()) {
                api.getCuratedPhotos(page = page, perPage = state.config.pageSize)
            } else {
                api.searchPhotos(query = query, page = page, perPage = state.config.pageSize)
            }

            val photos = response.body()?.photos ?: emptyList<PhotoDTO>()
            val endOfPaginationReached = photos.isEmpty()

            if (loadType == LoadType.REFRESH) {
                remoteKeyDao.clearRemoteKeys()
                pixelsDao.clearAll()
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1

            val keys = photos.map { photo ->
                RemoteKeysEntity(photoId = photo.id, prevKey = prevKey, nextKey = nextKey)
            }

            remoteKeyDao.insertAll(keys)
            pixelsDao.insertAll(photos.map { it.toEntity() })

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PhotosWithLikedStatus>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { photo ->
            remoteKeyDao.getRemoteKeyByPhotoId(photo.cachedPhotos.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PhotosWithLikedStatus>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.cachedPhotos?.id?.let { id ->
                remoteKeyDao.getRemoteKeyByPhotoId(id)
            }
        }
    }
}