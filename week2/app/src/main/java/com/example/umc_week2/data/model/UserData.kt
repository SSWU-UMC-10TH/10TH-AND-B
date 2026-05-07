package com.example.umc_week2.data.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val id: Int,
    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String
)