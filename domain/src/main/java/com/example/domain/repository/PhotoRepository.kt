package com.example.domain.repository

//Зависит от андроид
import androidx.paging.PagingData
import com.example.domain.model.PhotoResource
import com.example.domain.util.SourceVariant
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    //На уровне UI pager с инжектом use case
    fun getCuratedPhotos(query: String): Flow<PagingData<PhotoResource>>

    suspend fun getPhotoDetails(id: Int, source: SourceVariant): Result<PhotoResource>

    suspend fun saveToBookmarks(photoId: Int)

    suspend fun getLikedPhotos(): List<PhotoResource>

    suspend fun removeFromBookmarks(photoId: Int)

}