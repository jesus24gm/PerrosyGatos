package com.example.perrosygatos.Modelos

import com.google.gson.annotations.SerializedName

data class DogBreedImageResponse(

    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String

)
