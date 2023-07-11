package com.ranosan.challenge.boof.data.source

import com.ranosan.challenge.boof.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPopularMovies() =
        apiService.getPopularMovies()

    suspend fun getTopRatedMovies() =
        apiService.getTopRatedMovies()
}