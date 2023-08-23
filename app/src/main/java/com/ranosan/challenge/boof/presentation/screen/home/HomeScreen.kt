package com.ranosan.challenge.boof.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ranosan.challenge.boof.presentation.screen.home.components.AutoSlidePoster
import com.ranosan.challenge.boof.presentation.screen.home.components.ListNowPlaying
import com.ranosan.challenge.boof.presentation.screen.home.components.ListPopular
import com.ranosan.challenge.boof.presentation.screen.home.components.ListTopRated
import com.ranosan.challenge.boof.presentation.screen.home.components.ListTrending
import com.ranosan.challenge.boof.presentation.screen.home.components.ListUpComing
import com.ranosan.challenge.boof.presentation.screen.home.model.MovieItemUi

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        poster = state.listPoster,
        popular = state.listPopular,
        trending = state.listTrending,
        topRated = state.listTopRated,
        upComing = state.listUpComing,
        nowPlaying = state.listNowPlaying,
        navigateToDetail = navigateToDetail
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    poster: List<MovieItemUi>,
    popular: List<MovieItemUi>,
    topRated: List<MovieItemUi>,
    trending: List<MovieItemUi>,
    upComing: List<MovieItemUi>,
    nowPlaying: List<MovieItemUi>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        AutoSlidePoster(
            poster = poster,
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListTrending(
            trendingItem = trending,
            navigateToDetail = navigateToDetail
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListPopular(
            popularItem = popular,
            navigateToDetail = navigateToDetail
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListNowPlaying(
            nowPlaying = nowPlaying,
            navigateToDetail = navigateToDetail
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListTopRated(
            topRatedItem = topRated,
            navigateToDetail = navigateToDetail
        )
        Spacer(modifier = Modifier.height(16.dp))
        ListUpComing(
            upComingItem = upComing,
            navigateToDetail = navigateToDetail
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}