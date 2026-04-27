package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val photoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)