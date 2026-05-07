package com.example.umc_week2.ui.buy

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
class BuyViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BuyUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadBuyProducts()
    }

    private fun loadBuyProducts() {
        viewModelScope.launch {
            try {
                val defaultProducts = listOf(
                    ProductData(
                        R.drawable.socks1,
                        "Nike Everyday Plus Cushioned",
                        "Training Ankle Socks (6 Pairs)",
                        "5 Colours",
                        "US$10",
                        false,
                        true,
                        true
                    ),
                    ProductData(
                        R.drawable.socks2,
                        "Nike Elite Crew",
                        "Basketball Socks",
                        "7 Colours",
                        "US$16",
                        false,
                        false,
                        true
                    ),
                    ProductData(
                        R.drawable.shoes1,
                        "Nike Air Force 1 '07",
                        "Women's Shoes",
                        "5 Colours",
                        "US$115",
                        true,
                        false,
                        true
                    ),
                    ProductData(
                        R.drawable.shoes2,
                        "Jordan E Nike Air Force 1 '07ssentials",
                        "Men's Shoes",
                        "2 Colours",
                        "US$115",
                        true,
                        false,
                        true
                    )
                )

                val savedProducts = localRepository.getBuyProducts().first()

                if (savedProducts.isEmpty()) {
                    localRepository.saveBuyProducts(defaultProducts)
                }

                localRepository.getBuyProducts().collect { products ->
                    _uiState.update {
                        it.copy(productList = products)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "구매 상품을 불러오지 못했습니다.")
                }
            }
        }
    }

    fun toggleLike(position: Int) {
        val currentList = _uiState.value.productList.toMutableList()

        if (position !in currentList.indices) return

        currentList[position] = currentList[position].copy(
            isLiked = !currentList[position].isLiked
        )

        viewModelScope.launch {
            localRepository.saveBuyProducts(currentList)
            _uiState.update {
                it.copy(productList = currentList)
            }
        }
    }
}