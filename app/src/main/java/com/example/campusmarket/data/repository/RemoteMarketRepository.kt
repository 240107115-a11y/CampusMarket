package com.example.campusmarket.data.repository

import com.example.campusmarket.data.remote.MarketApiService
import com.example.campusmarket.data.remote.RemoteProductDto
import com.example.campusmarket.data.remote.RetrofitClient

class RemoteMarketRepository(
    private val apiService: MarketApiService = RetrofitClient.apiService
) {

    suspend fun getProducts(): Result<List<RemoteProductDto>> {
        return try {
            val products = apiService.getProducts()
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: Int): Result<RemoteProductDto> {
        return try {
            val product = apiService.getProductById(id)
            Result.success(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
