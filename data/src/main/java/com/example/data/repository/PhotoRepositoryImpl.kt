package com.example.data.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.data.database.entity.mapper.toPhotoResourceFlow
import com.example.data.data.datasource.local.PhotoLocalDataSource
import com.example.data.data.datasource.remote.PhotoRemoteDataSource
import com.example.data.data.network.monitor.NetworkMonitor
import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import com.example.domain.util.SourceVariants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource,
    private val networkMonitor: NetworkMonitor
) : PhotoRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCuratedPhotos(query: String): Flow<PagingData<PhotoResource>> {
        val pagingSourceFactory = { localDataSource.getPagedPhotos() }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = PhotoRemoteMediator(
                localDataSource,
                remoteDataSource,
                networkMonitor
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.toPhotoResourceFlow()
    }

    override suspend fun getPhotoDetails(
        photoId: Int,
        source: SourceVariants
    ): Result<PhotoResource> {
        val isLiked = localDataSource.checkPhotoIsLiked(photoId)
        val photo = when (source) {
            SourceVariants.LOCAL -> localDataSource.getPhotoById(photoId)
            SourceVariants.REMOTE -> remoteDataSource.fetchPhotoById(photoId)
        }
        return photo.map { it.copy(liked = isLiked) }
    }

    override suspend fun getLikedPhotos(): List<PhotoResource> {
        return localDataSource.getLikedPhotos()
    }


    override suspend fun saveToBookmarks(photoId: Int) {
        localDataSource.savePhotoToBookmarks(photoId)
    }

    override suspend fun removeFromBookmarks(photoId: Int) {
        localDataSource.removePhotoFromBookmarks(photoId)
    }


    override fun downloadPhoto(url: String, fileName: String) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(url.toUri())
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName)

        downloadManager.enqueue(request)
    }

    companion object {
        const val PAGE_SIZE = 30
        const val PREFETCH_DISTANCE = 2
    }
}