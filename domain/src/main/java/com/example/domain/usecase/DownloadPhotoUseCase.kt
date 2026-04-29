package com.example.domain.usecase

import com.example.domain.repository.PhotoDownloader
import javax.inject.Inject

class DownloadPhotoUseCase @Inject constructor(private val downloader: PhotoDownloader) {
    operator fun invoke(url: String, photographerName: String) {
        val fileName =
            "Pixels_${photographerName.replace(" ", "_")}_${System.currentTimeMillis()}.jpg"
        downloader.downloadPhoto(url, fileName)
    }
}