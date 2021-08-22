package com.maksimzotov.news.presentation.screens.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.maksimzotov.news.presentation.screens.item.NewsItemCompose

@Composable
fun FavoritesCompose(
    viewModel: FavoritesViewModel,
    navController: NavController,
    bottomBarHeight: Dp
) {
    val favorites by viewModel.favorites.observeAsState()
    if (favorites != null) {
        LazyColumn(
            modifier = Modifier.padding(bottom = bottomBarHeight)
        ) {
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