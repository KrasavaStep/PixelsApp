package com.example.data.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val REMOTE_KEYS_TABLE_NAME = "remote_keys"

@Entity(tableName = REMOTE_KEYS_TABLE_NAME)
data class RemoteKeysEntity(
    @PrimaryKey val photoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)