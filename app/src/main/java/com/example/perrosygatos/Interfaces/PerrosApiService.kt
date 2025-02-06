package com.example.perrosygatos.Interfaces

import com.example.perrosygatos.Modelos.DogBreed
import com.example.perrosygatos.Modelos.DogBreedImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PerrosApiService {
    @GET("breeds")
    fun getAllBreeds(): Call<List<DogBreed>>

    @GET("images/{image_id}")
    fun getBreedImage(@Path("image_id") breed: String): Call<DogBreedImageResponse>



}