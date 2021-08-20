package com.maksimzotov.news.data.main.retrofit

import com.maksimzotov.news.domain.entities.NewsItem
import retrofit2.Response

interface NewsApi {
    suspend fun getNews(): Response<List<NewsItem>>
}