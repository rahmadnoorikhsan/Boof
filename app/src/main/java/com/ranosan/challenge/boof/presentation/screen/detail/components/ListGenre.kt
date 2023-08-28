package com.ranosan.challenge.boof.presentation.screen.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ranosan.challenge.boof.R
import com.ranosan.challenge.boof.core.data.source.remote.response.movies.GenresItem

@Composable
fun ListGenre(
    listGenre: List<GenresItem>,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.bullet_symbol))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
        ) {
            items(listGenre, {it.id}) {item ->
                Text(
                    text = stringResource(R.string.bullet_symbol_with_text, "${item.name}"),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}