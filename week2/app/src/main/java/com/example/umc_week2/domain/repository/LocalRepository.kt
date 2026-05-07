package com.example.umc_week2.domain.repository

import com.example.umc_week2.ProductData
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getHomeProducts(): Flow<List<ProductData>>
    suspend fun saveHomeProducts(productList: List<ProductData>)

    fun getBuyProducts(): Flow<List<ProductData>>
    suspend fun saveBuyProducts(productList: List<ProductData>)
}