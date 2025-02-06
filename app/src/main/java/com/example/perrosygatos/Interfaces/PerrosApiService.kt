package com.example.perrosygatos.Interfaces

import com.example.perrosygatos.Modelos.DogBreed
import com.example.perrosygatos.Modelos.DogBreedImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PerrosApiService {
    @GET("breeds")
    fun getAllBreeds(): Call<List<DogBreed>>  // Raza -> Lista de imágenes

    // Obtener una imagen de una raza específica
    @GET("images/{image_id}")
    fun getBreedImage(@Path("image_id") breed: String): Call<DogBreedImageResponse>

    // Obtener información de la raza, si está disponible
    @GET("breed/{breed}/images")
    fun getBreedInfo(@Path("breed") breed: String): Call<Map<String, List<String>>>


}