package com.example.data.data.datasource.local_datasource

import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.database.entity.RemoteKeysEntity
import javax.inject.Inject

class MediatorKeyLocalDataSourceImpl @Inject constructor(
    private val remoteKeysDao: RemoteKeysDao
): MediatorKeyLocalDataSource {

    override suspend fun insertAllRemoteKeys(remoteKeyList: List<RemoteKeysEntity>) {
        remoteKeysDao.insertAll(remoteKeyList)
    }

    override suspend fun getRemoteKeyByPhotoId(id: Int): RemoteKeysEntity? {
        return remoteKeysDao.getRemoteKeyByPhotoId(id)
    }

    override suspend fun clearRemoteKeys() {
        remoteKeysDao.clearRemoteKeys()
    }
}