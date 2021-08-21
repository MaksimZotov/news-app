package com.maksimzotov.news.presentation.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.maksimzotov.news.domain.entities.NewsItem

@Composable
fun Home(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val news by viewModel.news.observeAsState()

    Column {
        Button(onClick = { viewModel.getNews() }) {
            Text(text = "Get News")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (news?.isSuccessful == true) {
            val newsWrapper = news!!.body()
            if (newsWrapper != null) {
                val newsList = newsWrapper.news
                LazyColumn {
                    items(newsList) { newsItem ->
                        NewsItem(newsItem, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItem(newsItem: NewsItem, navController: NavController) {
    Surface(
        modifier = Modifier.clickable {
            navController.also { nc ->
                nc.currentBackStackEntry?.arguments =
                    (nc.currentBackStackEntry?.arguments ?: bundleOf()).apply {
                        putAll(bundleOf("url" to newsItem.url))
                    }
                nc.navigate("web_page")
            }
        }
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = newsItem.urlToImage,
                    builder = {
                        crossfade(true)
                        crossfade(1000)
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

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
        }
    }
}