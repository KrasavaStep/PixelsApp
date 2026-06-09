package com.example.domain.repository

//Либо вынести отдельно в Photo rep репозиторий
//либо вынести в utils
interface PhotoDownloader {
    fun downloadPhoto(url: String, fileName: String)
}