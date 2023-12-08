package com.elvina.carphotogallery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("id")
    val id: String,
    @SerialName("color")
    val color: String,
    @SerialName("urls")
    val urls: Urls

//    val slug: String?,
//    val created_at: String?,
//    val updated_at: String?,
//    val promoted_at: String?,
//    val width: Int?,
//    val height: Int?,
//    val blur_hash: String?,
//    val description: null,
//    val alt_description: String,
//    val breadcrumbs: E,
//    val links	object,
//    val likes	int,
//    val liked_by_user	boolean,
//    val current_user_collections	array,
//    val sponsorship	null,
//    val topic_submissions	object

)

@Serializable
data class Urls(
    @SerialName("raw")
    val raw: String,
    @SerialName("full")
    val full: String,
    @SerialName("regular")
    val regular: String,
    @SerialName("small")
    val small: String,
    @SerialName("thumb")
    val thumb: String
)