package com.example.data.data.network.models

import com.google.gson.annotations.SerializedName

data class PixelsResponseDTO(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val photos: List<PhotoDTO>,
    @SerializedName("next_page") val nextPage: String
)