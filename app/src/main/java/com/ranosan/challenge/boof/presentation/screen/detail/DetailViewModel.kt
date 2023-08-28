package com.ranosan.challenge.boof.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranosan.challenge.boof.core.domain.model.Favorite
import com.ranosan.challenge.boof.core.domain.model.MovieItem
import com.ranosan.challenge.boof.core.domain.repository.FavoriteRepository
import com.ranosan.challenge.boof.core.domain.repository.MovieRepository
import com.ranosan.challenge.boof.core.util.Result
import com.ranosan.challenge.boof.core.util.toEntity
import com.ranosan.challenge.boof.core.util.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetDetail -> {
                getLogoMovie(event.id)
                getDetailMovie(event.id)
                checkIsFavorite(event.id)
            }

            is DetailEvent.GetRecommend -> {
                getRecommend(event.id)
                getSimilar(event.id)
            }

            is DetailEvent.OnFavClick -> {
                if (event.detailMovie != null) {
                    val detailMovie = event.detailMovie
                    val favoriteItem = Favorite(
                        id = detailMovie.id,
                        title = detailMovie.title,
                        overview = detailMovie.overview,
                        originalLanguage = detailMovie.originalLanguage,
                        originalTitle = detailMovie.originalTitle,
                        video = detailMovie.video,
                        posterPath = detailMovie.posterPath,
                        backdropPath = detailMovie.backdropPath,
                        releaseDate = detailMovie.releaseDate,
                        popularity = detailMovie.popularity,
                        voteAverage = detailMovie.voteAverage,
                        adult = detailMovie.adult,
                        voteCount = detailMovie.id
                    )
                    addOrRemoveFromFavorite(event.isFav, favoriteItem)
                }
            }
        }
    }

    private fun getLogoMovie(id: Int) = viewModelScope.launch {
        movieRepository.logoMovie(id).collect { result ->
            when(result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            logoMovie = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getDetailMovie(id: Int) = viewModelScope.launch {
        movieRepository.detailMovie(id).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }

                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            detailMovie = result.data.toUi()
                        )
                    }
                }
            }
        }
    }

    private fun getRecommend(id: Int) = viewModelScope.launch {
        movieRepository.getRecommend(id).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            listRecommend = result.data.map(MovieItem::toUi)
                        )
                    }
                }
            }
        }
    }

    private fun getSimilar(id: Int) = viewModelScope.launch {
        movieRepository.getSimilar(id).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            listSimilar = result.data.map(MovieItem::toUi)
                        )
                    }
                }
            }
        }
    }

    private fun addOrRemoveFromFavorite(isFav: Boolean, favoriteItem: Favorite) = viewModelScope.launch {
        if (isFav) {
            favoriteRepository.insertFavorite(favoriteItem.toEntity())
        } else {
            favoriteRepository.deleteFavorite(favoriteItem.toEntity().id)
        }
    }

    private fun checkIsFavorite(id: Int) = viewModelScope.launch {
        favoriteRepository.isFavorite(id).collect { isFav ->
            _state.update {
                it.copy(
                    isFav = isFav
                )
            }
        }
    }
}