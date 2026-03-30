package com.example.umc_week2

data class ProductData(
    val imageResId: Int,
    val name: String,
    val category: String,
    val subInfo: String,
    val price: String,
    val isBestSeller: Boolean = false,
    val isLiked: Boolean = false,
    val showWishIcon: Boolean = true
)