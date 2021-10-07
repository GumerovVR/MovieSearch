package com.example.moviesearch.data.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object ApiFactory {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build()
    }

    val apiService by lazy { retrofit.create(MovieApiService::class.java) }
}