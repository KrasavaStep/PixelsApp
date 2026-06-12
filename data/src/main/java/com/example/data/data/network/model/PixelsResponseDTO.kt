package com.example.data.data.network.model

import com.google.gson.annotations.SerializedName

data class PixelsResponseDTO(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val photos: List<PhotoDTO>,
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("prev_page") val prevPage: String?,
    @SerializedName("total_results") val totalResults: Int? = null
)