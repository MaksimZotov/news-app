package com.maksimzotov.news.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.maksimzotov.news.R
import com.maksimzotov.news.presentation.screens.item.NewsItemCompose

@Composable
fun HomeCompose(
    viewModel: HomeViewModel,
    navController: NavController,
    bottomBarHeight: Dp
) {
    val news by viewModel.news.observeAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Refresh, null) },
                onClick = { viewModel.getNews() },
                text = { Text(stringResource(R.string.update))}
            )
        },
        modifier = Modifier.padding(bottom = bottomBarHeight)
    ) {
        if (news?.isSuccessful == true) {
            val newsWrapper = news!!.body()
            if (newsWrapper != null) {
                val newsList = newsWrapper.news
                LazyColumn() {
                    items(newsList) { newsItem ->
                        NewsItemCompose(
                            newsItem,
                            viewModel,
                            navController
                        )
                    }
                }
            }
        }
    }
}