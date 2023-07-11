package com.ranosan.challenge.boof.presentation.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ranosan.challenge.boof.data.source.remote.response.movies.MovieItem

@Composable
fun ListTopRated(
    topRatedItem: List<MovieItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = "Top Rated",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier.height(176.dp)
        ) {
            items(topRatedItem, key = { it.id }) { item ->
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original${item.posterPath}",
                    contentDescription = item.title,
                    modifier = Modifier.clip(MaterialTheme.shapes.small)
                )
            }
        }
    }
}