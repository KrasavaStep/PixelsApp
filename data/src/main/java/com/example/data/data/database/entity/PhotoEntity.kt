package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val PHOTO_TABLE_NAME = "photos"

@Entity(tableName = PHOTO_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val avgColor: String,
    val liked: Boolean,
    val altName: String,
    val page: Int
)
