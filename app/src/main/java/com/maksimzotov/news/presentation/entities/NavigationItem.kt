package com.maksimzotov.news.presentation.entities

import com.maksimzotov.news.R

sealed class NavigationItem(var route: String, var icon: Int, var title: Int) {
    object Home :
        NavigationItem("home", R.drawable.ic_baseline_home_24, R.string.home)

    object Favorites :
        NavigationItem("favorites", R.drawable.ic_baseline_favorite_24, R.string.favorites)

    object Info :
        NavigationItem("info", R.drawable.ic_baseline_info_24, R.string.info)
}