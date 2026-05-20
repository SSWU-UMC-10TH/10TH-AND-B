package com.example.umc_week2.ui.home

import com.example.umc_week2.ProductData

data class HomeUiState(
    val productList: List<ProductData> = emptyList(),
    val errorMessage: String? = null
)