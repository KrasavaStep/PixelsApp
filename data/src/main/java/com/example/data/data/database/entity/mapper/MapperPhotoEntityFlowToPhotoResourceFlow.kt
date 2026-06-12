package com.example.data.data.database.entity.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.data.database.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<PagingData<PhotoEntity>>.toPhotoResourceFlow() =
    this.map { pagingData -> pagingData.map { entity -> entity.toPhotoResource() } }