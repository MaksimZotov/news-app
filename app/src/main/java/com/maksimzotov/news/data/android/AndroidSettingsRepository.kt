package com.maksimzotov.news.data.android

import com.maksimzotov.news.presentation.entities.DarkTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidSettingsRepository @Inject constructor(
    private val androidSettingsDao: AndroidSettingsDao
) {
    val darkTheme: Flow<DarkTheme?> = androidSettingsDao.getDarkTheme()
    fun setDarkTheme(darkTheme: DarkTheme) = androidSettingsDao.setDarkTheme(darkTheme)
}