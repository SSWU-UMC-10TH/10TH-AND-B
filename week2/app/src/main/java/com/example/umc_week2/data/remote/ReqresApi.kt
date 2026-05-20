package com.example.umc_week2.data.remote

import com.example.umc_week2.data.model.UserListResponse
import com.example.umc_week2.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresApi {

    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponse

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): UserListResponse
}