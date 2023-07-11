package com.ranosan.challenge.boof.presentation.screen.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.ranosan.challenge.boof.data.source.remote.response.movies.MovieItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidePoster(
    poster: List<MovieItem>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
) {
    ConstraintLayout(modifier) {
        val (posterCons, dotsIndicator) = createRefs()

        HorizontalPager(
            pageCount = poster.size,
            state = pagerState,
            modifier = Modifier
                .constrainAs(posterCons) {
                    top.linkTo(parent.top)
                }
        ) { index ->
            PosterMovie(
                poster = "https://image.tmdb.org/t/p/original${poster[index].backdropPath}",
                title = "${poster[index].title}",
            )
        }
        DotsIndicator(
            totalDots = poster.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 8.dp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .constrainAs(dotsIndicator) {
                    top.linkTo(posterCons.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun PosterMovie(
    poster: String,
    title: String,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier) {
        val (imageCons, titleCons) = createRefs()

        AsyncImage(
            model = poster,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxHeight(0.5f)
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
                .constrainAs(imageCons) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
        AboutMovie(
            title = title,
            modifier = Modifier
                .constrainAs(titleCons) {
                    bottom.linkTo(imageCons.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun AboutMovie(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = title.uppercase(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}