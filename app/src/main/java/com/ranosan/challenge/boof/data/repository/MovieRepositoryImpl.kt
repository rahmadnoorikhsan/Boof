package com.ranosan.challenge.boof.data.repository

import com.ranosan.challenge.boof.data.source.RemoteDataSource
import com.ranosan.challenge.boof.domain.repository.MovieRepository
import com.ranosan.challenge.boof.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override fun getNowPlaying() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getPopularMovies()
            emit(Result.Success(response.results))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getTopRatedMovies() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getTopRatedMovies()
            emit(Result.Success(response.results))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }
}