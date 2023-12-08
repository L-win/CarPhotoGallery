package com.elvina.carphotogallery.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("id")
    val id: String,
    @SerialName("color")
    val color: String

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
//    val urls: Object,
//    val links	object,
//    val likes	int,
//    val liked_by_user	bulean,
//    val current_user_collections	array,
//    val sponsorship	null,
//    val topic_submissions	object

)