package com.example.data.data.database.entity.mapper

import com.example.data.data.database.entity.RemoteKeysEntity
import com.example.domain.model.PhotoResource

fun PhotoResource.toRemoteKeyEntity(prevKey: Int?, nextKey: Int?): RemoteKeysEntity {
    return RemoteKeysEntity(
        photoId = this.id,
        prevKey = prevKey,
        nextKey = nextKey
    )
}

fun List<PhotoResource>.toRemoteKeysEntityList(prevKey: Int?, nextKey: Int?) =
    this.map { it.toRemoteKeyEntity(prevKey, nextKey) }