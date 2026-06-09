package com.example.domain.usecase.photo

import com.example.domain.repository.PhotoRepository
import com.example.domain.util.Constants
import javax.inject.Inject

class DownloadPhotoUseCase @Inject constructor(private val downloader: PhotoRepository) {

    operator fun invoke(url: String, photographerName: String) {
        val nameWithoutSpaces = photographerName.replace(" ", "_")
        val fileName =
            "${Constants.PHOTO_FILE_PREFIX}${nameWithoutSpaces}_${System.currentTimeMillis()}${Constants.PHOTO_FILE_JPG_EXTENSION}"
        downloader.downloadPhoto(url, fileName)
    }
}