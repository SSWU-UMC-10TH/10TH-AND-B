package com.example.umc_week2.ui.buy

import com.example.umc_week2.ProductData

data class BuyUiState(
    val productList: List<ProductData> = emptyList(),
    val errorMessage: String? = null
)