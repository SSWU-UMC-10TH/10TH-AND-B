package com.example.umc_compose.data

import com.example.umc_compose.R

val homeProducts = listOf(
    ProductData(
        id = 101,
        imageResId = R.drawable.main_shoes1,
        name = "Air Jordan XXXVI",
        category = "",
        subInfo = "",
        price = "US$185",
        showWishIcon = false
    ),
    ProductData(
        id = 102,
        imageResId = R.drawable.main_shoes2,
        name = "Nike Air Force 1 '07",
        category = "",
        subInfo = "",
        price = "US$115",
        showWishIcon = false
    )
)

val initialBuyProducts = listOf(
    ProductData(
        id = 1,
        imageResId = R.drawable.socks1,
        name = "Nike Everyday Plus Cushioned",
        category = "Training Ankle Socks (6 Pairs)",
        subInfo = "5 Colours",
        price = "US$10",
        isLiked = false
    ),
    ProductData(
        id = 2,
        imageResId = R.drawable.socks2,
        name = "Nike Elite Crew",
        category = "Basketball Socks",
        subInfo = "7 Colours",
        price = "US$16",
        isLiked = false
    ),
    ProductData(
        id = 3,
        imageResId = R.drawable.shoes1,
        name = "Nike Air Force 1 '07",
        category = "Women's Shoes",
        subInfo = "5 Colours",
        price = "US$115",
        isBestSeller = true,
        isLiked = false
    ),
    ProductData(
        id = 4,
        imageResId = R.drawable.shoes2,
        name = "Jordan Nike Air Force 1 '07 Essentials",
        category = "Men's Shoes",
        subInfo = "2 Colours",
        price = "US$115",
        isBestSeller = true,
        isLiked = false
    )
)