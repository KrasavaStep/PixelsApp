package com.example.data.data.network.monitor

/**
 * I need this for now, for check remote mediator network logic
 * in the future i send this to the app module with remote mediator
 * */

interface NetworkMonitor {
    //val isConnected: Flow<Boolean>
    suspend fun isCurrentConnectionActive(): Boolean
}