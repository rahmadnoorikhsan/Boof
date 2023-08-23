package com.ranosan.challenge.boof.presentation.screen.home

import com.ranosan.challenge.boof.presentation.screen.home.model.MovieItemUi

data class HomeState(
    val listPoster: List<MovieItemUi> = emptyList(),
    val listPopular: List<MovieItemUi> = emptyList(),
    val listTopRated: List<MovieItemUi> = emptyList(),
    val listTrending: List<MovieItemUi> = emptyList(),
    val listUpComing: List<MovieItemUi> = emptyList(),
    val listNowPlaying: List<MovieItemUi> = emptyList(),
    val isLoading: Boolean = false,
    val isError: String? = null
)
