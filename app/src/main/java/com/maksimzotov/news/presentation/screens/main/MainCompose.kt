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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maksimzotov.news.R
import com.maksimzotov.news.presentation.UIConstants
import com.maksimzotov.news.presentation.activity.MainActivityViewModel
import com.maksimzotov.news.presentation.entities.NavigationItem
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

        var urlToWebPage by rememberSaveable { mutableStateOf("")}
        val setUrlToWebPage: (String) -> Unit = { url -> urlToWebPage = url}

        val bottomItems = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Info
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

                                    currentRoute == UIConstants.WEB_PAGE_FROM_HOME_ROUTE &&
                                    navController.previousBackStackEntry?.destination?.route ==
                                    selectedItem.route ||

                                    currentRoute == UIConstants.WEB_PAGE_FROM_FAVORITES_ROUTE &&
                                    navController.previousBackStackEntry?.destination?.route ==
                                    selectedItem.route,

                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White.copy(0.75f),
                            alwaysShowLabel = true,
                            onClick = {
                                val backQueue = navController.backQueue
                                if (selectedItem == NavigationItem.Home) {
                                    if (currentRoute !in listOf(
                                            NavigationItem.Favorites.route,
                                            NavigationItem.Info.route
                                        )) {
                                        navController.popBackStack(
                                            NavigationItem.Home.route,
                                            false
                                        )
                                    } else {
                                        val lastEntry = backQueue.last {
                                            it.destination.route !in listOf(
                                                NavigationItem.Favorites.route,
                                                NavigationItem.Info.route
                                            )
                                        }
                                        lastEntry.destination.route?.let { route ->
                                            navController.popBackStack(
                                                route,
                                                false
                                            )
                                        }
                                    }
                                } else if (selectedItem == NavigationItem.Favorites) {
                                    if (currentRoute == NavigationItem.Home.route &&
                                        !backQueue.any {
                                            it.destination.route == NavigationItem.Favorites.route
                                        }
                                    ) {
                                        navController.navigate(NavigationItem.Favorites.route)
                                    } else if (
                                        currentRoute == UIConstants.WEB_PAGE_FROM_HOME_ROUTE
                                    ) {
                                        navController.navigate(NavigationItem.Favorites.route)
                                    } else {
                                        val lastEntry = backQueue.last {
                                            it.destination.route !in listOf(
                                                NavigationItem.Home.route,
                                                NavigationItem.Info.route
                                            )
                                        }
                                        lastEntry.destination.route?.let { route ->
                                            navController.popBackStack(
                                                route,
                                                false
                                            )
                                        }
                                    }
                                } else {
                                    val nextRoute =
                                        if (selectedItem == NavigationItem.Info &&
                                            currentRoute != NavigationItem.Info.route) {
                                            NavigationItem.Info.route
                                        } else {
                                            null
                                        }
                                    if (nextRoute != null) {
                                        if (backQueue.any {it.destination.route == nextRoute}) {
                                            navController.popBackStack()
                                            navController.popBackStack()
                                            navController.navigate(currentRoute!!)
                                        }
                                        navController.navigate(nextRoute)
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
                startDestination = NavigationItem.Home.route
            ) {
                composable(NavigationItem.Home.route) {
                    val homeViewModel: HomeViewModel = hiltViewModel()
                    HomeCompose(
                        homeViewModel,
                        navController,
                        bottomBarHeight,
                        setUrlToWebPage
                    )
                }
                composable(NavigationItem.Favorites.route) {
                    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
                    FavoritesCompose(
                        favoritesViewModel,
                        navController,
                        bottomBarHeight,
                        setUrlToWebPage
                    )
                }
                composable(NavigationItem.Info.route) {
                    InfoCompose(
                        bottomBarHeight
                    )
                }
                composable(UIConstants.WEB_PAGE_FROM_HOME_ROUTE) {
                    WebPageCompose(
                        urlToWebPage,
                        bottomBarHeight
                    )
                }
                composable(UIConstants.WEB_PAGE_FROM_FAVORITES_ROUTE) {
                    WebPageCompose(
                        urlToWebPage,
                        bottomBarHeight
                    )
                }
            }
        }
    }
}