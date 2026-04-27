package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PhotoResource
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    fun getCuratedPhotos(): Flow<PagingData<PhotoResource>>

    suspend fun getPhotoDetails(): PhotoResource

    suspend fun savePhotoData(photo: PhotoResource)

}