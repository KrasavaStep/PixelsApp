package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PhotoResource
import com.example.domain.util.SourceVariants
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun getCuratedPhotos(query: String): Flow<PagingData<PhotoResource>>

    suspend fun getPhotoDetails(id: Int, source: SourceVariants): Result<PhotoResource>

    suspend fun saveToBookmarks(photoId: Int)

    suspend fun getLikedPhotos(): List<PhotoResource>

    suspend fun removeFromBookmarks(photoId: Int)

}