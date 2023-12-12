package com.elvina.carphotogallery.ui.screens

import androidx.lifecycle.ViewModel
import com.elvina.carphotogallery.data.CarCollectionRepository
import com.elvina.carphotogallery.model.Photo

sealed interface PhotoScreenUiState {
    data class Success(val photos: List<Photo>) : HomeScreenUiState
    object Error : HomeScreenUiState
    object Loading : HomeScreenUiState
}

class PhotoScreenViewModel(
    private val repository: CarCollectionRepository
) : ViewModel() {

}