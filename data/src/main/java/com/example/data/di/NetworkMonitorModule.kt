package com.example.data.di

import android.content.Context
import com.example.data.data.network.monitor.AndroidNetworkMonitor
import com.example.data.data.network.monitor.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NetworkMonitorModule {

    @Provides
    fun provideMonitorModule(
        @ApplicationContext context: Context
    ): NetworkMonitor {
        return AndroidNetworkMonitor(context)
    }

}