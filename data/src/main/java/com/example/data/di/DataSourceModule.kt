package com.example.data.di

import com.example.data.data.database.PixelsDao
import com.example.data.data.datasource.local_datasource.PhotoLocalDataSource
import com.example.data.data.datasource.local_datasource.PhotoLocalDataSourceImpl
import com.example.data.data.datasource.remote_datasource.PhotoRemoteDataSource
import com.example.data.data.datasource.remote_datasource.PhotoRemoteDataSourceImpl
import com.example.data.data.network.PixelsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun providePhotoLocalDataSource(
        pixelsDao: PixelsDao,
    ): PhotoLocalDataSource {
        return PhotoLocalDataSourceImpl(pixelsDao)
    }

    @Provides
    fun providePhotoRemoteDataSource(
        pixelsApi: PixelsApi,
    ): PhotoRemoteDataSource {
        return PhotoRemoteDataSourceImpl(pixelsApi)
    }

}