package com.ranosan.challenge.boof.core.domain.repository

import com.ranosan.challenge.boof.core.domain.model.DetailMovie
import com.ranosan.challenge.boof.core.domain.model.MovieItem
import com.ranosan.challenge.boof.core.util.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Result<List<MovieItem>>>

    fun getTopRatedMovies(): Flow<Result<List<MovieItem>>>

    fun getTrendingMovies(): Flow<Result<List<MovieItem>>>

    fun getUpcomingMovies(): Flow<Result<List<MovieItem>>>

    fun getNowPlayingMovies(): Flow<Result<List<MovieItem>>>

    fun getImageSliders(): Flow<Result<List<MovieItem>>>

    fun logoMovie(id: Int): Flow<Result<String?>>

    fun detailMovie(id: Int): Flow<Result<DetailMovie>>

    fun getRecommend(id: Int): Flow<Result<List<MovieItem>>>

    fun getSimilar(id: Int): Flow<Result<List<MovieItem>>>
}