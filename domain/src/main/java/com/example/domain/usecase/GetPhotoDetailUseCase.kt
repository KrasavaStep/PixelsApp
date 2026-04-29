package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import com.example.domain.util.SourceVariants
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend operator fun invoke(id: Int, source: SourceVariants): Result<PhotoResource> {
        return repository.getPhotoDetails(id, source)
    }

}