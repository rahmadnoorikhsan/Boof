package com.ranosan.challenge.boof.core.domain.model

import com.ranosan.challenge.boof.core.data.source.remote.response.movies.GenresItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.ProductionCompaniesItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.ProductionCountriesItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.SpokenLanguagesItem

data class DetailMovie(
    val originalLanguage: String? = "",
    val imdbId: String? = "",
    val video: Boolean? = false,
    val title: String? = "",
    val backdropPath: String? = "",
    val revenue: Int? = 0,
    val genres: List<GenresItem>,
    val popularity: Double? = 0.0,
    val productionCountries: List<ProductionCountriesItem>,
    val id: Int,
    val voteCount: Int? = 0,
    val budget: Int? = 0,
    val overview: String? = "",
    val originalTitle: String? = "",
    val runtime: Int? = 0,
    val posterPath: String? = "",
    val spokenLanguages: List<SpokenLanguagesItem>,
    val productionCompanies: List<ProductionCompaniesItem>,
    val releaseDate: String? = "",
    val voteAverage: Double? = 0.0,
    val belongsToCollection: Any? = "",
    val tagline: String? = "",
    val adult: Boolean? = false,
    val homepage: String? = "",
    val status: String? = ""
)
