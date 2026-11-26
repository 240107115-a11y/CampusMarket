package com.example.campusmarket.ui.viewmodel

import com.example.campusmarket.data.remote.RemoteProductDto

sealed interface OnlineMarketUiState {
    object Loading : OnlineMarketUiState
    data class Success(val products: List<RemoteProductDto>) : OnlineMarketUiState
    data class Error(val message: String) : OnlineMarketUiState
    object Empty : OnlineMarketUiState
}
