package com.ranosan.challenge.boof.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ranosan.challenge.boof.domain.Poster
import com.ranosan.challenge.boof.presentation.screen.home.components.AutoSlidePoster

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeContent(
        viewModel.itemPoster
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    poster: List<Poster>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
    ) {
        AutoSlidePoster(poster = poster)
    }
}