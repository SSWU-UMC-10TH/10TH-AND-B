package com.example.week04

data class Product(
    val name: String,
    val price: String,
    val imageRes: Int,
    var isLiked: Boolean = false
)