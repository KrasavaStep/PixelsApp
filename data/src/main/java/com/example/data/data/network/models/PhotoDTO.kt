package com.example.data.data.network.models

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
    val src: SrcDTO,
    val liked: Boolean,
    @SerializedName("alt") val altName: String
)

data class SrcDTO(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)