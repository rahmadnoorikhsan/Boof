package com.ranosan.challenge.boof.presentation.screen.detail

import com.ranosan.challenge.boof.core.presentation.model.DetailMovieUi
import com.ranosan.challenge.boof.core.presentation.model.MovieItemUi

data class DetailState(
    val listRecommend: List<MovieItemUi> = emptyList(),
    val listSimilar: List<MovieItemUi> = emptyList(),
    val logoMovie: String? = null,
    val detailMovie: DetailMovieUi? = null,
    val isFav: Boolean = false,
    val isError: String? = null,
    val isLoading: Boolean = false
)
