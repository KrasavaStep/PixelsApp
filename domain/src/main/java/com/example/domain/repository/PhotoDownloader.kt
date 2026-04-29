package com.example.domain.repository

interface PhotoDownloader {
    fun downloadPhoto(url: String, fileName: String)
}