package com.maksimzotov.news.presentation.screens.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.maksimzotov.news.R

@Composable
fun InfoCompose(bottomBarHeight: Dp) {
    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = bottomBarHeight),
        contentAlignment = Alignment.Center
    ) {
        Text(stringResource(R.string.some_info))
    }
}