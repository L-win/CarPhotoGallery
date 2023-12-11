package com.elvina.carphotogallery.network

import com.elvina.carphotogallery.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("collections/2102317/photos")
    suspend fun getCollectionPhotos(@Query("client_id") clientId: String = "3Y0ZmeqmhJunQKu8n9maF58oSU9ANYZd-jTtvvWRg0k"): List<Photo>
}