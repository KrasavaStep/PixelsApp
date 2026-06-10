package com.example.data.di

import android.content.Context
import com.example.data.data.database.PixelsDao
import com.example.data.data.database.RemoteKeysDao
import com.example.data.data.datasource.local_datasource.MediatorKeyLocalDataSource
import com.example.data.data.datasource.local_datasource.PhotoLocalDataSource
import com.example.data.data.datasource.local_datasource.PhotoLocalDataSourceImpl
import com.example.data.data.datasource.remote_datasource.PhotoRemoteDataSource
import com.example.data.data.network.PixelsApi
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
        mediatorKeyLocalDataSource: MediatorKeyLocalDataSource
    ): PhotoRepository {
        return PhotoRepositoryImpl(
            context = context,
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            keyLocalDataSource = mediatorKeyLocalDataSource
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