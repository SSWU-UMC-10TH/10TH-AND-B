package com.example.umc_week2.ui.mypage

import com.example.umc_week2.data.model.UserData

data class MyPageUiState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val profileImageUrl: String = "",
    val followingList: List<UserData> = emptyList(),
    val errorMessage: String? = null
)