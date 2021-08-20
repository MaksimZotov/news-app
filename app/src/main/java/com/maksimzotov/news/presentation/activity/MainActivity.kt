package com.maksimzotov.news.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maksimzotov.news.presentation.entities.NavigationItem
import com.maksimzotov.news.presentation.screens.favorites.Favorites
import com.maksimzotov.news.presentation.screens.home.Home
import com.maksimzotov.news.presentation.screens.info.Info
import com.maksimzotov.news.presentation.theme.NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                Activity()
            }
        }
    }
}

@Composable
fun Activity() {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        val bottomItems = listOf(
            NavigationItem.Home,
            NavigationItem.Favorites,
            NavigationItem.Info
        )
        
        Scaffold(
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
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = "News App") }
                )
            }

        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.Home.route
            ) {
                composable(NavigationItem.Home.route) { Home() }
                composable(NavigationItem.Favorites.route) { Favorites() }
                composable(NavigationItem.Info.route) { Info() }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewsTheme {
        Activity()
    }
}