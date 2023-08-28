package com.ranosan.challenge.boof.core.data.source.remote.retrofit

import com.ranosan.challenge.boof.core.data.source.remote.response.movies.DetailResponse
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.ImageResponse
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.MoviesResponse
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.SimilarRecomendResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies() : MoviesResponse

    @GET("movie/{movie_id}/images")
    suspend fun getImageMovie(
        @Path("movie_id") id: Int,
        @Query("include_image_language") imageLanguage: String = "en"
    ) : ImageResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies() : MoviesResponse

    @GET("trending/movie/day")
    suspend fun getAllTrending() : MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpComingMovies() : MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies() : MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun detailMovie(
        @Path("movie_id") id: Int
    ) : DetailResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun recommendMovie(
        @Path("movie_id") id: Int
    ) : SimilarRecomendResponse

    @GET("movie/{movie_id}/similar")
    suspend fun similarMovie(
        @Path("movie_id") id: Int
    ) : SimilarRecomendResponse
}