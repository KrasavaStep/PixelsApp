package com.example.data.data.database.entity

import androidx.room.Embedded

/**
* Special data class for INNER JOIN statement
* */
data class PhotosWithLikedStatus(
    @Embedded val cachedPhotos: PhotoEntity,
    val isLiked: Boolean
)
