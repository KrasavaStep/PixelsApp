package com.example.pixelsapp.screens.home_screen

import androidx.paging.PagingData
import com.example.domain.model.FeaturedCollection
import com.example.domain.model.PhotoResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState (
    val photosPagingData: Flow<PagingData<PhotoResource>> = emptyFlow(),
    val collections: List<FeaturedCollection> = emptyList(),
    val selectedCollection: String? = null,
    val isCollectionsLoading: Boolean = false,
    val errorMessage: String? = null,
    val isOffline: Boolean = false
)