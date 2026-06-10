package com.example.data.data.datasource.local_datasource

import com.example.data.data.database.entity.RemoteKeysEntity

interface MediatorKeyLocalDataSource {

    suspend fun insertAllRemoteKeys(remoteKeyList: List<RemoteKeysEntity>)

    suspend fun getRemoteKeyByPhotoId(id: Int): RemoteKeysEntity?

    suspend fun clearRemoteKeys()

}