package com.ranosan.challenge.boof.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ranosan.challenge.boof.data.source.remote.response.movies.MovieItem
import com.ranosan.challenge.boof.presentation.screen.home.components.AutoSlidePoster
import com.ranosan.challenge.boof.presentation.screen.home.components.ListTopRated

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        poster = state.listPopular,
        topRated = state.listTopRated
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    poster: List<MovieItem>,
    topRated: List<MovieItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()
    ) {
        AutoSlidePoster(
            poster = poster
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListTopRated(
            topRatedItem = topRated
        )
    }
}