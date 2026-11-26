package com.example.campusmarket.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface MarketApiService {

    @GET("products")
    suspend fun getProducts(): List<RemoteProductDto>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): List<RemoteProductDto>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): RemoteProductDto
}
