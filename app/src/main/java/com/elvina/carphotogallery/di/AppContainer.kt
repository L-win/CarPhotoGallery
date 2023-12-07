package com.elvina.carphotogallery.di

import com.elvina.carphotogallery.data.CarCollectionRepository
import com.elvina.carphotogallery.data.CarCollectionRepositoryImpl
import com.elvina.carphotogallery.network.UnsplashApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val carCollectionRepository: CarCollectionRepository
}

class DefaulAppContainer : AppContainer {
    private val baseUrl: String = "https://api.unsplash.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: UnsplashApiService by lazy {
        retrofit.create(UnsplashApiService::class.java)
    }
    override val carCollectionRepository: CarCollectionRepository by lazy {
        CarCollectionRepositoryImpl(retrofitService)
    }

}