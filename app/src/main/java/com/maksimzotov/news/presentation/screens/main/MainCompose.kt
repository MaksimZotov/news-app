package com.maksimzotov.news.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maksimzotov.news.R
import com.maksimzotov.news.presentation.UIConstants
import com.maksimzotov.news.presentation.activity.MainActivityViewModel
import com.maksimzotov.news.presentation.entities.NavItem
import com.maksimzotov.news.presentation.screens.favorites.FavoritesCompose
import com.maksimzotov.news.presentation.screens.favorites.FavoritesViewModel
import com.maksimzotov.news.presentation.screens.home.HomeCompose
import com.maksimzotov.news.presentation.screens.home.HomeViewModel
import com.maksimzotov.news.presentation.screens.info.InfoCompose
import com.maksimzotov.news.presentation.screens.web.WebPageCompose

@Composable
fun MainCompose(
    viewModel: MainActivityViewModel,
    darkThemeIsAble: Boolean
) {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        val bottomBarHeight = 56.dp

        var urlToWebPageFromHome by rememberSaveable { mutableStateOf("") }
        val setUrlToWebPageFromHome: (String) -> Unit = { url -> urlToWebPageFromHome = url }

        var urlToWebPageFromFavorites by rememberSaveable { mutableStateOf("") }
        val setUrlToWebPageFromFavorites: (String) -> Unit = {
                url -> urlToWebPageFromFavorites = url
        }

        val bottomItems = listOf(
            NavItem.Home,
            NavItem.Favorites,
            NavItem.Info
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    actions = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_brightness_3_24),
                            contentDescription = stringResource(R.string.top_bar_title),
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable { viewModel.changeTheme(!darkThemeIsAble) }
                        )
                    }
                )
            },
            bottomBar = {
                BottomNavigation(
                    modifier = Modifier.height(bottomBarHeight)
                ){
                    val curNavBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = curNavBackStackEntry?.destination?.route
                    bottomItems.forEach { selectedItem ->
                        BottomNavigationItem(
                            selected =
                                currentRoute == selectedItem.route ||

                                    currentRoute == UIConstants.WEB_HOME &&
                                    navController.previousBackStackEntry?.destination?.route ==
                                    selectedItem.route ||

                                    currentRoute == UIConstants.WEB_FAVORITES &&
                                    navController.previousBackStackEntry?.destination?.route ==
                                    selectedItem.route,

                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White.copy(0.75f),
                            alwaysShowLabel = true,
                            onClick = {
                                val nc = navController
                                val backQueue = nc.backQueue
                                when (currentRoute) {
                                    NavItem.Home.route -> when(selectedItem) {
                                        NavItem.Favorites -> {
                                            if (backQueue.contains(UIConstants.WEB_FAVORITES))
                                                nc.popBackStack(UIConstants.WEB_FAVORITES)
                                            else
                                                nc.navigate(NavItem.Favorites.route)
                                        }
                                        NavItem.Info -> {
                                            nc.navigate(NavItem.Info.route)
                                        }
                                    }
                                    NavItem.Favorites.route -> when(selectedItem) {
                                        NavItem.Home -> {
                                            if (backQueue.contains(UIConstants.WEB_HOME))
                                                nc.popBackStack(UIConstants.WEB_HOME)
                                            else
                                                nc.popBackStack(NavItem.Home.route)
                                        }
                                        NavItem.Info -> {
                                            if (backQueue.contains(NavItem.Info.route))
                                                nc.popBackStack(NavItem.Info.route)
                                            else
                                                nc.navigate(NavItem.Info.route)
                                        }
                                    }
                                    NavItem.Info.route -> when(selectedItem) {
                                        NavItem.Home -> {
                                            if (backQueue.contains(UIConstants.WEB_HOME))
                                                nc.popBackStack(UIConstants.WEB_HOME)
                                            else
                                                nc.popBackStack(NavItem.Home.route)
                                        }
                                        NavItem.Favorites -> {
                                            if (backQueue.contains(UIConstants.WEB_FAVORITES))
                                                nc.popBackStack(UIConstants.WEB_FAVORITES)
                                            else {
                                                if (backQueue.contains(NavItem.Favorites.route))
                                                    nc.popBackStack(NavItem.Favorites.route)
                                                else
                                                    nc.navigate(NavItem.Favorites.route)
                                            }
                                        }
                                    }
                                    UIConstants.WEB_HOME -> when(selectedItem) {
                                        NavItem.Home -> {
                                            nc.popBackStack(NavItem.Home.route)
                                        }
                                        NavItem.Favorites -> {
                                            if (backQueue.contains(UIConstants.WEB_FAVORITES))
                                                nc.popBackStack(UIConstants.WEB_FAVORITES)
                                            else
                                                nc.navigate(NavItem.Favorites.route)
                                        }
                                        NavItem.Info -> {
                                            nc.navigate(NavItem.Info.route)
                                        }
                                    }
                                    UIConstants.WEB_FAVORITES -> when(selectedItem) {
                                        NavItem.Home -> {
                                            if (backQueue.contains(UIConstants.WEB_HOME))
                                                nc.popBackStack(UIConstants.WEB_HOME)
                                            else
                                                nc.popBackStack(NavItem.Home.route)
                                        }
                                        NavItem.Favorites -> {
                                            nc.popBackStack(NavItem.Favorites.route)
                                        }
                                        NavItem.Info -> {
                                            nc.navigate(NavItem.Info.route)
                                        }
                                    }
                                }
                            },
                            label = { Text(stringResource(selectedItem.title)) },
                            icon = {
                                Icon(
                                    painter = painterResource(id = selectedItem.icon),
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = NavItem.Home.route
            ) {
                composable(NavItem.Home.route) {
                    val homeViewModel: HomeViewModel = hiltViewModel()
                    HomeCompose(
                        homeViewModel,
                        navController,
                        bottomBarHeight,
                        setUrlToWebPageFromHome
                    )
                }
                composable(NavItem.Favorites.route) {
                    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
                    FavoritesCompose(
                        favoritesViewModel,
                        navController,
                        bottomBarHeight,
                        setUrlToWebPageFromFavorites
                    )
                }
                composable(NavItem.Info.route) {
                    InfoCompose(
                        bottomBarHeight
                    )
                }
                composable(UIConstants.WEB_HOME) {
                    WebPageCompose(
                        urlToWebPageFromHome,
                        bottomBarHeight
                    )
                }
                composable(UIConstants.WEB_FAVORITES) {
                    WebPageCompose(
                        urlToWebPageFromFavorites,
                        bottomBarHeight
                    )
                }
            }
        }
    }
}