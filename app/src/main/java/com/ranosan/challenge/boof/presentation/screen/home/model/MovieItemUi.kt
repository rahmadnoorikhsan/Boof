package com.ranosan.challenge.boof.presentation.screen.home.model

data class MovieItemUi(
    val overview: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val video: Boolean? = false,
    val title: String? = "",
    val genreIds: List<Int> = emptyList(),
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val releaseDate: String? = "",
    val popularity: Double? = 0.0,
    val voteAverage: Double? = 0.0,
    val id: Int,
    val adult: Boolean? = false,
    val voteCount: Int? = 0,
    var logo: String? = "",
)