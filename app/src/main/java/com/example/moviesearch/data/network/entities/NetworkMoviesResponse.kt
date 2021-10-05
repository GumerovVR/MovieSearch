package com.example.moviesearch.data.network.entities

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class NetworkMoviesResponse (

    @SerializedName("results")
    @Expose
    val networkMovies: List<NetworkMovie>

)