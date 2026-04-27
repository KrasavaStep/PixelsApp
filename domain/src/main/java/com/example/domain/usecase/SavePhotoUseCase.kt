package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository

class SavePhotoUseCase(private val repository: PhotoRepository) {

    suspend operator fun invoke(photo: PhotoResource) {
        repository.savePhotoData(photo)
    }

}