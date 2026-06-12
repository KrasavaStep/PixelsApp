package com.example.data.di

import android.content.Context
import com.example.data.data.datasource.local.PhotoLocalDataSource
import com.example.data.data.datasource.remote.PhotoRemoteDataSource
import com.example.data.data.network.PixelsApi
import com.example.data.data.network.monitor.NetworkMonitor
import com.example.data.repository.CollectionRepositoryImpl
import com.example.data.repository.PhotoRepositoryImpl
import com.example.domain.repository.CollectionRepository
import com.example.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePhotoRepository(
        @ApplicationContext context: Context,
        remoteDataSource: PhotoRemoteDataSource,
        localDataSource: PhotoLocalDataSource,
        networkMonitor: NetworkMonitor
    ): PhotoRepository {
        return PhotoRepositoryImpl(
            context = context,
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            networkMonitor = networkMonitor
        )
    }

    @Singleton
    @Provides
    fun provideCollectionRepository(
        api: PixelsApi,
    ): CollectionRepository {
        return CollectionRepositoryImpl(
            pixelsApi = api
        )
    }
}