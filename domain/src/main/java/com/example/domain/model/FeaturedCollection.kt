package com.example.domain.model

data class FeaturedCollection(
    val id: String,
    val title: String,
    val description: String,
    val private: Boolean,
    val mediaCount: Int,
    val photosCount: Int
)
