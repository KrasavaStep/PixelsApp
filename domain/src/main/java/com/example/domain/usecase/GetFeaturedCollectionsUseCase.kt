package com.example.domain.usecase

import com.example.domain.model.FeaturedCollection
import com.example.domain.repository.CollectionRepository
import javax.inject.Inject

class GetFeaturedCollectionsUseCase @Inject constructor(private val repository: CollectionRepository) {

    suspend operator fun invoke(): Result<List<FeaturedCollection>> {
        return repository.getFeaturedCollections()
    }

}