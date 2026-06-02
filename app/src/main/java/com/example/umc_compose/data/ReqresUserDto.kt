package com.example.umc_compose.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val data: ReqresUserDto
)

data class UserListResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<ReqresUserDto>
)

data class ReqresUserDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
) {
    val fullName: String
        get() = "$firstName $lastName"
}