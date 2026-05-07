package com.example.umc_week2.data.repository

import android.content.Context
import com.example.umc_week2.ProductData
import com.example.umc_week2.ProductDataStore
import com.example.umc_week2.domain.repository.LocalRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalRepository {

    override fun getHomeProducts(): Flow<List<ProductData>> {
        return ProductDataStore.getHomeProducts(context)
    }

    override suspend fun saveHomeProducts(productList: List<ProductData>) {
        ProductDataStore.saveHomeProducts(context, productList)
    }

    override fun getBuyProducts(): Flow<List<ProductData>> {
        return ProductDataStore.getBuyProducts(context)
    }

    override suspend fun saveBuyProducts(productList: List<ProductData>) {
        ProductDataStore.saveBuyProducts(context, productList)
    }
}