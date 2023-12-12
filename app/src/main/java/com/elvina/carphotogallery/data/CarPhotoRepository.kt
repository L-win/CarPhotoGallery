package com.elvina.carphotogallery.data

import com.elvina.carphotogallery.model.Photo
import com.elvina.carphotogallery.network.UnsplashApiService

interface CarPhotoRepository {
    suspend fun getPhoto(url: String): Photo
}

class CarPhotoRepositoryImpl(
    private val unsplashApiService: UnsplashApiService
) : CarPhotoRepository {
    override suspend fun getPhoto(url: String): Photo = unsplashApiService.getPhoto(url = url)
}