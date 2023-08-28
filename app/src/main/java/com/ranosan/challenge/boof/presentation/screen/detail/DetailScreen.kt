package com.ranosan.challenge.boof.presentation.screen.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ranosan.challenge.boof.core.presentation.model.DetailMovieUi
import com.ranosan.challenge.boof.core.presentation.model.MovieItemUi
import com.ranosan.challenge.boof.core.util.Constants.getImageUrl
import com.ranosan.challenge.boof.presentation.screen.detail.components.ListGenre
import com.ranosan.challenge.boof.presentation.screen.detail.components.ListRecommends
import com.ranosan.challenge.boof.presentation.screen.detail.components.ListSimilar

@Composable
fun DetailScreen(
    id: Int,
    navigateToDetail: (Int) -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(DetailEvent.GetDetail(id))
        viewModel.onEvent(DetailEvent.GetRecommend(id))
    }

    DetailContent(
        logoMovies = state.logoMovie,
        detailMovie = state.detailMovie,
        listRecommend = state.listRecommend,
        listSimilar = state.listSimilar,
        navigateToDetail = navigateToDetail,
        isFav = state.isFav,
        onFavClick = {
            viewModel.onEvent(
                DetailEvent.OnFavClick(
                    !state.isFav,
                    id,
                    state.detailMovie
                )
            )
        }
    )
}

@Composable
fun DetailContent(
    logoMovies: String?,
    detailMovie: DetailMovieUi?,
    isFav: Boolean,
    modifier: Modifier = Modifier,
    listRecommend: List<MovieItemUi>,
    listSimilar: List<MovieItemUi>,
    navigateToDetail: (Int) -> Unit,
    onFavClick: () -> Unit,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = getImageUrl(detailMovie?.backdropPath),
            contentDescription = detailMovie?.title,
            modifier = Modifier
                .padding(16.dp)
                .clip(MaterialTheme.shapes.small)
        )
        if (logoMovies?.isNotEmpty() == true) {
            AsyncImage(
                model = getImageUrl(logoMovies),
                contentDescription = detailMovie?.title,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .requiredHeight(72.dp)
            )
        } else {
            Text(
                text = detailMovie?.title?.uppercase() ?: "",
                maxLines = 2,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    fontSynthesis = FontSynthesis.Weight,
                    letterSpacing = TextUnit(value = 6f, type = TextUnitType.Sp)
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        detailMovie?.genres?.take(3).let { listGenre ->
            if (listGenre != null) {
                ListGenre(
                    listGenre = listGenre,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(
            onClick = onFavClick
        ) {
            Icon(
                imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = if (isFav) MaterialTheme.colorScheme.primary else Color.White,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        detailMovie?.overview?.let { overview ->
            Text(
                text = overview,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Justify
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (listRecommend.isNotEmpty()) {
            ListRecommends(
                listRecommend = listRecommend,
                navigateToDetail = navigateToDetail,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (listSimilar.isNotEmpty()) {
            ListSimilar(
                listSimilar = listSimilar,
                navigateToDetail = navigateToDetail,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}
