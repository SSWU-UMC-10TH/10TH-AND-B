package com.example.umc_compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umc_compose.R
import com.example.umc_compose.data.ProductData
import com.example.umc_compose.data.homeProducts

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .statusBarsPadding()
            .background(Color.White),
        contentPadding = PaddingValues(
            start = 22.dp,
            end = 22.dp,
            top = 28.dp,
            bottom = 24.dp
        )
    ) {
        item {
            Text(
                text = "Discover",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "9월 4일 목요일",
                fontSize = 13.sp,
                color = Color(0xFF8C8C8C)
            )

            Spacer(modifier = Modifier.height(26.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "home banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "What's new",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "나이키 최신 상품",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF8C8C8C)
            )

            Spacer(modifier = Modifier.height(18.dp))
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(end = 40.dp)
            ) {
                items(
                    items = homeProducts,
                    key = { product -> product.id }
                ) { product ->
                    HomeProductItem(product = product)
                }
            }
        }
    }
}

@Composable
private fun HomeProductItem(
    product: ProductData
) {
    Column(
        modifier = Modifier.width(255.dp)
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(255.dp)
                .background(Color(0xFFF5F5F5)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = product.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = product.price,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}