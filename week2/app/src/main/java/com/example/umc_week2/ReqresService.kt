package com.example.umc_week2

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresService {

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): SingleUserResponse

    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): UserListResponse
}