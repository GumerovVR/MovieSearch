package com.example.moviesearch.data.network.entities

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class NetworkMovie(
    @SerializedName("adult")
    @Expose
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,

    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>?,

    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String?,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String?,

    @SerializedName("overview")
    @Expose
    val overview: String?,

    @SerializedName("popularity")
    @Expose
    val popularity: Double?,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("video")
    @Expose
    val video: Boolean?,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int?
) {
    companion object {
        private const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/"
        private const val POSTER_SMALL_SIZE = "w185"
        private const val POSTER_FULL_SIZE = "w780"
    }
    fun getSmallSizePosterPatch(): String{
        return BASE_URL_POSTER + POSTER_SMALL_SIZE + posterPath
    }
    fun getFullSizePosterPatch(): String{
        return BASE_URL_POSTER + POSTER_FULL_SIZE + posterPath
    }
}