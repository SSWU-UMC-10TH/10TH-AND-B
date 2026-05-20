package com.example.umc_week2.ui.wish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umc_week2.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(WishUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadWishProducts()
    }

    private fun loadWishProducts() {
        viewModelScope.launch {
            try {
                localRepository.getBuyProducts().collect { allProducts ->
                    val wishList = allProducts
                        .filter { it.isLiked }
                        .map {
                            it.copy(showWishIcon = false)
                        }

                    _uiState.update {
                        it.copy(wishList = wishList)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "위시 상품을 불러오지 못했습니다.")
                }
            }
        }
    }
}