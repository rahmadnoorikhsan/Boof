package com.ranosan.challenge.boof.presentation.screen.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.ranosan.challenge.boof.core.presentation.model.MovieItemUi
import com.ranosan.challenge.boof.core.ui.theme.md_theme_dark_background
import com.ranosan.challenge.boof.core.util.Constants.getImageUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidePoster(
    poster: List<MovieItemUi>,
    modifier: Modifier = Modifier,
    delay: Long = 5000L,
    pagerState: PagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        poster.size
    }
) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    var pageKey by remember { mutableIntStateOf(0) }

    if (!isDragged) {
        with(pagerState) {
            if (poster.isNotEmpty()) {
                LaunchedEffect(key1 = pageKey) {
                    launch {
                        delay(delay)
                        val nextPage = (currentPage + 1).mod(poster.size)
                        animateScrollToPage(nextPage)
                        pageKey = nextPage
                    }
                }
            }
        }
    }

    ConstraintLayout(modifier) {
        val (logoCons, dotsIndicator) = createRefs()

        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(count = poster.size) { index ->
                if (index == pagerState.currentPage) {
                    AsyncImage(
                        model = getImageUrl(poster[index].backdropPath),
                        contentDescription = "${poster[index].title}",
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .drawWithCache {
                                val gradient = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        md_theme_dark_background
                                    ),
                                    startY = size.height / 3,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradient, blendMode = BlendMode.Multiply)
                                }
                            }
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            modifier = Modifier
                .constrainAs(logoCons) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) { index ->
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .requiredHeight(280.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.Transparent)
            ) {
                if (poster[index].logo != null) {
                    AsyncImage(
                        model = getImageUrl(poster[index].logo),
                        contentDescription = "${poster[index].title}",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .size(72.dp)
                    )
                } else {
                    Text(
                        text = "${poster[index].title?.uppercase()}",
                        maxLines = 2,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            fontSynthesis = FontSynthesis.Weight,
                            letterSpacing = TextUnit(value = 6f, type = TextUnitType.Sp)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .size(72.dp)
                    )
                }
            }
        }

        DotsIndicator(
            totalDots = poster.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .constrainAs(dotsIndicator) {
                    top.linkTo(logoCons.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}