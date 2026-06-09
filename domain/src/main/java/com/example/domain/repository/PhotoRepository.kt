package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PhotoResource
import com.example.domain.util.SourceVariants
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun getCuratedPhotos(query: String): Flow<PagingData<PhotoResource>>

    suspend fun getPhotoDetails(photoId: Int, source: SourceVariants): Result<PhotoResource>

    suspend fun saveToBookmarks(photoId: Int)

    fun downloadPhoto(url: String, fileName: String)

    suspend fun getLikedPhotos(): List<PhotoResource>

    suspend fun removeFromBookmarks(photoId: Int)

}