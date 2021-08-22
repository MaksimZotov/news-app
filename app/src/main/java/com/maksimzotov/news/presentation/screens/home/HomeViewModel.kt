package com.maksimzotov.news.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.domain.entities.NewsWrapper
import com.maksimzotov.news.domain.usecases.AddToFavoritesUseCase
import com.maksimzotov.news.domain.usecases.GetFavoritesUseCase
import com.maksimzotov.news.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    private val _news: MutableState<Response<NewsWrapper>?> = mutableStateOf(null)
    val news: State<Response<NewsWrapper>?> = _news

    val favorites: Flow<List<NewsItem>?> = getFavoritesUseCase.getFavoriteNews()

    fun getNews() = viewModelScope.launch {
        _news.value = getNewsUseCase.getNews()
    }

    fun addToFavorites(newsItem: NewsItem) = viewModelScope.launch {
        addToFavoritesUseCase.addToFavorites(newsItem)
    }
}