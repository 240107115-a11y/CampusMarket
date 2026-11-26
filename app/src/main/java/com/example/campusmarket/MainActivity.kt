package com.example.campusmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.campusmarket.ui.navigation.CampusNavGraph
import com.example.campusmarket.ui.navigation.Screen
import com.example.campusmarket.ui.theme.CampusMarketTheme
import com.example.campusmarket.ui.viewmodel.MarketViewModel
import com.example.campusmarket.ui.viewmodel.MyListingsViewModel
import com.example.campusmarket.ui.viewmodel.OnlineMarketViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myListingsViewModel: MyListingsViewModel = viewModel()
            val isDarkTheme by myListingsViewModel.isDarkTheme.collectAsState()

            CampusMarketTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CampusMarketApp(myListingsViewModel = myListingsViewModel)
                }
            }
        }
    }
}

@Composable
fun CampusMarketApp(myListingsViewModel: MyListingsViewModel) {
    val navController = rememberNavController()
    val marketViewModel: MarketViewModel = viewModel()
    val onlineViewModel: OnlineMarketViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.Home.route,
        Screen.Online.route,
        Screen.MyListings.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Screen.Home.route,
                        onClick = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Campus"
                            )
                        },
                        label = { Text("Campus") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.Online.route,
                        onClick = {
                            navController.navigate(Screen.Online.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Online Store"
                            )
                        },
                        label = { Text("Online") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.MyListings.route,
                        onClick = {
                            navController.navigate(Screen.MyListings.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "My Listings"
                            )
                        },
                        label = { Text("My Listings") }
                    )
                }
            }
        }
    ) { innerPadding ->
        CampusNavGraph(
            navController = navController,
            marketViewModel = marketViewModel,
            onlineViewModel = onlineViewModel,
            myListingsViewModel = myListingsViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
