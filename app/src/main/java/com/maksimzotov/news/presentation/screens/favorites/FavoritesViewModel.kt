package com.maksimzotov.news.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.news.domain.entities.NewsItem
import com.maksimzotov.news.domain.usecases.GetFavoritesUseCase
import com.maksimzotov.news.domain.usecases.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {
    val favorites: Flow<List<NewsItem>?> = getFavoritesUseCase.getFavoriteNews()

    fun removeFromFavorites(newsItem: NewsItem) = viewModelScope.launch {
        removeFromFavoritesUseCase.removeFromFavorites(newsItem)
    }
}