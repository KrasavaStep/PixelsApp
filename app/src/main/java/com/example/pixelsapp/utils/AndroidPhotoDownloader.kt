package com.example.pixelsapp.utils

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.example.domain.repository.PhotoDownloader
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidPhotoDownloader @Inject constructor(
    @ApplicationContext private val context: Context
) : PhotoDownloader {

    private val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    override fun downloadPhoto(url: String, fileName: String) {
        val request = DownloadManager.Request(url.toUri())
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName)

        downloadManager.enqueue(request)
    }
}