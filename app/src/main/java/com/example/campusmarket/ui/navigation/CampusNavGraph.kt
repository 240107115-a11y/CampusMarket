package com.example.campusmarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.campusmarket.ui.screens.DetailScreen
import com.example.campusmarket.ui.screens.FavoritesScreen
import com.example.campusmarket.ui.screens.HomeScreen
import com.example.campusmarket.ui.viewmodel.MarketViewModel

@Composable
fun CampusNavGraph(
    navController: NavHostController,
    viewModel: MarketViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                uiState = uiState,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onCategorySelected = viewModel::onCategorySelected,
                onProductClick = { productId ->
                    navController.navigate(Screen.Detail.createRoute(productId))
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                },
                onToggleFavorite = viewModel::toggleFavorite
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            DetailScreen(
                productId = productId,
                isFavorite = uiState.favoriteProductIds.contains(productId),
                onToggleFavorite = { viewModel.toggleFavorite(productId) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                favoriteProducts = viewModel.getFavorites(),
                onProductClick = { productId ->
                    navController.navigate(Screen.Detail.createRoute(productId))
                },
                onBackClick = { navController.popBackStack() },
                onToggleFavorite = viewModel::toggleFavorite
            )
        }
    }
}
