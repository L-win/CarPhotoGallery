package com.elvina.carphotogallery

import android.app.Application
import com.elvina.carphotogallery.di.AppContainer
import com.elvina.carphotogallery.di.DefaultAppContainer

class CarPhotoGalleryApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}