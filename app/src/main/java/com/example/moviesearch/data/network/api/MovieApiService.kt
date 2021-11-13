package com.example.moviesearch.data.network.api

import com.example.moviesearch.common.Const.API_KEY
import com.example.moviesearch.data.network.entities.NetworkMoviesResponse
import com.example.moviesearch.data.network.entities.NetworkTrailersResponce
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    // [API Documentation](https://developers.themoviedb.org/3/)

    @GET("discover/movie")
    suspend fun getMoviesFromNetwork(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) lang: String = LANG_ENG,
        @Query(QUERY_PARAM_SORT_BY)  sortBy: String,
        @Query(QUERY_PARAM_MIN_VOTE_COUNT)  minVoteCount: String = MIN_VOTE_COUNT_VALUE,
        @Query(QUERY_PARAM_PAGE)  page: Int
    ): Response<NetworkMoviesResponse>

    @GET("search/movie")
    suspend fun getSearchMoviesFromNetwork(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) lang: String = LANG_ENG,
        @Query(QUERY_PARAM_QUERY) query: String,
        @Query(QUERY_PARAM_PAGE)  page: Int
    ): Response<NetworkMoviesResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailersForMovie(
        @Path(PATCH_PARAM_MOVIE_ID) movieId: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) lang: String = LANG_ENG
    ): NetworkTrailersResponce


    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_SORT_BY = "sort_by"
        private const val QUERY_PARAM_QUERY = "query"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_MIN_VOTE_COUNT = "vote_count.gte"
        private const val PATCH_PARAM_MOVIE_ID = "movie_id"

        private const val MIN_VOTE_COUNT_VALUE = "1000"
        const val LANG_ENG = "en-US"
        const val SORT_BY_POPULARITY = "popularity.desc"
        const val SORT_BY_TOP_RATED = "vote_average.desc"
        const val SORT_BY_REVENUE = "revenue.desc"
        const val UNKNOWN_ID = -1
    }
}