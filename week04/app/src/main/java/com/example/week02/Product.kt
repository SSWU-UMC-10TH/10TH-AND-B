package com.example.week02

data class Product(
    val id: Int,
    val imageResId: Int,
    val name: String,
    val price: String,
    var isWished: Boolean = false