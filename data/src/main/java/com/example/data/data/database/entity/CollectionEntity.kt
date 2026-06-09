package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO все tableName вынести в конст (посмотреть как делается на формуах)
@Entity(tableName = "collection_table")
data class CollectionEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val private: Boolean,
    val mediaCount: Int,
    val photosCount: Int
)
