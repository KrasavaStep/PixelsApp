package com.example.data.data.network

import com.example.data.data.network.models.CollectionResponseDTO
import com.example.data.data.network.models.PhotoDTO
import com.example.data.data.network.models.PixelsResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PixelsApi {

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PixelsResponseDTO>

    @GET("collections/featured?per_page=7")
    suspend fun getCollections() : Response<CollectionResponseDTO>

    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PixelsResponseDTO>

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: Int,
    ): Response<PhotoDTO>

}