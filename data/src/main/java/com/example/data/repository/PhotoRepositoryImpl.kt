package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.data.database.PixelsDao
import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.database.entity.LikedPhotoEntity
import com.example.data.data.database.entity.mapper.toPhotoResource
import com.example.data.data.network.PixelsApi
import com.example.data.data.network.mappers.toPhotoResource
import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import com.example.domain.util.SourceVariant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


//TODO -> спросить про паттерн DataSource
class PhotoRepositoryImpl @Inject constructor(
    private val pixelsApi: PixelsApi,
    private val pixelsDao: PixelsDao,
    private val remoteKeyDao: RemoteKeysDao
) : PhotoRepository {

    //TODO -. пагер в UI
    @OptIn(ExperimentalPagingApi::class)
    override fun getCuratedPhotos(query: String): Flow<PagingData<PhotoResource>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            remoteMediator = RemoteMediator(pixelsApi, pixelsDao, remoteKeyDao, query),
            pagingSourceFactory = { pixelsDao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toPhotoResource()
            }
        }
    }

    //TODO -> поправить
    private fun getLocalPHotoDetails() {
        runCatching {
            pixelsDao.getPhotoDetails(id).toPhotoResource().copy(
                liked = pixelsDao.likeCheckForPhoto(id)
            )
        }
    }

    private fun getRemotePHotoDetails() {

        runCatching {
            pixelsApi.getPhotoById(id).body().toPhotoResource().copy(
                liked = pixelsDao.likeCheckForPhoto(id)
            )
        }
    }

    override suspend fun getPhotoDetails(id: Int, source: SourceVariant): Result<PhotoResource> {
        return when (source) {
            SourceVariant.LOCAL -> getLocalPHotoDetails()

        SourceVariant.REMOTE -> getRemotePHotoDetails()

    }
}

override suspend fun saveToBookmarks(photoId: Int) {
    pixelsDao.addToBookmarks(LikedPhotoEntity(photoId))
}


override suspend fun getLikedPhotos(): List<PhotoResource> {
    //TODO - маппер отдельно
    return pixelsDao.getLikedPhotos().map { entity -> entity.toPhotoResource() }
}


override suspend fun removeFromBookmarks(photoId: Int) {
    pixelsDao.removeFromBookmarks(photoId)
}

}