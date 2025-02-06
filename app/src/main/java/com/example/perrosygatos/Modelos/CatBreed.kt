package com.example.perrosygatos.Modelos

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String?,
    @SerializedName("life_span") val lifeSpan: String?,
    @SerializedName("temperament") val temperament: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("reference_image_id") val imageId: String?
)
