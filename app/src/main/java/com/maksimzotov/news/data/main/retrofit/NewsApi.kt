package com.maksimzotov.news.data.main.retrofit

import com.maksimzotov.news.domain.entities.NewsWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "ru",
        @Query("apiKey") key: String = "f3d22a6242cc4b68aad52cf201088dc6"
    ): Response<NewsWrapper>
}