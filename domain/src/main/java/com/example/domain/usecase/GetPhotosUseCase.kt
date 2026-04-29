package com.example.domain.usecase

import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    operator fun invoke(query: String) = repository.getCuratedPhotos(query)


}