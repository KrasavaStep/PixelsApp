package com.example.data.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Src

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val photographerId: Int,
    val avgColor: String,
    @Embedded val src: Src,
    val liked: Boolean,
    val altName: String
)
