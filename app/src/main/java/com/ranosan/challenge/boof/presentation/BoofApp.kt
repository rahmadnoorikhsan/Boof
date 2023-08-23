package com.ranosan.challenge.boof.presentation

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ranosan.challenge.boof.R
import com.ranosan.challenge.boof.presentation.navigation.NavigationItem
import com.ranosan.challenge.boof.presentation.navigation.Screen
import com.ranosan.challenge.boof.presentation.screen.detail.DetailScreen
import com.ranosan.challenge.boof.presentation.screen.home.HomeScreen
import kotlin.math.roundToInt

@Composable
fun BoofApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val systemUiController = rememberSystemUiController()
    val colorStatusBar = MaterialTheme.colorScheme.surface.copy(alpha = 0f)
    val colorNavBar = MaterialTheme.colorScheme.surface

    val bottomBarHeight = 80.dp
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
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorStatusBar,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = colorNavBar,
            darkIcons = true
        )
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
                                y = -bottomBarOffsetHeightPx.value.roundToInt()
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
            composable(Screen.Explore.route) {}
            composable(Screen.Profile.route) {}
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("movieId") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("movieId") ?: 1
                DetailScreen(
                    id = id
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
                title = stringResource(R.string.profile),
                iconActive = Icons.Default.Person,
                iconNonActive = Icons.Outlined.Person,
                screen = Screen.Profile,
                description = stringResource(R.string.profile_desc)
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
        Screen.Profile.route
    )
}