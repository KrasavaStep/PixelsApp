package com.example.domain.usecase

import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

class RemoveFromBookmarksUseCase @Inject constructor(private val repository: PhotoRepository) {
    suspend operator fun invoke(id: Int) = repository.removeFromBookmarks(id)
}