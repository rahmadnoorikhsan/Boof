package com.ranosan.challenge.boof.domain.repository

import com.ranosan.challenge.boof.data.source.remote.response.movies.MovieItem
import com.ranosan.challenge.boof.util.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlaying(): Flow<Result<List<MovieItem>>>

    fun getTopRatedMovies(): Flow<Result<List<MovieItem>>>
}