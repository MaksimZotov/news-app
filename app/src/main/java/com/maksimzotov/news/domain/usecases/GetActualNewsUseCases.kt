package com.maksimzotov.news.domain.usecases

import com.maksimzotov.news.domain.Repository
import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetActualNewsUseCases @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun getNews(): Response<List<NewsItem>> = withContext(coroutineDispatcher) {
        repository.getNews()
    }

    suspend fun getSportsNews(): Response<List<NewsItem>> = withContext(coroutineDispatcher) {
        repository.getSportsNews()
    }

    suspend fun getHealthNews(): Response<List<NewsItem>> = withContext(coroutineDispatcher) {
        repository.getHealthNews()
    }

    suspend fun getScienceNews(): Response<List<NewsItem>> = withContext(coroutineDispatcher) {
        repository.getScienceNews()
    }

    suspend fun getTechnologyNews(): Response<List<NewsItem>> = withContext(coroutineDispatcher) {
        repository.getTechnologyNews()
    }
}