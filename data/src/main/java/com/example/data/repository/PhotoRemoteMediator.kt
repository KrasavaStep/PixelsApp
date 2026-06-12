package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.datasource.local.PhotoLocalDataSource
import com.example.data.data.datasource.remote.PhotoRemoteDataSource
import com.example.data.data.network.monitor.NetworkMonitor


/**
 * Remove Remote Key Page Storage because:
 *
 * When use additional methods for page keys from db
 * after the second api call with 2-nd page
 * getRemoteKeyForLastItem() return null element and throw exception
 * (NON-NULL object of type 'com.example.`data`.`data`.database.entity.RemoteKeysEntity')
 * then for some reason try catch (or runCatching) maybe catch that exception and always return
 * 2 page number and because of that there appear infinity cycle with 2-nd number of page
 * and Pager thinks that page 2 doesn't have the end
 * */

@ExperimentalPagingApi
class PhotoRemoteMediator(
    private val localDataSource: PhotoLocalDataSource,
    private val remoteDataSource: PhotoRemoteDataSource,
    private val networkMonitor: NetworkMonitor
) : RemoteMediator<Int, PhotoEntity>() {
    private var pageIndex = 1

    override suspend fun initialize(): InitializeAction {
        return if (networkMonitor.isCurrentConnectionActive()) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {

        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val photoResponse = remoteDataSource.fetchCuratedPhotos(
            page = pageIndex,
            perPage = state.config.pageSize
        )

        if (loadType == LoadType.REFRESH) {
            pageIndex = state.config.initialLoadSize / state.config.pageSize
            localDataSource.clearAllPhotos()
        } else {
            pageIndex++
        }

        return photoResponse.fold(
            onSuccess = { response ->
                localDataSource.insertAllPhotos(photoResponse = response)
                MediatorResult.Success(endOfPaginationReached = response.nextPage.isEmpty())
            },
            onFailure = { exception ->
                MediatorResult.Error(exception)
            }
        )


    }

    private fun getPageIndex(loadType: LoadType): Int? {
        return when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> null
            LoadType.APPEND -> pageIndex
        }
    }
}