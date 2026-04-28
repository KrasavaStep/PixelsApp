package com.example.data.data.network.mappers

import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.network.models.CollectionDTO
import com.example.data.data.network.models.PhotoDTO
import com.example.domain.model.FeaturedCollection
import kotlin.Int

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

fun CollectionDTO.toCollection(): FeaturedCollection {
    return FeaturedCollection(
        id = this.id,
        title = this.title,
        description = this.description,
        private = this.private,
        mediaCount = this.mediaCount,
        photosCount = this.photosCount
    )
}