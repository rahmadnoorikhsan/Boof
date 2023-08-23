package com.ranosan.challenge.boof.presentation.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ranosan.challenge.boof.util.Constants.getImageUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalDraggable(
    logoMovie: String?,
    titleMovie: String?,
    posterMovie: String?,
    modifier: Modifier = Modifier,
    state: DraggableState = rememberDraggableState(onDelta = { it != 0.5f }),
    orientation: Orientation = Orientation.Vertical,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .draggable(state, orientation)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = getImageUrl(logoMovie),
                contentDescription = titleMovie,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .requiredHeight(72.dp)
            )
            ModalBottomSheet(onDismissRequest = { }) {
                AsyncImage(
                    model = getImageUrl(posterMovie),
                    contentDescription = titleMovie
                )
            }
        }
    }
}