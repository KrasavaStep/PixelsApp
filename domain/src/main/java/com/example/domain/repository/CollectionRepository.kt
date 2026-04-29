package com.example.domain.repository

import com.example.domain.model.FeaturedCollection

interface CollectionRepository {

    suspend fun getFeaturedCollections(): Result<List<FeaturedCollection>>

    suspend fun saveFeaturedCollection(collection: FeaturedCollection)

}