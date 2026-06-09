package com.example.data.repository

import com.example.data.data.network.PixelsApi
import com.example.data.data.network.mappers.toCollection
import com.example.domain.model.FeaturedCollection
import com.example.domain.repository.CollectionRepository
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(
    private val pixelsApi: PixelsApi
) : CollectionRepository {

    override suspend fun getFeaturedCollections(): Result<List<FeaturedCollection>> {
        return runCatching {
            //TODO -> маппинг сделать
            pixelsApi.getCollections().body()?.toCollection()
        }
    }

    override suspend fun saveFeaturedCollection(collection: FeaturedCollection) {
        TODO("Not yet implemented")
    }
}