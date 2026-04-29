package com.example.pixelsapp.screens.details_screen

import com.example.domain.model.PhotoResource

data class DetailsState (
    val photoData: PhotoResource? = null,
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isLiked: Boolean = false
)