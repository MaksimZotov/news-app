package com.maksimzotov.news.data.main.retrofit

import com.maksimzotov.news.domain.entities.NewsItem
import retrofit2.Response

interface NewsApi {
    suspend fun getNews(): Response<List<NewsItem>>
    suspend fun getSportsNews(): Response<List<NewsItem>>
    suspend fun getHealthNews(): Response<List<NewsItem>>
    suspend fun getScienceNews(): Response<List<NewsItem>>
    suspend fun getTechnologyNews(): Response<List<NewsItem>>
}