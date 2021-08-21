package com.maksimzotov.news.presentation.screens.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.maksimzotov.news.presentation.screens.home.NewsItemCompose
import com.maksimzotov.news.presentation.screens.info.InfoViewModel

@Composable
fun Favorites(
    viewModel: FavoritesViewModel,
    navController: NavController
) {
    val favorites by viewModel.favorites.observeAsState()
    if (favorites != null) {
        LazyColumn {
            items(favorites!!) { newsItem ->
                NewsItemCompose(
                    newsItem,
                    viewModel,
                    navController
                )
            }
        }
    }
}