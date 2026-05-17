package com.example.umc_compose.navigation

import com.example.umc_compose.R

object Routes {
    const val HOME = "home"
    const val BUY = "buy"
    const val WISH = "wish"
    const val CART = "cart"
    const val PROFILE = "profile"
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val iconRes: Int
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = Routes.HOME,
        label = "홈",
        iconRes = R.drawable.ic_home
    ),
    BottomNavItem(
        route = Routes.BUY,
        label = "구매하기",
        iconRes = R.drawable.ic_buy
    ),
    BottomNavItem(
        route = Routes.WISH,
        label = "위시리스트",
        iconRes = R.drawable.ic_wish
    ),
    BottomNavItem(
        route = Routes.CART,
        label = "장바구니",
        iconRes = R.drawable.ic_cart
    ),
    BottomNavItem(
        route = Routes.PROFILE,
        label = "프로필",
        iconRes = R.drawable.ic_user
    )
)