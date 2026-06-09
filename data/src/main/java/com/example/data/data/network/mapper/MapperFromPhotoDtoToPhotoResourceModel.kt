package com.example.data.data.network.mapper

import com.example.data.data.network.model.PhotoDTO
import com.example.domain.model.PhotoResource

fun PhotoDTO?.toPhotoResourceModel(): PhotoResource {
    return PhotoResource(
        id = this?.id ?: 0,
        url = this?.src?.large ?: "",
        photographer = this?.photographer ?: "",
        photographerUrl = this?.photographerUrl ?: "",
        width = this?.width ?: 0,
        height = this?.height ?: 0,
        avgColor = this?.avgColor ?: "",
        liked = this?.liked ?: false,
        altName = this?.altName ?: "",
    )
}