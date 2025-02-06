package com.example.perrosygatos.Interfaces
import com.example.perrosygatos.Modelos.CatBreed
import com.example.perrosygatos.Modelos.CatImageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface GatosApiService {
    @GET("breeds")
    fun getCatBreeds(): Call<List<CatBreed>>

    @GET("images/{image_id}")
    fun getCatBreedImages(
        @Path("image_id") breedId: String): Call<CatImageResponse>
}