package com.example.umc_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                NikeMainScreen()
            }
        }
    }
}

private object Routes {
    const val HOME = "home"
    const val BUY = "buy"
    const val WISH = "wish"
    const val CART = "cart"
    const val PROFILE = "profile"
}

private data class BottomNavItem(
    val route: String,
    val label: String,
    val iconRes: Int
)

private val bottomNavItems = listOf(
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

@Composable
private fun NikeMainScreen() {
    val navController = rememberNavController()

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
                BuyScreen()
            }

            composable(Routes.WISH) {
                WishScreen()
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

@Composable
private fun CustomBottomBar(
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

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(46.dp))

        Text(
            text = "Discover",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "9월 4일 목요일",
            fontSize = 13.sp,
            color = Color(0xFF8C8C8C),
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "home image",
            modifier = Modifier
                .fillMaxWidth()
                .height(345.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun BuyScreen() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "Shoes")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(top = 54.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(34.dp),
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
                        fontSize = 14.sp,
                        color = if (isSelected) Color.Black else Color(0xFF8A8A8A),
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .width(if (index == 0) 68.dp else 0.dp)
                            .height(2.dp)
                            .background(
                                if (isSelected) Color.Black else Color.Transparent
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun WishScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
            .padding(start = 23.dp, top = 53.dp)
    ) {
        Text(
            text = "위시리스트",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
private fun CartScreen(
    onOrderClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bag),
                contentDescription = "empty cart",
                modifier = Modifier.size(58.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                fontSize = 11.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }

        Button(
            onClick = onOrderClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 28.dp, vertical = 18.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "주문하기",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
    )
}