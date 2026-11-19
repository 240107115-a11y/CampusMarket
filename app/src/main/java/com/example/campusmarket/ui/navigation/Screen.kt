package com.example.campusmarket.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    object Detail : Screen("detail/{productId}") {
        fun createRoute(productId: Int): String = "detail/$productId"
    }
}
