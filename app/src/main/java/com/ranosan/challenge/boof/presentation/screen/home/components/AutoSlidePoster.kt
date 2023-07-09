package com.ranosan.challenge.boof.presentation.screen.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.ranosan.challenge.boof.domain.Poster
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidePoster(
    poster: List<Poster>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState()
) {
    LaunchedEffect(pagerState.currentPage) {
        delay(5000)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % poster.size)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            pageCount = poster.size,
            state = pagerState,
            pageSize = PageSize.Fill,
            beyondBoundsPageCount = poster.size
        ) { page ->
            PosterMovie(
                poster = poster[page].poster,
                title = poster[page].title,
                year = poster[page].year,
                country = poster[page].country,
                genre = poster[page].genre
            )
        }
        DotsIndicator(
            totalDots = poster.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 8.dp,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun PosterMovie(
    poster: String,
    title: String,
    year: Int,
    country: String,
    genre: String,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier) {
        val (posterCons, aboutCons) = createRefs()

        AsyncImage(
            model = poster,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .constrainAs(posterCons) {
                    top.linkTo(parent.top)
                }
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height/3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )

        AboutMovie(
            title = title,
            year = year,
            country = country,
            genre = genre,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(aboutCons) {
                    bottom.linkTo(posterCons.bottom, margin = 16.dp)
                }
        )
    }
}

@Composable
fun AboutMovie(
    title: String,
    year: Int,
    country: String,
    genre: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = title.uppercase(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = year.toString())
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.White)
            )
            Text(text = country)
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.White)
            )
            Text(text = genre)
        }
    }
}