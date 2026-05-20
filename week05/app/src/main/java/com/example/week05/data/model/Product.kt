package com.example.week05.data.model

data class Product(
    val name: String,
    val price: String,
    val imageRes: Int,
    var isLiked: Boolean = false
)