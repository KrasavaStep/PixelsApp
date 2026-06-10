package com.example.data.data.datasource.remote_datasource

import com.example.data.data.network.PixelsApi
import com.example.data.data.network.mapper.toPhotoResourceModel
import com.example.data.data.network.mapper.toPhotoResponseModel
import com.example.domain.model.PhotoResource
import com.example.domain.model.PhotoResponseModel

class PhotoRemoteDataSourceImpl(
    private val pixelsApi: PixelsApi
): PhotoRemoteDataSource {

    override suspend fun fetchPhotoById(photoId: Int): Result<PhotoResource> {
        return runCatching {
            pixelsApi.getPhotoById(photoId).toPhotoResourceModel()
        }
    }

    override suspend fun fetchCuratedPhotos(
        page: Int,
        perPage: Int
    ): Result<PhotoResponseModel> {
        return runCatching {
            pixelsApi.getCuratedPhotos(page, perPage).toPhotoResponseModel()
        }
    }

    override suspend fun searchForPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Result<PhotoResponseModel> {
        return runCatching {
            pixelsApi.searchPhotos(query, page, perPage).toPhotoResponseModel()
        }
    }
}