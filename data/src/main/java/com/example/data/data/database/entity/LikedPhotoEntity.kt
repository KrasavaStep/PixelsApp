package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("liked_photos_table")
data class LikedPhotoEntity(
    @PrimaryKey val id: Int
)
