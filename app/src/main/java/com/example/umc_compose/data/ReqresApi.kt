package com.example.umc_compose.data

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
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 6
    ): UserListResponse
}