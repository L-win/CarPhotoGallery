package com.elvina.carphotogallery.ui.screens

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
import com.elvina.carphotogallery.data.CarPhotoRepository
import com.elvina.carphotogallery.model.Photo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PhotoScreenUiState {
    data class Success(val photo: Photo) : PhotoScreenUiState
    object Error : PhotoScreenUiState
    object Loading : PhotoScreenUiState
}

class PhotoScreenViewModel(
    private val repository: CarPhotoRepository
) : ViewModel() {

    var photoScreenUiState: PhotoScreenUiState by mutableStateOf(PhotoScreenUiState.Loading)
        private set

//    init {
//        getCarPhoto(id = "123")
//    }

    fun getCarPhoto(id: String) {
        viewModelScope.launch {
            photoScreenUiState = PhotoScreenUiState.Loading
            photoScreenUiState = try {
                //TODO: pass photo id
                PhotoScreenUiState.Success(repository.getPhoto(id = id))
            } catch (e: IOException) {
                PhotoScreenUiState.Error
            } catch (e: HttpException) {
                PhotoScreenUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CarPhotoGalleryApplication)
                val carPhotoRepository = application.container.carPhotoRepository
                PhotoScreenViewModel(repository = carPhotoRepository)
            }
        }
    }
}