package com.example.data.data.datasource.local_datasource

import androidx.paging.PagingSource
import com.example.data.data.database.entity.PhotoEntity
import com.example.domain.model.PhotoResource

interface PhotoLocalDataSource {

    suspend fun insertAllPhotos(photos: List<PhotoResource>)

    suspend fun getPhotoById(photoId: Int): Result<PhotoResource>

    fun getPagedPhotos(): PagingSource<Int, PhotoEntity>

    fun checkPhotoIsLiked(photoId: Int): Boolean

    suspend fun savePhotoToBookmarks(photoId: Int)

    suspend fun removePhotoFromBookmarks(photoId: Int)

    suspend fun getLikedPhotos(): List<PhotoResource>

    suspend fun cleatAllPhotos()

}