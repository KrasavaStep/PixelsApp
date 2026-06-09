package com.example.data.data.network.model

import com.google.gson.annotations.SerializedName

data class CollectionResponseDTO(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val collections: List<CollectionDTO>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("next_page") val nextPage: String
)
