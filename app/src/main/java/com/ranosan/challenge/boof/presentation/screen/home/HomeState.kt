package com.ranosan.challenge.boof.presentation.screen.home

import com.ranosan.challenge.boof.data.source.remote.response.movies.MovieItem

data class HomeState(
    val listPopular: List<MovieItem> = emptyList(),
    val listTopRated: List<MovieItem> = emptyList(),
    val isLoading: Boolean = false,
    val isError: String? = null
)
