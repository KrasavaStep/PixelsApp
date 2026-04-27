package com.example.domain.usecase

import com.example.domain.model.FeaturedCollection
import com.example.domain.repository.CollectionRepository

class GetFeaturedCollectionsUseCase(private val repository: CollectionRepository) {

    suspend operator fun invoke(): List<FeaturedCollection> {
        return repository.getFeaturedCollections()
    }

}