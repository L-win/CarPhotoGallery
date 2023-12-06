package com.elvina.carphotogallery.network

import com.elvina.carphotogallery.model.Photo
import retrofit2.http.GET

interface UnsplashApiService {
    @GET("collections")
    suspend fun getCollectionPhotos(): List<Photo>
}