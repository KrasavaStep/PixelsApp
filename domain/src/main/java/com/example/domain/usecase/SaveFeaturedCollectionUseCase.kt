package com.example.domain.usecase

import com.example.domain.model.FeaturedCollection
import com.example.domain.repository.CollectionRepository

//TODO -> Почему не используется?
class SaveFeaturedCollectionUseCase(private val repository: CollectionRepository) {

    suspend operator fun invoke(collection: FeaturedCollection) {
        repository.saveFeaturedCollection(collection)
    }

}