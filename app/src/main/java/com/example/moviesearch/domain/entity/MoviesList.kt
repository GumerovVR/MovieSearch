package com.example.moviesearch.domain.entity

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class MoviesList (

    @SerializedName("results")
    @Expose
    private val movies: List<Movie>?,

)