package com.example.data.data.network

import com.example.data.data.network.models.PixelsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixelsApi {

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PixelsResponseDTO>

}