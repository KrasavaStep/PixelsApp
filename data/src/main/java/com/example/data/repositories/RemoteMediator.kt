package com.example.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.data.database.entity.PhotoEntity

@OptIn(ExperimentalPagingApi::class)
class RemoteMediator(
    private val api: PexelsApi,
    private val db: AppDatabase
) : RemoteMediator<Int, PhotoEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PhotoEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            // Запрос в сеть
            val response = api.getPhotos(page = page, perPage = state.config.pageSize)

            // Сохранение в базу
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeys()
                    db.photoDao().clearAll()
                }
                val keys = response.photos.map { RemoteKeys(it.id, page + 1) }
                db.remoteKeysDao().insertAll(keys)
                db.photoDao().insertAll(response.photos.map { it.toEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = response.photos.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}