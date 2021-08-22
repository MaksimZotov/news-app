package com.maksimzotov.news.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.maksimzotov.news.presentation.screens.main.MainCompose
import com.maksimzotov.news.presentation.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme by viewModel.darkTheme.collectAsState(null)
            val darkThemeIsAble = darkTheme?.isAble == true
            NewsTheme(darkTheme = darkThemeIsAble) {
                MainCompose(viewModel, darkThemeIsAble)
            }
        }
    }
}