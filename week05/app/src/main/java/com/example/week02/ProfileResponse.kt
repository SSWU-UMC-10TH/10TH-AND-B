package com.example.week02

import com.google.gson.annotations.SerializedName

data class SingleUserResponse(
    val data: ReqResUser
)

data class UserListResponse(
    val data: List<ReqResUser>
)

data class ReqResUser(
    val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)