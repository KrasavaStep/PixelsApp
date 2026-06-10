package com.example.domain.usecase.photo

import androidx.paging.PagingData
import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCuratedPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    operator fun invoke(query: String): Flow<PagingData<PhotoResource>> {
        return repository.getCuratedPhotos(query)
    }
}