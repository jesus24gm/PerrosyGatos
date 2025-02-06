package com.example.perrosygatos.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perrosygatos.Interfaces.GatosApiService
import com.example.perrosygatos.Modelos.CatBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.perrosygatos.Modelos.CatImageResponse
class GatosViewModel : ViewModel() {

    var catBreeds by mutableStateOf<List<CatBreed>>(emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)

        private set

    private val gatosApiService: GatosApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gatosApiService = retrofit.create(GatosApiService::class.java)

        fetchCatBreeds()
    }

    fun fetchCatBreeds() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    gatosApiService.getCatBreeds().execute()
                }
                if (response.isSuccessful) {
                    response.body()?.let { breeds ->
                        catBreeds = breeds
                    }
                } else {
                    errorMessage = "Error al obtener razas: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage = "Error al obtener razas: ${e.message}"
            }
        }
    }

    suspend fun getCatImage(referenceImageId: String): String? {
        return try {
            val response = withContext(Dispatchers.IO) {
                gatosApiService.getCatBreedImages(referenceImageId).execute()
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
