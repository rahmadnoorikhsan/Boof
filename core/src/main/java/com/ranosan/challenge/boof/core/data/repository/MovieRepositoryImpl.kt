package com.ranosan.challenge.boof.core.data.repository

import com.ranosan.challenge.boof.core.data.source.RemoteDataSource
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.LogosResponseItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.MovieResponseItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.RecommendItem
import com.ranosan.challenge.boof.core.domain.repository.MovieRepository
import com.ranosan.challenge.boof.core.util.Result
import com.ranosan.challenge.boof.core.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override fun getPopularMovies() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getPopularMovies().results.map(MovieResponseItem::toDomain)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getTopRatedMovies() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getTopRatedMovies().results.map(MovieResponseItem::toDomain)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getTrendingMovies() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getTrendingMovies().results.map(MovieResponseItem::toDomain)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getUpcomingMovies() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getUpcomingMovies().results.map(MovieResponseItem::toDomain)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getNowPlayingMovies() = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getNowPlayingMovies().results.map(MovieResponseItem::toDomain)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getImageSliders() = flow {
        emit(Result.Loading())
        try {
            val trending = remoteDataSource.getNowPlayingMovies().results.map(MovieResponseItem::toDomain)
            val result = trending.map {
                it.logo = remoteDataSource.getImageMovie(it.id).logos.map(LogosResponseItem::toDomain).firstOrNull()?.filePath
                it
            }
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun logoMovie(id: Int) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getImageMovie(id).logos.map(LogosResponseItem::toDomain).firstOrNull()?.filePath
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun detailMovie(id: Int) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.detailMovie(id).toDomain()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getRecommend(id: Int) = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getRecommend(id).results.map(RecommendItem::toDomain)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}