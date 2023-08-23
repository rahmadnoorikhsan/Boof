package com.ranosan.challenge.boof.presentation.screen.detail

import com.ranosan.challenge.boof.presentation.screen.detail.model.DetailMovieUi
import com.ranosan.challenge.boof.presentation.screen.home.model.MovieItemUi

data class DetailState(
    val listRecommend: List<MovieItemUi> = emptyList(),
    val logoMovie: String? = null,
    val detailMovie: DetailMovieUi? = null,
    val isError: String? = null,
    val isLoading: Boolean = false
)
