package com.elvina.carphotogallery.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elvina.carphotogallery.R
import com.elvina.carphotogallery.model.Photo

@Composable
fun HomeScreen(
    homeScreenUiState: HomeScreenUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    when (homeScreenUiState) {
        is HomeScreenUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is HomeScreenUiState.Success -> PhotoGridScreen(
            homeScreenUiState.photos,
            modifier = modifier.fillMaxSize(),
            navController = navController
        )

        is HomeScreenUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        // TODO WTF?
        else -> null
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(text = "Loading images...", textAlign = TextAlign.Center)
    CircularProgressIndicator(
        modifier = Modifier
            .padding(115.dp)
            .size(10.dp),
        color = MaterialTheme.colorScheme.secondary,
//        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )

}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_connection), contentDescription = "No internet connection"
        )
        Text(text = "Something went wrong!")
        Spacer(modifier = Modifier.size(30.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun PhotoGridScreen(photos: List<Photo>, modifier: Modifier = Modifier, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(
            items = photos,
            key = { photo -> photo.id }
        ) { photo ->
            CarPhotoCard(
                photo, modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(
                        1.5f
                    ),
                navController = navController

            )
        }
    }
}

@Composable
fun CarPhotoCard(photo: Photo, modifier: Modifier = Modifier, navController: NavController) {
    Card(
        modifier = modifier.clickable {
            navController.navigate("PhotoScreen" + "/{id}=${photo.id}")
        },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.urls.small)
                .crossfade(true)
                .build(),
            // TODO: Missing resources
//            error = painterResource(R.drawable.ic_broken_image),
//            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = photo.slug,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}