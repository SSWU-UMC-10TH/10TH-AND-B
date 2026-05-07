package com.example.umc_week2.data.repository

import com.example.umc_week2.data.model.UserData
import com.example.umc_week2.data.remote.ReqresApi
import com.example.umc_week2.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: ReqresApi
) : RemoteRepository {

    override suspend fun getUser(id: Int): UserData {
        return api.getUser(id).data
    }

    override suspend fun getUsers(page: Int): List<UserData> {
        return api.getUsers(page).data
    }
}