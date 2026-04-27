package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository

class GetPhotoDetailUseCase(private val repository: PhotoRepository) {

    suspend operator fun invoke(): PhotoResource {
        return repository.getPhotoDetails()
    }

}