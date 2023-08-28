package com.ranosan.challenge.boof.presentation

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ranosan.challenge.boof.R
import com.ranosan.challenge.boof.presentation.navigation.NavigationItem
import com.ranosan.challenge.boof.presentation.navigation.Screen
import com.ranosan.challenge.boof.presentation.screen.detail.DetailScreen
import com.ranosan.challenge.boof.presentation.screen.favorite.FavoriteScreen
import com.ranosan.challenge.boof.presentation.screen.home.HomeScreen
import com.ranosan.challenge.boof.presentation.screen.search.SearchScreen
import kotlin.math.roundToInt

@Composable
fun BoofApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarHeight = 96.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember {
        mutableFloatStateOf(0f)
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(nestedScrollConnection),
        bottomBar = {
            if (currentRoute.shouldShowButtonAppBar())
                BottomBar(
                    navController = navController,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                x = 0,
                                y = -bottomBarOffsetHeightPx.floatValue.roundToInt()
                            )
                        }
                )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(Screen.Explore.route) {
                SearchScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("movieId") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("movieId") ?: 1
                DetailScreen(
                    id = id,
                    navigateToDetail = { idRecommend ->
                        navController.navigate(Screen.Detail.createRoute(idRecommend)) {
                            popUpTo(Screen.Detail.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        val items = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                iconActive = Icons.Default.Home,
                iconNonActive = Icons.Outlined.Home,
                screen = Screen.Home,
                description = stringResource(R.string.home_desc)
            ),
            NavigationItem(
                title = stringResource(R.string.explore),
                iconActive = Icons.Default.Search,
                iconNonActive = Icons.Outlined.Search,
                screen = Screen.Explore,
                description = stringResource(R.string.explore_desc)
            ),
            NavigationItem(
                title = stringResource(R.string.favorite),
                iconActive = Icons.Default.Favorite,
                iconNonActive = Icons.Outlined.FavoriteBorder,
                screen = Screen.Favorite,
                description = stringResource(R.string.favorite_desc)
            )
        )

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.scrim.copy(
                alpha = 0.5f
            )
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    label = {
                        if (currentRoute == item.screen.route) {
                            Text(
                                text = item.title,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(text = item.title)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == item.screen.route) item.iconActive else item.iconNonActive,
                            contentDescription = item.title,
                            tint = if (currentRoute == item.screen.route) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

private fun String?.shouldShowButtonAppBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.Explore.route,
        Screen.Favorite.route
    )
}