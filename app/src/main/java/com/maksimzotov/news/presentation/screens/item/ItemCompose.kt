package com.maksimzotov.news.presentation.screens.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.presentation.UIConstants
import com.maksimzotov.news.presentation.screens.favorites.FavoritesViewModel
import com.maksimzotov.news.presentation.screens.home.HomeViewModel

@Composable
fun NewsItemCompose(
    newsItem: NewsItem,
    viewModel: ViewModel,
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(6.dp)
            .clickable {
                navController.also { nc ->
                    nc.currentBackStackEntry?.arguments =
                        (nc.currentBackStackEntry?.arguments ?: bundleOf()).apply {
                            putAll(bundleOf(UIConstants.URL_KEY to newsItem.url))
                        }
                    nc.navigate(UIConstants.WEB_PAGE_ROUTE)
                }
            }
            .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.small)
    ) {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = newsItem.urlToImage,
                    builder = {
                        crossfade(true)
                        crossfade(1000)
                    },
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(152.dp)
                    .border(1.5.dp, MaterialTheme.colors.secondary, MaterialTheme.shapes.large)

            )
            Spacer(modifier = Modifier.height(8.dp))

            var isExpanded by remember { mutableStateOf(false) }

            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )

            Column(modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = newsItem.title,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    when(viewModel) {
                        is HomeViewModel -> viewModel.addToFavorites(newsItem)
                        is FavoritesViewModel -> viewModel.removeFromFavorites(newsItem)
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                val text = when (viewModel) {
                    is HomeViewModel -> {
                        val favorites by viewModel.favorites.observeAsState()
                        if (favorites?.contains(newsItem) == true) {
                            "Added"
                        } else {
                            "Add"
                        }
                    }
                    is FavoritesViewModel -> "Remove"
                    else -> "Oops!"
                }
                Text(text)
            }
        }
    }
}