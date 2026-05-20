package com.example.umc_compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umc_compose.component.ProductGridList
import com.example.umc_compose.data.ProductData

@Composable
fun BuyScreen(
    products: List<ProductData>,
    onWishClick: (ProductData) -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "sale")

    val filteredProducts = when (selectedTabIndex) {
        0 -> products
        else -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(top = 34.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTabIndex == index

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        selectedTabIndex = index
                    }
                ) {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        color = if (isSelected) Color.Black else Color(0xFF8A8A8A),
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Box(
                        modifier = Modifier
                            .width(if (isSelected) 38.dp else 0.dp)
                            .height(2.dp)
                            .background(if (isSelected) Color.Black else Color.Transparent)
                    )
                }
            }
        }

        ProductGridList(
            products = filteredProducts,
            onWishClick = onWishClick,
            modifier = Modifier.weight(1f)
        )
    }
}