package com.example.domain.repository

import com.example.domain.model.FeaturedCollection

interface CollectionRepository {

    suspend fun getFeaturedCollections(): List<FeaturedCollection>

    suspend fun saveFeaturedCollection(collection: FeaturedCollection)

}