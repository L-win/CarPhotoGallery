package com.elvina.carphotogallery.network

import com.elvina.carphotogallery.model.Photo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("collections/2102317/photos")
    suspend fun getCollectionPhotos(
        @Query("client_id") clientId: String = ""
    ): List<Photo>

    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: String = "FKJgBUDoVC0",
        @Query("client_id") client: String = "",
    ): Photo
}