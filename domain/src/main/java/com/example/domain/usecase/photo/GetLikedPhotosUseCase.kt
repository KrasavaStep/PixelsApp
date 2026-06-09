package com.example.domain.usecase.photo

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class GetLikedPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend operator fun invoke(): Result<List<PhotoResource>> {
        return repository.getLikedPhotos()
    }

}