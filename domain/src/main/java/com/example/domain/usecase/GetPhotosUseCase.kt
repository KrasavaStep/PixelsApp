package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository

class GetPhotosUseCase(private val repository: PhotoRepository) {

    suspend operator fun invoke(): List<PhotoResource> {
        return repository.getCuratedPhotos()
    }

}