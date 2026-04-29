package com.example.data.data.database.entity

import androidx.room.Embedded

data class PhotosWithLikedStatus(
    @Embedded val cachedPhotos: PhotoEntity,
    val isLiked: Boolean
)
