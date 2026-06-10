package com.example.domain.model

data class PhotoResponseModel(
    val page: Int,
    val perPage: Int,
    val photos: List<PhotoResource>,
    val nextPage: String,
    val totalResults: Int? = null
)