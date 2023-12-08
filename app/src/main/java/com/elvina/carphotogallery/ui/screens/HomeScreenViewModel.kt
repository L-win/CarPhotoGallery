package com.elvina.carphotogallery.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.elvina.carphotogallery.CarPhotoGalleryApplication
import com.elvina.carphotogallery.data.CarCollectionRepository
import com.elvina.carphotogallery.model.Photo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface HomeScreenUiState {
    data class Success(val photos: List<Photo>) : HomeScreenUiState
    object Error : HomeScreenUiState
    object Loading : HomeScreenUiState
}

class HomeScreenViewModel(
    private val repository: CarCollectionRepository
) : ViewModel() {
    var homeSreenUiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState.Loading)
        private set

    init {
        getCarPhotos()
    }

    fun getCarPhotos() {
        viewModelScope.launch {
            homeSreenUiState = HomeScreenUiState.Loading
            homeSreenUiState = try {
                HomeScreenUiState.Success(repository.getCollectionPhotos())
            } catch (e: IOException) {
                Log.v("MyDebug", "Ioexception", e)
                HomeScreenUiState.Error
            } catch (e: HttpException) {
                Log.v("MyDebug", "HttpException", e)
                HomeScreenUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CarPhotoGalleryApplication)
                val carCollectionRepository = application.container.carCollectionRepository
                HomeScreenViewModel(repository = carCollectionRepository)
            }
        }
    }
}