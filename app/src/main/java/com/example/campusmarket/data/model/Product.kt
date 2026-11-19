package com.example.campusmarket.data.model

import androidx.annotation.DrawableRes

enum class ProductCategory(val displayName: String) {
    ALL("All"),
    TEXTBOOKS("Textbooks"),
    ELECTRONICS("Electronics"),
    DORM("Dorm"),
    SUPPLIES("Supplies")
}

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val category: ProductCategory,
    val condition: String,
    val sellerName: String,
    val sellerContact: String,
    val location: String,
    val description: String,
    val imageRes: Int
)
