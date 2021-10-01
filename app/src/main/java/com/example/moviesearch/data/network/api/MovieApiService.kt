package com.example.moviesearch.data.network.api

import com.example.moviesearch.data.network.entities.NetworkMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    // [API Documentation](https://developers.themoviedb.org/3/)

    @GET("discover/movie")
    suspend fun getMoviesFromNetwork(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) lang: String = "en-US",
        @Query(QUERY_PARAM_SORT_BY)  sortBy: String,
        @Query(QUERY_PARAM_MIN_VOTE_COUNT)  minVoteCount: String = MIN_VOTE_COUNT_VALUE,
        @Query(QUERY_PARAM_PAGE)  page: Int
    ): Response<NetworkMoviesResponse>


    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_SORT_BY = "sort_by"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_MIN_VOTE_COUNT = "vote_count.gte"

        private const val API_KEY = "f7f6a1b9c480b02df1d8e3994c683597"
        private const val MIN_VOTE_COUNT_VALUE = "1000"

        const val SORT_BY_POPULARITY = "popularity.desc"
        const val SORT_BY_TOP_RATED = "vote_average.desc"
        const val UNKNOWN_ID = -1

        const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/"
        const val POSTER_SMALL_SIZE = "w185"
        const val POSTER_FULL_SIZE = "w780"
    }
}