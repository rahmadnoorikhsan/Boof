package com.ranosan.challenge.boof.data.source

import com.ranosan.challenge.boof.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPopularMovies() = apiService.getPopularMovies()

    suspend fun getTopRatedMovies() = apiService.getTopRatedMovies()

    suspend fun getTrendingMovies() = apiService.getAllTrending()

    suspend fun getUpcomingMovies() = apiService.getUpComingMovies()

    suspend fun getNowPlayingMovies() = apiService.getNowPlayingMovies()

    suspend fun getImageMovie(id: Int) = apiService.getImageMovie(id)

    suspend fun detailMovie(id: Int) = apiService.detailMovie(id)

    suspend fun getRecommend(id: Int) = apiService.recommendMovie(id)
}