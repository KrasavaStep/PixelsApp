package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val LIKED_PHOTO_TABLE_NAME = "liked_photos_table"

@Entity(tableName = LIKED_PHOTO_TABLE_NAME)
data class LikedPhotoEntity(
    @PrimaryKey val id: Int
)
