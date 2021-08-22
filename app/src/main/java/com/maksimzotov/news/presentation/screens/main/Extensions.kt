package com.maksimzotov.news.presentation.screens.main

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun ArrayDeque<NavBackStackEntry>.contains(route: String) = any {
    it.destination.route == route
}

fun NavController.popBackStack(route: String) = popBackStack(route, false)