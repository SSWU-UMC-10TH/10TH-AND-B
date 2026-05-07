package com.example.umc_week2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.umc_week2.ProductData
import com.example.umc_week2.R
import com.example.umc_week2.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadHomeProducts()
    }

    private fun loadHomeProducts() {
        viewModelScope.launch {
            try {
                val defaultHomeProducts = listOf(
                    ProductData(
                        imageResId = R.drawable.main_shoes1,
                        name = "Air Jordan XXXVI",
                        category = "",
                        subInfo = "",
                        price = "US$185",
                        isBestSeller = false,
                        isLiked = false,
                        showWishIcon = false
                    ),
                    ProductData(
                        imageResId = R.drawable.main_shoes2,
                        name = "Nike Air Force 1 '07",
                        category = "",
                        subInfo = "",
                        price = "US$115",
                        isBestSeller = false,
                        isLiked = false,
                        showWishIcon = false
                    )
                )

                val savedHomeProducts = localRepository.getHomeProducts().first()

                if (savedHomeProducts.isEmpty()) {
                    localRepository.saveHomeProducts(defaultHomeProducts)
                }

                localRepository.getHomeProducts().collect { products ->
                    _uiState.update {
                        it.copy(productList = products)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "홈 상품을 불러오지 못했습니다.")
                }
            }
        }
    }
}