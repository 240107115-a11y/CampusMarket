package com.example.campusmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.campusmarket.data.remote.RemoteProductDto
import com.example.campusmarket.data.repository.RemoteMarketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnlineMarketViewModel(
    private val repository: RemoteMarketRepository = RemoteMarketRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<OnlineMarketUiState>(OnlineMarketUiState.Loading)
    val uiState: StateFlow<OnlineMarketUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var allRemoteProducts: List<RemoteProductDto> = emptyList()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = OnlineMarketUiState.Loading
            val result = repository.getProducts()
            result.onSuccess { products ->
                allRemoteProducts = products
                applyFilter()
            }.onFailure { error ->
                _uiState.value = OnlineMarketUiState.Error(
                    error.localizedMessage ?: "Failed to load products from network"
                )
            }
        }
    }

    fun retry() {
        loadProducts()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (allRemoteProducts.isNotEmpty()) {
            applyFilter()
        }
    }

    private fun applyFilter() {
        val query = _searchQuery.value.trim().lowercase()
        val filtered = if (query.isEmpty()) {
            allRemoteProducts
        } else {
            allRemoteProducts.filter {
                it.title.lowercase().contains(query) ||
                    it.category.lowercase().contains(query)
            }
        }

        _uiState.value = if (filtered.isEmpty()) {
            OnlineMarketUiState.Empty
        } else {
            OnlineMarketUiState.Success(filtered)
        }
    }
}
