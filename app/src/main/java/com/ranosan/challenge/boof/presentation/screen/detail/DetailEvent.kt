package com.ranosan.challenge.boof.presentation.screen.detail

sealed class DetailEvent {
    data class GetDetail(val id: Int) : DetailEvent()
    data class GetRecommend(val id: Int) : DetailEvent()
}