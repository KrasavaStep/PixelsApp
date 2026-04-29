package com.example.domain.usecase

import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class GetLikedPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend operator fun invoke() = repository.getLikedPhotos()

}