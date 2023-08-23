package com.ranosan.challenge.boof.presentation.screen.detail

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
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetDetail -> {
                getLogoMovie(event.id)
                getDetailMovie(event.id)
            }

            is DetailEvent.GetRecommend -> {
                getRecommend(event.id)
            }
        }
    }

    private fun getLogoMovie(id: Int) = viewModelScope.launch {
        repository.logoMovie(id).collect { result ->
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
        repository.detailMovie(id).collect { result ->
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
        repository.getRecommend(id).collect { result ->
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
}