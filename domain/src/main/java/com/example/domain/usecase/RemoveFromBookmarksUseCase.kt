package com.example.domain.usecase

import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

//TODO -> Везде указывать возвращаемый тип явно
class RemoveFromBookmarksUseCase @Inject constructor(private val repository: PhotoRepository) {
    suspend operator fun invoke(id: Int) = repository.removeFromBookmarks(id)
}