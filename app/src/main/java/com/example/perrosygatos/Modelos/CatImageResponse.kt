package com.example.perrosygatos.Modelos

import com.google.gson.annotations.SerializedName

data class CatImageResponse(

    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String
)
