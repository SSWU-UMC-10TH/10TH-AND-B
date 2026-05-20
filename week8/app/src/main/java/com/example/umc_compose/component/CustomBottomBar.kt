package com.example.umc_compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umc_compose.navigation.BottomNavItem
import com.example.umc_compose.navigation.bottomNavItems

@Composable
fun CustomBottomBar(
    currentRoute: String,
    onTabClick: (BottomNavItem) -> Unit
) {
    Surface(
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .navigationBarsPadding()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFEDEDED))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                bottomNavItems.forEach { item ->
                    val isSelected = currentRoute == item.route
                    val itemColor = if (isSelected) Color.Black else Color(0xFF8C8C8C)

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                onTabClick(item)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.label,
                            modifier = Modifier.size(22.dp),
                            tint = itemColor
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.label,
                            fontSize = 9.sp,
                            color = itemColor,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}