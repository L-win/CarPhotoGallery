package com.elvina.carphotogallery.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elvina.carphotogallery.model.Photo

@Composable
fun PhotoScreen(
    photoScreenUiState: PhotoScreenUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (photoScreenUiState) {
//        is PhotoScreenUiState.Loading ->
//        is PhotoScreenUiState.Success ->
//        is PhotoScreenUiState.Error ->
    }
}


@Composable
fun PhotoLargeScreen(photos: List<Photo>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(
            items = photos,
            key = { photo -> photo.id }
        ) { photo ->
            CarPhotoCardLarge(
                photo, modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
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