package com.example.domain.usecase

import com.example.domain.repository.PhotoRepository
import javax.inject.Inject

//Вынести пагинацию от сюда тоже
class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    //указывать реальный тип
    operator fun invoke(query: String) = repository.getCuratedPhotos(query)


}