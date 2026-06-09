package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import com.example.domain.util.SourceVariant
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend operator fun invoke(id: Int, source: SourceVariant): Result<PhotoResource> {
        return repository.getPhotoDetails(id, source)
    }

}