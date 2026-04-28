package com.example.data.repositories

import com.example.data.data.database.CollectionDao
import com.example.data.data.network.PixelsApi
import com.example.data.data.network.mappers.toCollection
import com.example.domain.model.FeaturedCollection
import com.example.domain.repository.CollectionRepository
import java.lang.Exception
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val pixelsApi: PixelsApi
) : CollectionRepository {

    override suspend fun getFeaturedCollections(): Result<List<FeaturedCollection>> {
        return pixelsApi.getCollections().runCatching {
            this.body()?.collections?.map {
                it.toCollection()
            } ?: emptyList()
        }
    }

    override suspend fun saveFeaturedCollection(collection: FeaturedCollection) {
        TODO("Not yet implemented")
    }
}