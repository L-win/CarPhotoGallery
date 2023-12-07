package com.elvina.carphotogallery.network

import com.elvina.carphotogallery.model.Photo
import retrofit2.http.GET

interface UnsplashApiService {
//    @Headers("clientId: " + "")
    @GET("collections/2102317/photos")
    suspend fun getCollectionPhotos(): List<Photo>
}