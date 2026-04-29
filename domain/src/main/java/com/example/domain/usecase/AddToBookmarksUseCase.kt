package com.example.domain.usecase

import com.example.domain.model.PhotoResource
import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class AddToBookmarksUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend operator fun invoke(photoId: Int) {
        repository.saveToBookmarks(photoId)
    }

}