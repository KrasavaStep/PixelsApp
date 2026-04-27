package com.example.data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.database.entity.RemoteKeysEntity

@Database(
    entities = [
        PhotoEntity::class,
        RemoteKeysEntity::class
    ],
    version = 1
)
abstract class PixelsDatabase() : RoomDatabase() {
    abstract fun getDao(): PixelsDao

    abstract fun getRemoteKeyDao(): RemoteKeysDao
}