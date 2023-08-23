package com.ranosan.challenge.boof.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranosan.challenge.boof.domain.model.MovieItem
import com.ranosan.challenge.boof.domain.repository.MovieRepository
import com.ranosan.challenge.boof.util.Result
import com.ranosan.challenge.boof.util.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getPoster()
        getPopularMovies()
        getTopRatedMovies()
        getTrendingMovies()
        getUpComingMovies()
        getNowPlayingMovies()
    }

    private fun getPoster() = viewModelScope.launch {
        repository.getImageSliders().collect { result ->
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
                            listPoster = result.data.map(MovieItem::toUi).take(5)
                        )
                    }
                }
            }
        }
    }

    private fun getPopularMovies() = viewModelScope.launch {
        repository.getPopularMovies().collect { result ->
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
                            listPopular = result.data.map(MovieItem::toUi)
                        )
                    }
                }
            }
        }
    }

    private fun getTopRatedMovies() = viewModelScope.launch {
        repository.getTopRatedMovies().collect { result ->
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
                            listTopRated = result.data.map(MovieItem::toUi)
                        )
                    }
                }
            }
        }
    }

    private fun getTrendingMovies() = viewModelScope.launch {
        repository.getTrendingMovies().collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isError = result.message,
                            isLoading = false
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
                        Log.d("TREND", "getTrendingMovies: ${result.data}")
                        it.copy(
                            isLoading = false,
                            listTrending = result.data.map(MovieItem::toUi)
                        )
                    }
                }
            }
        }
    }

    private fun getUpComingMovies() = viewModelScope.launch {
        repository.getUpcomingMovies().collect { result ->
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
                            listUpComing = result.data.map(MovieItem::toUi).asReversed()
                        )
                    }
                }
            }
        }
    }

    private fun getNowPlayingMovies() = viewModelScope.launch {
        repository.getNowPlayingMovies().collect { result ->
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
                            listNowPlaying = result.data.map(MovieItem::toUi)
                        )
                    }
                }
            }
        }
    }
}