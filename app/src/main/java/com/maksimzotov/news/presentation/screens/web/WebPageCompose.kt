package com.maksimzotov.news.presentation.screens.web

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageCompose(
    urlToRender: String,
    bottomBarHeight: Dp
) {
    AndroidView(
        factory = ::WebView,
        modifier = Modifier.padding(bottom = bottomBarHeight)
    ) { webView ->
        with(webView) {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(urlToRender)
        }
    }
}