package com.example.data.data.database.entity.mapper

import java.net.URI

fun String.getPhotoHeight(): Int {
    val query = URI(this).query

    val params = query.split("&").associate {
        val (key, value) = it.split("=")
        key to value
    }

    return params["h"]?.toInt() ?: 0
}

fun String.getPhotoWidth(): Int {
    val query = URI(this).query

    val params = query.split("&").associate {
        val (key, value) = it.split("=")
        key to value
    }

    return params["w"]?.toInt() ?: 0
}