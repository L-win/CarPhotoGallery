package com.elvina.carphotogallery.model

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    val src: String
)