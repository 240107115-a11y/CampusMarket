package com.example.campusmarket.data.remote

data class RatingDto(
    val rate: Double,
    val count: Int
)

data class RemoteProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingDto?
)
