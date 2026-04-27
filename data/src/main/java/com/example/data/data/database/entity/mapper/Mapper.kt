package com.example.data.data.database.entity.mapper

import com.example.data.data.database.entity.PhotoEntity
import com.example.domain.model.PhotoResource
import java.net.URI

fun PhotoEntity.toPhotoResource(): PhotoResource {
    return PhotoResource(
        this.id,
        photographer = this.photographer,
        url = this.url,
        height = getPhotoHeight(this.url),
        width = getPhotoWidth(this.url),
        avgColor = this.avgColor,
        photographerUrl = this.photographerUrl,
        liked = this.liked,
        altName = this.altName
    )
}

private fun getPhotoHeight(url: String): Int {
    val query = URI(url).query

    val params = query.split("&").associate {
        val (key, value) = it.split("=")
        key to value
    }

    return params["h"]?.toInt() ?: 0
}

private fun getPhotoWidth(url: String): Int {
    val query = URI(url).query

    val params = query.split("&").associate {
        val (key, value) = it.split("=")
        key to value
    }

    return params["w"]?.toInt() ?: 0
}