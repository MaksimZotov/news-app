package com.maksimzotov.news.domain.usecases

import com.maksimzotov.news.domain.Repository
import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkWithFavoriteNewsUseCases @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    fun getFavoriteNews() = repository.getFavoriteNews()

    suspend fun addNewsItemToFavorites(newsItem: NewsItem) = withContext(coroutineDispatcher) {
        repository.addNewsItemToFavorites(newsItem)
    }

    suspend fun removeNewsItemFromFavorites(newsItem: NewsItem) = withContext(coroutineDispatcher) {
        repository.removeNewsItemFromFavorites(newsItem)
    }
}