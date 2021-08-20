package com.maksimzotov.news.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _news = MutableLiveData<Response<List<NewsItem>>>()
    val news: LiveData<Response<List<NewsItem>>> = _news

    private val _newsStub = MutableLiveData<List<NewsItemStub>>()
    val newsStub: LiveData<List<NewsItemStub>> = _newsStub

    fun getNews() {
        _newsStub.value = SampleData.dataStub
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