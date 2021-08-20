package com.maksimzotov.news.domain

import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getNews(): Response<List<NewsItem>>

    fun getFavoriteNews(): Flow<List<NewsItem>?>
    fun addNewsItemToFavorites(newsItem: NewsItem)
    fun removeNewsItemFromFavorites(newsItem: NewsItem)
}