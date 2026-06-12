package com.example.data.data.network

import com.example.data.data.network.model.CollectionResponseDTO
import com.example.data.data.network.model.PhotoDTO
import com.example.data.data.network.model.PixelsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PixelsApi {

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): PixelsResponseDTO

    @GET("collections/featured?per_page=7")
    suspend fun getCollections() : CollectionResponseDTO

    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): PixelsResponseDTO

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: Int,
    ): PhotoDTO

}