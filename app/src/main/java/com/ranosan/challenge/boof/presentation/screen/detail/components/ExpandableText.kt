package com.ranosan.challenge.boof.presentation.screen.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    descriptionMovie: String?
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    Text(
        text = "$descriptionMovie",
        maxLines = if (isExpanded) Int.MAX_VALUE else 3,
        style = MaterialTheme.typography.bodyLarge.copy(
            textAlign = TextAlign.Justify
        ),
        modifier = modifier.padding(horizontal = 16.dp)
    )
    TextButton(onClick = { isExpanded = !isExpanded }) {
        Text(text = (if (isExpanded) "...Show More" else "Hide").uppercase())
    }
}