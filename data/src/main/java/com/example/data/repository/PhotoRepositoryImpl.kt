package com.example.data.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.data.database.PixelsDao
import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.database.entity.LikedPhotoEntity
import com.example.data.data.database.entity.mapper.toPhotoResource
import com.example.data.data.network.PixelsApi
import com.example.data.data.network.mapper.toPhotoResourceModel
import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import com.example.domain.util.SourceVariants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pixelsApi: PixelsApi,
    private val pixelsDao: PixelsDao,
    private val remoteKeyDao: RemoteKeysDao
) : PhotoRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCuratedPhotos(query: String): Flow<PagingData<PhotoResource>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = RemoteMediator(pixelsApi, pixelsDao, remoteKeyDao, query),
            pagingSourceFactory = { pixelsDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toPhotoResource()
            }
        }
    }

    private suspend fun getLocalPhotoResource(photoId: Int): Result<PhotoResource> {
        return runCatching {
            pixelsDao.getPhotoDetails(photoId).toPhotoResource().copy(
                liked = pixelsDao.likeCheckForPhoto(photoId)
            )
        }
    }

    private suspend fun getRemotePhotoResource(photoId: Int): Result<PhotoResource> {
        return runCatching {
            pixelsApi.getPhotoById(photoId).toPhotoResourceModel().copy(
                liked = pixelsDao.likeCheckForPhoto(photoId)
            )
        }
    }

    override suspend fun getPhotoDetails(photoId: Int, source: SourceVariants): Result<PhotoResource> {
        return when (source) {
            SourceVariants.LOCAL -> getLocalPhotoResource(photoId)
            SourceVariants.REMOTE -> getRemotePhotoResource(photoId)
        }
    }

    override suspend fun saveToBookmarks(photoId: Int) {
        pixelsDao.addToBookmarks(LikedPhotoEntity(photoId))
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


    override suspend fun getLikedPhotos(): List<PhotoResource> {
        return pixelsDao.getLikedPhotos().map { entity -> entity.toPhotoResource() }
    }


    override suspend fun removeFromBookmarks(photoId: Int) {
        pixelsDao.removeFromBookmarks(photoId)
    }

}