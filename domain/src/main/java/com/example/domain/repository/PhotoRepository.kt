package com.example.domain.repository

import com.example.domain.model.PhotoResource

interface PhotoRepository {

    suspend fun getCuratedPhotos(): List<PhotoResource>

    suspend fun getPhotoDetails(): PhotoResource

    suspend fun savePhotoData(photo: PhotoResource)

}