package com.maksimzotov.news.presentation.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.domain.entities.NewsWrapper
import com.maksimzotov.news.domain.usecases.AddToFavoritesUseCase
import com.maksimzotov.news.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase
) : ViewModel() {
    private val _news = MutableLiveData<Response<NewsWrapper>>()
    val news: LiveData<Response<NewsWrapper>> = _news

    fun getNews() = viewModelScope.launch {
        _news.value = getNewsUseCase.getNews()
    }

    fun addToFavorites(newsItem: NewsItem) = viewModelScope.launch {
        addToFavoritesUseCase.addToFavorites(newsItem)
    }
}