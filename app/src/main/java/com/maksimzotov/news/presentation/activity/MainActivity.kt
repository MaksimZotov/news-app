package com.maksimzotov.news.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maksimzotov.news.R
import com.maksimzotov.news.presentation.entities.NavigationItem
import com.maksimzotov.news.presentation.screens.favorites.Favorites
import com.maksimzotov.news.presentation.screens.favorites.FavoritesViewModel
import com.maksimzotov.news.presentation.screens.home.Home
import com.maksimzotov.news.presentation.screens.home.HomeViewModel
import com.maksimzotov.news.presentation.screens.info.Info
import com.maksimzotov.news.presentation.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme by viewModel.darkTheme.observeAsState()
            val darkThemeIsAble = darkTheme?.isAble == true
            NewsTheme(
                darkTheme = darkThemeIsAble
            ) {
                Activity(viewModel)
            }
        }
    }
}

@Composable
fun Activity(viewModel: MainActivityViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()

        val bottomItems = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Info
        )
        
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "News App") },
                    actions = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_brightness_3_24),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable { viewModel.changeTheme() }
                        )
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    bottomItems.forEach { item ->
                        BottomNavigationItem(
                            selected = false,
                            onClick = { navController.navigate(item.route) },
                            label = { Text(item.title) },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
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
                    Home(homeViewModel, navController)
                }
                composable(NavigationItem.Favorites.route) {
                    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
                    Favorites(favoritesViewModel, navController)
                }
                composable(NavigationItem.Info.route) { Info() }
                composable("web_page") {
                    navController.previousBackStackEntry?.arguments?.getString("url")?.let {
                        WebPageScreen(it)
                    }
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(urlToRender: String) {
    AndroidView(factory = ::WebView) { webView ->
        with(webView) {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(urlToRender)
        }
    }
}