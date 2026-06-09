package com.example.data.data.database.entity.mapper

import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.database.entity.PhotosWithLikedStatus
import com.example.domain.model.PhotoResource
import java.net.URI

//TODO Повыносить мапперы
fun PhotosWithLikedStatus.toPhotoResource(): PhotoResource {
    return PhotoResource(
        this.cachedPhotos.id,
        photographer = this.cachedPhotos.photographer,
        url = this.cachedPhotos.url,
        height = getPhotoHeight(this.cachedPhotos.url),
        width = getPhotoWidth(this.cachedPhotos.url),
        avgColor = this.cachedPhotos.avgColor,
        photographerUrl = this.cachedPhotos.photographerUrl,
        liked = this.isLiked,
        altName = this.cachedPhotos.altName
    )
}

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

//TODO вынести в экстеншн либо в др место
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