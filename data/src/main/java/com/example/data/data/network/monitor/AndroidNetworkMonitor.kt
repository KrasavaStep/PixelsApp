package com.example.data.data.network.monitor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class AndroidNetworkMonitor(
    context: Context,
) : NetworkMonitor{

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

//    override val isConnected: Flow<Boolean> = callbackFlow {
//        val callback = object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                channel.trySend(true)
//            }
//
//            override fun onLost(network: Network) {
//                channel.trySend(false)
//            }
//
//            override fun onCapabilitiesChanged(
//                network: Network,
//                networkCapabilities: NetworkCapabilities
//            ) {
//                val hasInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//                channel.trySend(hasInternet)
//            }
//        }
//        channel.trySend(isCurrentConnectionActive())
//
//        val request = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()
//
//        connectivityManager.registerNetworkCallback(request, callback)
//
//        awaitClose {
//            connectivityManager.unregisterNetworkCallback(callback)
//        }
//    }.distinctUntilChanged()

    override suspend fun isCurrentConnectionActive(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

}