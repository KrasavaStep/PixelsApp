package com.example.data.repositories

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: PexelsApi,
    private val db: AppDatabase
): PhotoRepository {
    override suspend fun getCuratedPhotos(): List<PhotoResource> {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoDetails(): PhotoResource {
        TODO("Not yet implemented")
    }

    override suspend fun savePhotoData(photo: PhotoResource) {
        TODO("Not yet implemented")
    }
}