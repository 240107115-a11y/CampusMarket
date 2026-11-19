package com.example.campusmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.campusmarket.ui.navigation.CampusNavGraph
import com.example.campusmarket.ui.theme.CampusMarketTheme
import com.example.campusmarket.ui.viewmodel.MarketViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusMarketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CampusMarketApp()
                }
            }
        }
    }
}

@Composable
fun CampusMarketApp() {
    val navController = rememberNavController()
    val viewModel: MarketViewModel = viewModel()
    CampusNavGraph(
        navController = navController,
        viewModel = viewModel
    )
}
