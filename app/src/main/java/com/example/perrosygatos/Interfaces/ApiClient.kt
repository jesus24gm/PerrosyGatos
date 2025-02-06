package com.example.perrosygatos.Interfaces

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val DOG_API_KEY = "live_1mAc85mODWFiW3h9mTAcCLMsdS15sVWnZ2hU62RpQm3rCubpWuRxKDa6qVRPlA"
    private const val CAT_API_KEY = "live_gQYhYOayJsyAAOwfBsxn7SuvKiEeVn97aUwkrnLd5OYi0iHPoqBkb1J9Qica5FMq"

    private val dogHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request: Request = chain.request().newBuilder()
            .addHeader("x-api-key", DOG_API_KEY)
            .build()
        chain.proceed(request)
    }.build()

    private val catHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request: Request = chain.request().newBuilder()
            .addHeader("x-api-key", CAT_API_KEY)
            .build()
        chain.proceed(request)
    }.build()

    private fun getRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dogApi: PerrosApiService = getRetrofit("https://api.thedogapi.com/v1/", dogHttpClient)
        .create(PerrosApiService::class.java)

    val catApi: GatosApiService = getRetrofit("https://api.thecatapi.com/v1/", catHttpClient)
        .create(GatosApiService::class.java)
}
