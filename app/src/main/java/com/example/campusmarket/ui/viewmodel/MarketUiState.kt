package com.example.campusmarket.ui.viewmodel

import com.example.campusmarket.data.model.Product
import com.example.campusmarket.data.model.ProductCategory

data class MarketUiState(
    val products: List<Product> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: ProductCategory = ProductCategory.ALL,
    val favoriteProductIds: Set<Int> = emptySet(),
    val selectedProduct: Product? = null
)
