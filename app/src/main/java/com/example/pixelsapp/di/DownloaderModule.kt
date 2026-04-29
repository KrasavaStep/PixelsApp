package com.example.pixelsapp.di

import android.content.Context
import com.example.domain.repository.PhotoDownloader
import com.example.pixelsapp.utils.AndroidPhotoDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DownloaderModule {

    @Provides
    @Singleton
    fun providePhotoDownloader(
        @ApplicationContext context: Context
    ): PhotoDownloader = AndroidPhotoDownloader(context)
}