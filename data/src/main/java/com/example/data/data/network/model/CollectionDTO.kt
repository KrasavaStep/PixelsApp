package com.example.data.data.network.model

import com.google.gson.annotations.SerializedName

data class CollectionDTO(
    val id: String,
    val title: String,
    val description: String?,
    val private: Boolean,
    @SerializedName("media_count") val mediaCount: Int,
    @SerializedName("photos_count") val photosCount: Int,
    @SerializedName("videos_count") val videosCount: Int
)
