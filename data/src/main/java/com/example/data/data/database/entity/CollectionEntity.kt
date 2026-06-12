package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val COLLECTION_TABLE_NAME = "collection_table"

@Entity(tableName = COLLECTION_TABLE_NAME)
data class CollectionEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val private: Boolean,
    val mediaCount: Int,
    val photosCount: Int
)
