package com.example.pixelsapp.screens.bookmarks_screen

import com.example.domain.model.PhotoResource

data class BookmarksState(
    val data: List<PhotoResource> = emptyList(),
    val isEmpty: Boolean = false,
    val isLoading: Boolean = true
)
