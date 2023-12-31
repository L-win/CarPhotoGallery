package com.elvina.carphotogallery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elvina.carphotogallery.model.Photo

@Composable
fun PhotoScreen(
//    photoScreenUiState: PhotoScreenUiState,
//    retryAction: () -> Unit,
    photoScreenViewModel: PhotoScreenViewModel,
    modifier: Modifier = Modifier,
    navController: NavController,
    id: String
) {
    photoScreenViewModel.getCarPhoto(id)
    val photoScreenUiState: PhotoScreenUiState = photoScreenViewModel.photoScreenUiState
    Log.d("MyT", photoScreenUiState.photo)
    when (photoScreenUiState) {
        is PhotoScreenUiState.Loading -> Loading()
        is PhotoScreenUiState.Success -> PhotoLargeScreen(
            photoScreenUiState.photo, modifier = modifier.fillMaxSize()
        )

        is PhotoScreenUiState.Error -> Error(id)
        //TODO: temporary
        else -> null
    }


}


@Composable
fun PhotoLargeScreen(photo: Photo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        CarPhotoCardLarge(
            photo = photo, modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
    }
}


@Composable
fun CarPhotoCardLarge(photo: Photo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.urls.small)
                .crossfade(true)
                .build(),
            contentDescription = photo.slug,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Error(id: String) {
    Column() {
        Text("Error")
        Text(id)
    }

}

@Composable
fun Loading() {
    Column() {
        Text("Loading")
    }
}