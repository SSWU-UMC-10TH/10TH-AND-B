package com.example.umc_compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umc_compose.component.ProductGridList
import com.example.umc_compose.data.ProductData

@Composable
fun WishScreen(
    products: List<ProductData>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(top = 53.dp)
    ) {
        Text(
            text = "위시리스트",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 23.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProductGridList(
            products = products,
            modifier = Modifier.weight(1f)
        )
    }
}