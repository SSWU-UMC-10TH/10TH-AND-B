package com.example.umc_compose.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.umc_compose.component.CustomBottomBar
import com.example.umc_compose.data.ProductData
import com.example.umc_compose.data.initialBuyProducts
import com.example.umc_compose.screen.BuyScreen
import com.example.umc_compose.screen.CartScreen
import com.example.umc_compose.screen.HomeScreen
import com.example.umc_compose.screen.ProfileScreen
import com.example.umc_compose.screen.WishScreen

@Composable
fun NikeMainScreen() {
    val navController = rememberNavController()

    val buyProducts = remember {
        mutableStateListOf<ProductData>().apply {
            addAll(initialBuyProducts)
        }
    }

    val wishProducts = remember {
        mutableStateListOf<ProductData>()
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Routes.HOME

    Scaffold(
        containerColor = Color.White,
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            CustomBottomBar(
                currentRoute = currentRoute,
                onTabClick = { item ->
                    navController.navigateBottomTab(item)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen()
            }

            composable(Routes.BUY) {
                BuyScreen(
                    products = buyProducts,
                    onWishClick = { clickedProduct ->
                        val index = buyProducts.indexOfFirst { product ->
                            product.id == clickedProduct.id
                        }

                        if (index != -1) {
                            val updatedProduct = buyProducts[index].copy(
                                isLiked = !buyProducts[index].isLiked
                            )

                            buyProducts[index] = updatedProduct

                            if (updatedProduct.isLiked) {
                                val alreadyExists = wishProducts.any { product ->
                                    product.id == updatedProduct.id
                                }

                                if (!alreadyExists) {
                                    wishProducts.add(
                                        updatedProduct.copy(
                                            isLiked = true,
                                            showWishIcon = false
                                        )
                                    )
                                }
                            } else {
                                wishProducts.removeAll { product ->
                                    product.id == updatedProduct.id
                                }
                            }
                        }
                    }
                )
            }

            composable(Routes.WISH) {
                WishScreen(
                    products = wishProducts
                )
            }

            composable(Routes.CART) {
                CartScreen(
                    onOrderClick = {
                        navController.navigate(Routes.BUY) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(Routes.PROFILE) {
                ProfileScreen()
            }
        }
    }
}

private fun NavController.navigateBottomTab(item: BottomNavItem) {
    navigate(item.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}