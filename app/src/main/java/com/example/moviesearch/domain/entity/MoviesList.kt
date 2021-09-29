package com.example.moviesearch.domain.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class MoviesList (

    @SerializedName("results")
    @Expose
    val movies: List<Movie>,

)