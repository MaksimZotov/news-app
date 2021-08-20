package com.maksimzotov.news.data.main

import com.maksimzotov.news.data.main.retrofit.NewsApi
import com.maksimzotov.news.data.main.room.MainDao
import com.maksimzotov.news.domain.Repository
import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val mainDao: MainDao,
    private val newsApi: NewsApi
) : Repository {

    override suspend fun getNews(): Response<List<NewsItem>> =
        newsApi.getNews()


    override fun getFavoriteNews(): Flow<List<NewsItem>?> =
        mainDao.getFavoriteNews()

    override fun addNewsItemToFavorites(newsItem: NewsItem) =
        mainDao.addNewsItemToFavorites(newsItem)

    override fun removeNewsItemFromFavorites(newsItem: NewsItem) =
        mainDao.removeNewsItemFromFavorites(newsItem)
}