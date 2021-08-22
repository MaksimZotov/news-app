package com.maksimzotov.news.presentation.entities

import com.maksimzotov.news.R

sealed class NavItem(var route: String, var icon: Int, var title: Int) {
    object Home :
        NavItem("home", R.drawable.ic_baseline_home_24, R.string.home)

    object Favorites :
        NavItem("favorites", R.drawable.ic_baseline_favorite_24, R.string.favorites)

    object Info :
        NavItem("info", R.drawable.ic_baseline_info_24, R.string.info)
}