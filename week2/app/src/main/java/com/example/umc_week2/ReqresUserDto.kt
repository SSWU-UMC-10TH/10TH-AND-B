package com.example.umc_week2

import com.google.gson.annotations.SerializedName

data class SingleUserResponse(
    val data: ReqresUser
)

data class UserListResponse(
    val page: Int,
    val data: List<ReqresUser>
)

data class ReqresUser(
    val id: Int,
    val email: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    val avatar: String
)