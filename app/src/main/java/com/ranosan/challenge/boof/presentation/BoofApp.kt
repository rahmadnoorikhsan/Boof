package com.ranosan.challenge.boof.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ranosan.challenge.boof.R
import com.ranosan.challenge.boof.presentation.navigation.NavigationItem
import com.ranosan.challenge.boof.presentation.navigation.Screen
import com.ranosan.challenge.boof.presentation.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoofApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute.shouldShowButtonAppBar()) BottomBar(navController)
        }
    ) { innerPadding ->
            NavHost(navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen()
                }
                composable(Screen.Explore.route) {}
                composable(Screen.Profile.route) {}
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

    NavigationBar(modifier) {
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
                iconActive = Icons.Default.Face,
                iconNonActive = Icons.Outlined.Face,
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
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == item.screen.route) item.iconActive else item.iconNonActive,
                            contentDescription = item.description
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
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