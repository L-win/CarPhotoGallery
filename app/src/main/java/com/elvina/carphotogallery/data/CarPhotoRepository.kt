package com.elvina.carphotogallery.data

import com.elvina.carphotogallery.model.Photo
import com.elvina.carphotogallery.network.UnsplashApiService

interface CarPhotoRepository {
    suspend fun getPhoto(id: String): Photo
}

class CarPhotoRepositoryImpl(
    private val unsplashApiService: UnsplashApiService
) : CarPhotoRepository {
    override suspend fun getPhoto(id: String): Photo = unsplashApiService.getPhoto(id = id)
}