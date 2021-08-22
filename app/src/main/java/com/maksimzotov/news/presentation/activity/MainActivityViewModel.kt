package com.maksimzotov.news.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimzotov.news.data.android.AndroidSettingsRepository
import com.maksimzotov.news.presentation.entities.DarkTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val androidSettingsRepository: AndroidSettingsRepository
) : ViewModel() {
    val darkTheme: Flow<DarkTheme?> = androidSettingsRepository.darkTheme

    fun changeTheme(darkThemeIsAble: Boolean) = viewModelScope.launch {
        androidSettingsRepository.setDarkTheme(DarkTheme(darkThemeIsAble))
    }
}