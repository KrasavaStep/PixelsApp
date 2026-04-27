package com.example.data.repositories

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.data.database.PixelsDao
import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.database.entity.mapper.toPhotoResource
import com.example.data.data.network.PixelsApi
import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val pixelsApi: PixelsApi,
    private val pixelsDao: PixelsDao,
    private val remoteKeyDao: RemoteKeysDao
) : PhotoRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCuratedPhotos(): Flow<PagingData<PhotoResource>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = RemoteMediator(pixelsApi, pixelsDao, remoteKeyDao),
            pagingSourceFactory = { pixelsDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toPhotoResource()
            }
        }
    }

    override suspend fun getPhotoDetails(): PhotoResource {
        TODO("Not yet implemented")
    }

    override suspend fun savePhotoData(photo: PhotoResource) {
        TODO("Not yet implemented")
    }
}