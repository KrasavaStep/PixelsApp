package com.example.data.data.database.entity.mapper

import com.example.data.data.database.entity.PhotosWithLikedStatus
import com.example.domain.model.PhotoResource

fun PhotosWithLikedStatus.toPhotoResource(): PhotoResource {
    return PhotoResource(
        this.cachedPhotos.id,
        photographer = this.cachedPhotos.photographer,
        url = this.cachedPhotos.url,
        height = this.cachedPhotos.url.getPhotoHeight(),
        width = this.cachedPhotos.url.getPhotoWidth(),
        avgColor = this.cachedPhotos.avgColor,
        photographerUrl = this.cachedPhotos.photographerUrl,
        liked = this.isLiked,
        altName = this.cachedPhotos.altName
    )
}

fun List<PhotosWithLikedStatus>.toPhotoResourceList(): List<PhotoResource> =
    this.map { it.toPhotoResource() }