package com.example.data.data.network.model

import com.google.gson.annotations.SerializedName

data class PhotoDTO(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    @SerializedName("photographer_url") val photographerUrl: String,
    @SerializedName("photographer_id") val photographerId: Long,
    @SerializedName("avg_color") val avgColor: String,
    val src: PhotoSourceDTO,
    val liked: Boolean,
    @SerializedName("alt") val altName: String
)