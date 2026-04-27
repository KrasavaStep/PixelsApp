package com.example.data.di

import com.example.data.data.database.PixelsDao
import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.network.PixelsApi
import com.example.data.repositories.PhotoRepositoryImpl
import com.example.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePhotoRepository(
        pixelsDao: PixelsDao,
        api: PixelsApi,
        remoteKeyDao: RemoteKeysDao
    ): PhotoRepository {
        return PhotoRepositoryImpl(
            pixelsApi = api,
            pixelsDao = pixelsDao,
            remoteKeyDao = remoteKeyDao
        )
    }
}