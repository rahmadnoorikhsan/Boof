package com.ranosan.challenge.boof.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val iconActive: ImageVector,
    val iconNonActive: ImageVector,
    val screen: Screen,
    val description: String
)
