package com.ranosan.challenge.boof.presentation.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ranosan.challenge.boof.presentation.screen.home.model.MovieItemUi
import com.ranosan.challenge.boof.util.Constants.getImageUrl

@Composable
fun ListNowPlaying(
    nowPlaying: List<MovieItemUi>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = "Now Playing",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.height(176.dp)
        ) {
            items(nowPlaying, key = { it.id }) { item ->
                AsyncImage(
                    model = getImageUrl(item.posterPath),
                    contentDescription = item.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { navigateToDetail(item.id) }
                )
            }
        }
    }
}