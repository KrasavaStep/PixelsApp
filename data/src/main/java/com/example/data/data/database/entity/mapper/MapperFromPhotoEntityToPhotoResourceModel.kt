package com.example.data.data.database.entity.mapper

import com.example.data.data.database.entity.PhotoEntity
import com.example.domain.model.PhotoResource

fun PhotoEntity.toPhotoResource(): PhotoResource {
    return PhotoResource(
        this.id,
        photographer = this.photographer,
        url = this.url,
        height = this.url.getPhotoHeight(),
        width = this.url.getPhotoWidth(),
        avgColor = this.avgColor,
        photographerUrl = this.photographerUrl,
        liked = this.liked,
        altName = this.altName
    )
}

