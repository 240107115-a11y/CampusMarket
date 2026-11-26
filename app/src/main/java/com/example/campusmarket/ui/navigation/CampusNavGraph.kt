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
import com.example.campusmarket.ui.screens.MyListingsScreen
import com.example.campusmarket.ui.screens.OnlineMarketScreen
import com.example.campusmarket.ui.viewmodel.MarketViewModel
import com.example.campusmarket.ui.viewmodel.MyListingsViewModel
import com.example.campusmarket.ui.viewmodel.OnlineMarketViewModel

@Composable
fun CampusNavGraph(
    navController: NavHostController,
    marketViewModel: MarketViewModel,
    onlineViewModel: OnlineMarketViewModel,
    myListingsViewModel: MyListingsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by marketViewModel.uiState.collectAsState()
    val onlineUiState by onlineViewModel.uiState.collectAsState()
    val onlineSearchQuery by onlineViewModel.searchQuery.collectAsState()
    val localListings by myListingsViewModel.listings.collectAsState()
    val isDarkTheme by myListingsViewModel.isDarkTheme.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                uiState = uiState,
                onSearchQueryChanged = marketViewModel::onSearchQueryChanged,
                onCategorySelected = marketViewModel::onCategorySelected,
                onProductClick = { productId ->
                    navController.navigate(Screen.Detail.createRoute(productId))
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                },
                onToggleFavorite = marketViewModel::toggleFavorite
            )
        }

        composable(Screen.Online.route) {
            OnlineMarketScreen(
                uiState = onlineUiState,
                searchQuery = onlineSearchQuery,
                onSearchQueryChanged = onlineViewModel::onSearchQueryChanged,
                onRetry = onlineViewModel::retry
            )
        }

        composable(Screen.MyListings.route) {
            MyListingsScreen(
                listings = localListings,
                isDarkTheme = isDarkTheme,
                onDarkThemeChange = myListingsViewModel::setDarkTheme,
                onAddListing = myListingsViewModel::addListing,
                onUpdateListing = myListingsViewModel::updateListing,
                onDeleteListing = myListingsViewModel::deleteListing
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
                onToggleFavorite = { marketViewModel.toggleFavorite(productId) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                favoriteProducts = marketViewModel.getFavorites(),
                onProductClick = { productId ->
                    navController.navigate(Screen.Detail.createRoute(productId))
                },
                onBackClick = { navController.popBackStack() },
                onToggleFavorite = marketViewModel::toggleFavorite
            )
        }
    }
}
