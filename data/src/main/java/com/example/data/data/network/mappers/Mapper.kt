package com.example.data.data.network.mappers

import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.network.models.PhotoDTO

fun PhotoDTO.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = this.id,
        url = this.src.large,
        photographer = this.photographer,
        photographerUrl = this.photographerUrl,
        width = this.width,
        height = this.height,
        avgColor = this.avgColor,
        liked = this.liked,
        altName = this.altName,
    )
}