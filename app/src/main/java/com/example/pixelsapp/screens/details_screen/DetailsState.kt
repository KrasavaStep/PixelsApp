package com.example.pixelsapp.screens.details_screen

import androidx.paging.PagingData
import com.example.domain.model.FeaturedCollection
import com.example.domain.model.PhotoResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DetailsState (
    val photoData: PhotoResource? = null,
    val loading: Boolean = false,
    val errorMessage: String? = null,
)