package com.example.campusmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.campusmarket.data.model.Product
import com.example.campusmarket.data.model.ProductCategory
import com.example.campusmarket.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MarketViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MarketUiState())
    val uiState: StateFlow<MarketUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val allProducts = ProductRepository.sampleProducts
        _uiState.update { currentState ->
            currentState.copy(products = allProducts)
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { currentState ->
            currentState.copy(searchQuery = newQuery)
        }
        applyFilters()
    }

    fun onCategorySelected(category: ProductCategory) {
        _uiState.update { currentState ->
            currentState.copy(selectedCategory = category)
        }
        applyFilters()
    }

    private fun applyFilters() {
        val all = ProductRepository.sampleProducts
        val query = _uiState.value.searchQuery.trim().lowercase()
        val cat = _uiState.value.selectedCategory

        val filtered = all.filter { product ->
            val matchesCategory = (cat == ProductCategory.ALL) || (product.category == cat)
            val matchesSearch = query.isEmpty() ||
                product.title.lowercase().contains(query) ||
                product.description.lowercase().contains(query)
            matchesCategory && matchesSearch
        }

        _uiState.update { it.copy(products = filtered) }
    }

    fun toggleFavorite(productId: Int) {
        _uiState.update { currentState ->
            val updated = currentState.favoriteProductIds.toMutableSet()
            if (updated.contains(productId)) {
                updated.remove(productId)
            } else {
                updated.add(productId)
            }
            currentState.copy(favoriteProductIds = updated)
        }
    }

    fun getFavorites(): List<Product> {
        val favIds = _uiState.value.favoriteProductIds
        return ProductRepository.sampleProducts.filter { favIds.contains(it.id) }
    }
}
