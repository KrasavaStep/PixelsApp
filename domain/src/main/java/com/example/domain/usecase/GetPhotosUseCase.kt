package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import java.util.concurrent.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    operator fun invoke() = repository.getCuratedPhotos()


}