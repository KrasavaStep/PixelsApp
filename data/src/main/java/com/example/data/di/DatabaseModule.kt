package com.example.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.data.database.PixelsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        application: Application
    ): PixelsDatabase {
        return Room.databaseBuilder(application, PixelsDatabase::class.java, "pixel_db")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Singleton
    @Provides
    fun provideDAO(db: PixelsDatabase) = db.getDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDAO(db: PixelsDatabase) = db.getRemoteKeyDao()

}