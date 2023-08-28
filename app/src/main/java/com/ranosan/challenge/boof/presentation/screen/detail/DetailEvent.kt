package com.ranosan.challenge.boof.presentation.screen.detail

import com.ranosan.challenge.boof.core.presentation.model.DetailMovieUi

sealed class DetailEvent {
    data class GetDetail(val id: Int) : DetailEvent()
    data class GetRecommend(val id: Int) : DetailEvent()
    data class OnFavClick(val isFav: Boolean, val id: Int, val detailMovie: DetailMovieUi?): DetailEvent()
}