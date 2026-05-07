package com.example.week02

import retrofit2.Response
import retrofit2.http.GET

interface ProfileApi {
    @GET("api/users/1")
    suspend fun getMyProfile(): Response<SingleUserResponse>

    @GET("api/users?page=1")
    suspend fun getUserList(): Response<UserListResponse>
}