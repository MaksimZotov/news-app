package com.maksimzotov.news.data.android

import com.maksimzotov.news.presentation.entities.DarkTheme
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidSettingsRepository @Inject constructor(
    private val androidSettingsDao: AndroidSettingsDao,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    val darkTheme: Flow<DarkTheme?> = androidSettingsDao.getDarkTheme()

    suspend fun setDarkTheme(darkTheme: DarkTheme) = withContext(coroutineDispatcher) {
        androidSettingsDao.setDarkTheme(darkTheme)
    }
}