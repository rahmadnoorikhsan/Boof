package com.ranosan.challenge.boof.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Explore: Screen("exploration")
    object Favorite: Screen("favorite")
    object Detail: Screen ("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}
