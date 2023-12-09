package com.elvina.carphotogallery.data

import com.elvina.carphotogallery.model.Photo
import com.elvina.carphotogallery.network.UnsplashApiService

interface CarCollectionRepository {
    suspend fun getCollectionPhotos(): List<Photo>
}

class CarCollectionRepositoryImpl(
    private val unsplashApiService: UnsplashApiService
) : CarCollectionRepository {
    override suspend fun getCollectionPhotos(): List<Photo> = unsplashApiService.getCollectionPhotos()
}