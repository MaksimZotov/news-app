package com.maksimzotov.news.domain.usecases

import com.maksimzotov.news.domain.Repository
import com.maksimzotov.news.domain.entities.NewsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: Repository,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend fun getNews(): Response<List<NewsItem>> = withContext(coroutineDispatcher) {
        repository.getNews()
    }
}