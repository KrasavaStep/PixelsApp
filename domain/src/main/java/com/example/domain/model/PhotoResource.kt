package com.example.domain.model

data class PhotoResource(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val avgColor: String,
    val liked: Boolean,
    val altName: String
)