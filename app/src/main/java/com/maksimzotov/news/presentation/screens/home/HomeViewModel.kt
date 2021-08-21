package com.maksimzotov.news.presentation.screens.home

import androidx.lifecycle.*
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.domain.entities.NewsWrapper
import com.maksimzotov.news.domain.usecases.AddToFavoritesUseCase
import com.maksimzotov.news.domain.usecases.GetFavoritesUseCase
import com.maksimzotov.news.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    private val _news = MutableLiveData<Response<NewsWrapper>>()
    val news: LiveData<Response<NewsWrapper>> = _news

    val favorites: LiveData<List<NewsItem>?> = getFavoritesUseCase.getFavoriteNews().asLiveData()

    fun getNews() = viewModelScope.launch {
        _news.value = getNewsUseCase.getNews()
    }

    fun addToFavorites(newsItem: NewsItem) = viewModelScope.launch {
        addToFavoritesUseCase.addToFavorites(newsItem)
    }
}