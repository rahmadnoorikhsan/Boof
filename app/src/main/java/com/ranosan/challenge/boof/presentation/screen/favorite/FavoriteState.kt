package com.ranosan.challenge.boof.presentation.screen.favorite

import com.ranosan.challenge.boof.core.domain.model.Favorite

data class FavoriteState(
    val favoriteItem: List<Favorite> = emptyList()
)
