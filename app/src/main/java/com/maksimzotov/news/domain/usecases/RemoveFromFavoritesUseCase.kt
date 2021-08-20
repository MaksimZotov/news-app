package com.maksimzotov.news.domain.usecases

import com.maksimzotov.news.domain.Repository
import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun removeFromFavorites(newsItem: NewsItem) = withContext(coroutineDispatcher) {
        repository.removeNewsItemFromFavorites(newsItem)
    }
}