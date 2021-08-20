package com.maksimzotov.news.domain.usecases

import com.maksimzotov.news.domain.Repository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: Repository
) {
    fun getFavoriteNews() = repository.getFavoriteNews()
}