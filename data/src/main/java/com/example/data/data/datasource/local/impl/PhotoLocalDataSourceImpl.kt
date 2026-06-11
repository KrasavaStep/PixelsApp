package com.example.data.data.datasource.local.impl

import androidx.paging.PagingSource
import com.example.data.data.database.PixelsDao
import com.example.data.data.database.entity.LikedPhotoEntity
import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.database.entity.mapper.toPhotoEntityList
import com.example.data.data.database.entity.mapper.toPhotoResource
import com.example.data.data.database.entity.mapper.toPhotoResourceList
import com.example.data.data.datasource.local.PhotoLocalDataSource
import com.example.domain.model.PhotoResource
import com.example.domain.model.PhotoResponseModel
import javax.inject.Inject

class PhotoLocalDataSourceImpl @Inject constructor(
    private val pixelsDao: PixelsDao
): PhotoLocalDataSource {

    override suspend fun insertAllPhotos(photoResponse: PhotoResponseModel) {
        val photos = photoResponse.photos.toPhotoEntityList(photoResponse.page)
        pixelsDao.insertAll(photos)
    }

    override suspend fun getPhotoById(photoId: Int): Result<PhotoResource> {
        return runCatching {
            pixelsDao.getPhotoById(photoId).toPhotoResource()
        }
    }

    override fun getPagedPhotos(): PagingSource<Int, PhotoEntity> {
        return pixelsDao.getPagedPhotos()
    }

    override fun checkPhotoIsLiked(photoId: Int): Boolean {
        return pixelsDao.checkPhotoIsLiked(photoId)
    }

    override suspend fun savePhotoToBookmarks(photoId: Int) {
        pixelsDao.addToBookmarks(LikedPhotoEntity(photoId))
    }

    override suspend fun removePhotoFromBookmarks(photoId: Int) {
        pixelsDao.addToBookmarks(LikedPhotoEntity(photoId))
    }

    override suspend fun getLikedPhotos(): List<PhotoResource> {
        return pixelsDao.getLikedPhotos().toPhotoResourceList()
    }

    override suspend fun clearAllPhotos() {
        pixelsDao.clearAll()
    }
}