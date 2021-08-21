package com.maksimzotov.news.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maksimzotov.news.data.android.AndroidSettingsRepository
import com.maksimzotov.news.presentation.entities.DarkTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val androidSettingsRepository: AndroidSettingsRepository
) : ViewModel() {
    val darkTheme: LiveData<DarkTheme?> = androidSettingsRepository.darkTheme.asLiveData()

    fun changeTheme() = viewModelScope.launch {
        val isAble = darkTheme.value?.isAble == true
        androidSettingsRepository.setDarkTheme(DarkTheme(!isAble))
    }
}