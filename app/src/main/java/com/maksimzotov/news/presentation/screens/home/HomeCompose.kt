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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

data class NewsItemStub(
    val author: String,
    val body: String,
    val url: String
)

@Composable
fun Home(
    news: List<NewsItemStub> = SampleData.dataStub
) {
    LazyColumn {
        items(news) { newsItemStub ->
            NewsItemStub(newsItemStub)
        }
    }
}

@Composable
fun NewsItemStub(newsStub: NewsItemStub) {
    Surface {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = rememberImagePainter(
                    data = newsStub.url,
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
                Text(
                    text = newsStub.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = newsStub.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

object SampleData {

    val dataStub = listOf(
        NewsItemStub(
            "Colleague",
            "Test...Test...Test...",
            "https://picsum.photos/150/?random=1"
        ),
        NewsItemStub(
            "Colleague",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n",
            "https://picsum.photos/150/?random=2"
        ),
        NewsItemStub(
            "Colleague",
            "I think Kotlin is my favorite programming language.\n" +
                    "It's so much fun!",
            "https://picsum.photos/150/?random=3"
        ),
        NewsItemStub(
            "Colleague",
            "Searching for alternatives to XML layouts...",
            "https://picsum.photos/150/?random=4"
        ),
        NewsItemStub(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "It's the Android's modern toolkit for building native UI." +
                    "It simplifies and accelerates UI development on Android." +
                    "Less code, powerful tools, and intuitive Kotlin APIs :)",
            "https://picsum.photos/150/?random=5"
        ),
        NewsItemStub(
            "Colleague",
            "It's available from API 21+ :)",
            "https://picsum.photos/150/?random=6"
        ),
        NewsItemStub(
            "Colleague",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?",
            "https://picsum.photos/150/?random=7"
        ),
        NewsItemStub(
            "Colleague",
            "Android Studio next version's name is Arctic Fox",
            "https://picsum.photos/150/?random=8"
        ),
        NewsItemStub(
            "Colleague",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^",
            "https://picsum.photos/150/?random=9"
        ),
        NewsItemStub(
            "Colleague",
            "I didn't know you can now run the emulator directly from Android Studio",
            "https://picsum.photos/150/?random=10"
        ),
        NewsItemStub(
            "Colleague",
            "Compose Previews are great to check quickly how a composable layout looks like",
            "https://picsum.photos/150/?random=11"
        ),
        NewsItemStub(
            "Colleague",
            "Previews are also interactive after enabling the experimental setting",
            "https://picsum.photos/150/?random=12"
        ),
        NewsItemStub(
            "Colleague",
            "Have you tried writing build.gradle with KTS?",
            "https://picsum.photos/150/?random=13"
        ),
    )
}