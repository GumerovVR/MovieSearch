package com.example.moviesearch.data.network

import com.example.moviesearch.domain.entity.MoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getTopMoviesFromJSON(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) lang: String = "en-US",
        @Query(QUERY_PARAM_SORT_BY)  sortBy: String = SORT_BY_TOP_RATED,
        @Query(QUERY_PARAM_MIN_VOTE_COUNT)  minVoteCount: String = MIN_VOTE_COUNT_VALUE,
        @Query(QUERY_PARAM_PAGE)  page: String = "1"
    ): Single<MoviesList>


    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_SORT_BY = "sort_by"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_MIN_VOTE_COUNT = "vote_count.gte"

        private const val API_KEY = "f7f6a1b9c480b02df1d8e3994c683597"
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
        private const val MIN_VOTE_COUNT_VALUE = "1000"

    }
}