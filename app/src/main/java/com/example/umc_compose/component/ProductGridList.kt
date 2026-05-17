package com.example.umc_compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umc_compose.data.ProductData

@Composable
fun ProductGridList(
    products: List<ProductData>,
    onWishClick: (ProductData) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val productRows = products.chunked(2)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        items(
            items = productRows,
            key = { row ->
                row.joinToString(separator = "_") { product ->
                    product.id.toString()
                }
            }
        ) { rowProducts ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowProducts.forEach { product ->
                    key(product.id) {
                        ProductItem(
                            product = product,
                            onWishClick = onWishClick,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                if (rowProducts.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: ProductData,
    onWishClick: (ProductData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFF5F5F5))
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )

            if (product.showWishIcon) {
                Text(
                    text = if (product.isLiked) "♥" else "♡",
                    fontSize = 22.sp,
                    color = if (product.isLiked) Color(0xFFB60000) else Color(0xFF8C8C8C),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clickable {
                            onWishClick(product)
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (product.isBestSeller) {
            Text(
                text = "BestSeller",
                color = Color(0xFFFF8A00),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = product.name,
            color = Color.Black,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 13.sp
        )

        if (product.category.isNotBlank()) {
            Text(
                text = product.category,
                color = Color.Gray,
                fontSize = 10.sp,
                lineHeight = 12.sp
            )
        }

        if (product.subInfo.isNotBlank()) {
            Text(
                text = product.subInfo,
                color = Color.Gray,
                fontSize = 10.sp,
                lineHeight = 12.sp
            )
        }

        Text(
            text = product.price,
            color = Color.Black,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}