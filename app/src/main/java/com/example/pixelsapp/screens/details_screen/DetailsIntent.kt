package com.example.pixelsapp.screens.details_screen

sealed class DetailsIntent {
    class LoadPhoto: DetailsIntent()

    class AddToBookmarks(): DetailsIntent()

    class RemoveFromBookmarks(): DetailsIntent()
}