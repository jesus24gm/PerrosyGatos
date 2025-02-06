package com.example.perrosygatos.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.perrosygatos.Interfaces.PerrosApiService
import com.example.perrosygatos.Modelos.DogBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerrosViewModel : ViewModel() {

    var dogBreeds by mutableStateOf<List<DogBreed>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
    var breedDescription by mutableStateOf<String?>(null) // Estado para la descripción
        private set



    private val perrosApiService: PerrosApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        perrosApiService = retrofit.create(PerrosApiService::class.java)




        fetchDogBreeds()
    }

    fun fetchDogBreeds() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    perrosApiService.getAllBreeds().execute()
                }
                if (response.isSuccessful) {
                    response.body()?.let { breeds ->
                        dogBreeds = breeds
                    }
                } else {
                    errorMessage = "Error al obtener razas: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error al obtener razas: ${e.message}"
            }
        }
    }

    suspend fun getDogImage(referenceImageId: String): String? {
        return try {
            val response = withContext(Dispatchers.IO) {
                perrosApiService.getBreedImage(referenceImageId).execute()
            }
            if (response.isSuccessful) {
                response.body()?.url
            } else {
                errorMessage = "Error al obtener imagen: ${response.code()}"
                null
            }
        } catch (e: Exception) {
            errorMessage = "Error al obtener imagen: ${e.message}"
            null
        }
    }


}


