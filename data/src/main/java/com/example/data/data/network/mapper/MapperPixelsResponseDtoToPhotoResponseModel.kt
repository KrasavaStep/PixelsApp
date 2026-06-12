package com.example.data.data.network.mapper

import com.example.data.data.network.model.PixelsResponseDTO
import com.example.domain.model.PhotoResponseModel

fun PixelsResponseDTO.toPhotoResponseModel(): PhotoResponseModel {
    return PhotoResponseModel(
        page = this.page,
        perPage = this.perPage,
        photos = this.photos.toPhotoResourceList(),
        nextPage = this.nextPage ?: "",
        prevPage = this.prevPage ?: "",
        totalResults = this.totalResults
    )
}