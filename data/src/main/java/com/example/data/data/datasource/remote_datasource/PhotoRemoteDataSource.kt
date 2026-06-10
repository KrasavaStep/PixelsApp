package com.example.data.data.datasource.remote_datasource

import com.example.domain.model.PhotoResource
import com.example.domain.model.PhotoResponseModel

interface PhotoRemoteDataSource {

    suspend fun fetchPhotoById(photoId: Int): Result<PhotoResource>

    suspend fun fetchCuratedPhotos(page: Int, perPage: Int): Result<PhotoResponseModel>

    suspend fun searchForPhotos(query: String, page: Int, perPage: Int): Result<PhotoResponseModel>

}