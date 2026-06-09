package com.example.data.data.network

import com.example.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class PixelsInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header(AUTHORIZATION_HEADER, BuildConfig.PIXELS_API_KEY)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

}