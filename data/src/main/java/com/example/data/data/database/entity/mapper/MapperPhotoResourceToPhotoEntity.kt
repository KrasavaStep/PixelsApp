package com.example.data.data.database.entity.mapper

import com.example.data.data.database.entity.PhotoEntity
import com.example.domain.model.PhotoResource

fun PhotoResource.toPhotoEntity(page: Int): PhotoEntity {
    return PhotoEntity(
        this.id,
        photographer = this.photographer,
        url = this.url,
        height = this.height,
        width = this.width,
        avgColor = this.avgColor,
        photographerUrl = this.photographerUrl,
        liked = this.liked,
        altName = this.altName,
        page = page
    )
}

fun List<PhotoResource>.toPhotoEntityList(page: Int): List<PhotoEntity> =
    this.map { it.toPhotoEntity(page) }