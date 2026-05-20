package com.example.umc_week2.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umc_week2.domain.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    fun loadMyPage() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {
                val user = remoteRepository.getUser(1)
                val followingList = remoteRepository.getUsers(1)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        userName = "${user.firstName} ${user.lastName}",
                        profileImageUrl = user.avatar,
                        followingList = followingList
                    )
                }
            } catch (e: Exception) {
                Log.e("MyPageViewModel", "loadMyPage error", e)

                val errorText = when (e) {
                    is HttpException -> {
                        "HTTP ${e.code()} 오류: ${e.message()}"
                    }
                    else -> {
                        "${e.javaClass.simpleName}: ${e.message}"
                    }
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = errorText
                    )
                }
            }
        }
    }
}