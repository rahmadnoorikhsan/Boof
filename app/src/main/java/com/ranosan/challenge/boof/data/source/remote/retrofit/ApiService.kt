package com.ranosan.challenge.boof.data.source.remote.retrofit

import com.ranosan.challenge.boof.data.source.remote.response.movies.MoviesResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies() : MoviesResponse
}