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
        //TODO -> строку в константы
        return Room.databaseBuilder(application, PixelsDatabase::class.java, "pixel_db")
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    //TODO -> нейминги должны быть явные + явно возвращаемый тип
    @Singleton
    @Provides
    fun provideDAO(db: PixelsDatabase) = db.getDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDAO(db: PixelsDatabase) = db.getRemoteKeyDao()

}