package com.example.data.data.network.mapper

import com.example.data.data.network.model.CollectionDTO
import com.example.data.data.network.model.CollectionResponseDTO
import com.example.domain.model.FeaturedCollection

fun CollectionDTO.toFeaturedCollectionModel(): FeaturedCollection {
    return FeaturedCollection(
        id = this.id,
        title = this.title,
        description = this.description ?: "",
        private = this.private,
        mediaCount = this.mediaCount,
        photosCount = this.photosCount
    )
}

fun CollectionResponseDTO.toCollections(): List<FeaturedCollection> =
    this.collections.map { it.toFeaturedCollectionModel() }