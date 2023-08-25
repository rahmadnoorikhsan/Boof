package com.ranosan.challenge.boof.core.util

import com.ranosan.challenge.boof.core.data.source.remote.response.movies.DetailResponse
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.LogosResponseItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.MovieResponseItem
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.RecommendItem
import com.ranosan.challenge.boof.core.domain.model.DetailMovie
import com.ranosan.challenge.boof.core.domain.model.LogoItem
import com.ranosan.challenge.boof.core.domain.model.MovieItem
import com.ranosan.challenge.boof.core.presentation.model.DetailMovieUi
import com.ranosan.challenge.boof.core.presentation.model.MovieItemUi

fun MovieResponseItem.toDomain() = MovieItem(
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    video = video,
    title = title,
    genreIds = genreIds,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    popularity = popularity,
    voteAverage = voteAverage,
    id = id,
    adult = adult,
    voteCount = voteCount
)

fun MovieItem.toUi() = MovieItemUi(
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    video = video,
    title = title,
    genreIds = genreIds,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    popularity = popularity,
    voteAverage = voteAverage,
    id = id,
    adult = adult,
    voteCount = voteCount,
    logo = logo
)


fun LogosResponseItem.toDomain() = LogoItem(
    filePath = filePath
)


fun DetailResponse.toDomain() = DetailMovie(
    originalLanguage = originalLanguage,
    imdbId = imdbId,
    video = video,
    title = title,
    backdropPath = backdropPath,
    revenue = revenue,
    genres = genres,
    popularity = popularity,
    productionCountries = productionCountries,
    id = id,
    voteCount = voteCount,
    budget = budget,
    overview = overview,
    originalTitle = originalTitle,
    runtime = runtime,
    posterPath = posterPath,
    spokenLanguages = spokenLanguages,
    productionCompanies = productionCompanies
)

fun DetailMovie.toUi() = DetailMovieUi(
    originalLanguage = originalLanguage,
    imdbId = imdbId,
    video = video,
    title = title,
    backdropPath = backdropPath,
    revenue = revenue,
    genres = genres,
    popularity = popularity,
    productionCountries = productionCountries,
    id = id,
    voteCount = voteCount,
    budget = budget,
    overview = overview,
    originalTitle = originalTitle,
    runtime = runtime,
    posterPath = posterPath,
    spokenLanguages = spokenLanguages,
    productionCompanies = productionCompanies,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    belongsToCollection = belongsToCollection,
    tagline = tagline
)

fun RecommendItem.toDomain() = MovieItem(
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    video = video,
    title = title,
    genreIds = genreIds,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    popularity = popularity,
    adult = adult,
    voteCount = voteCount,
    voteAverage = voteAverage,
    id = id
)
