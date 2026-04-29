package com.example.pixelsapp.screens.details_screen

sealed class DetailsIntent {
    class LoadPhoto: DetailsIntent()

    class ToggleLike(val isLiked: Boolean): DetailsIntent()

    class DownloadPhoto(val url: String, val photographer: String): DetailsIntent()
}