package com.example.umc_week2.ui.wish

import com.example.umc_week2.ProductData

data class WishUiState(
    val wishList: List<ProductData> = emptyList(),
    val errorMessage: String? = null
)