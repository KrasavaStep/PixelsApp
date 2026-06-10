package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.database.entity.RemoteKeysEntity
import com.example.data.data.database.entity.mapper.toRemoteKeysEntityList
import com.example.data.data.datasource.local_datasource.MediatorKeyLocalDataSource
import com.example.data.data.datasource.local_datasource.PhotoLocalDataSource
import com.example.data.data.datasource.remote_datasource.PhotoRemoteDataSource


@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource,
    private val keyLocalDataSource: MediatorKeyLocalDataSource,
    private val query: String
) : RemoteMediator<Int, PhotoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        val result = if (query.isEmpty()) {
            remoteDataSource.fetchCuratedPhotos(page = page, perPage = state.config.pageSize)
        } else {
            remoteDataSource.searchForPhotos(
                query = query,
                page = page,
                perPage = state.config.pageSize
            )
        }

        return result.fold(
            onSuccess = { photoResponse ->
                val photos = photoResponse.photos
                val endOfPaginationReached = photos.isEmpty()

                if (loadType == LoadType.REFRESH) {
                    keyLocalDataSource.clearRemoteKeys()
                    localDataSource.cleatAllPhotos()
                }

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                val keys = photos.toRemoteKeysEntityList(prevKey, nextKey)
                keyLocalDataSource.insertAllRemoteKeys(keys)
                localDataSource.insertAllPhotos(photos)

                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            },
            onFailure = { exception ->
                MediatorResult.Error(exception)
            }
        )
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PhotoEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { entity -> keyLocalDataSource.getRemoteKeyByPhotoId(entity.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PhotoEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { entity -> keyLocalDataSource.getRemoteKeyByPhotoId(entity.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PhotoEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keyLocalDataSource.getRemoteKeyByPhotoId(id)
            }
        }
    }
}