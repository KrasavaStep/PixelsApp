package com.example.data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.data.database.entity.LikedPhotoEntity
import com.example.data.data.database.entity.PhotoEntity

@Database(
    entities = [
        PhotoEntity::class,
        LikedPhotoEntity::class
    ],
    version = 1
)
abstract class PixelsDatabase() : RoomDatabase() {
    abstract fun getPixelsDao(): PixelsDao
}