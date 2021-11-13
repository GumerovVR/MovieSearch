package com.example.moviesearch.data.network.entities


import com.google.gson.annotations.SerializedName

data class NetworkTrailersResponce(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results: List<NetworkTrailer>
)