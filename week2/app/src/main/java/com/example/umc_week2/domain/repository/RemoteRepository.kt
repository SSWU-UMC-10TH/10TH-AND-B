package com.example.umc_week2.domain.repository

import com.example.umc_week2.data.model.UserData

interface RemoteRepository {
    suspend fun getUser(id: Int): UserData
    suspend fun getUsers(page: Int): List<UserData>
}