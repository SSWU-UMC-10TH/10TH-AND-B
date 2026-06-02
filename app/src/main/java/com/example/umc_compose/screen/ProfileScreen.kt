package com.example.umc_compose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.umc_compose.data.ReqresUserDto
import com.example.umc_compose.data.RetrofitClient
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() {
    var user by remember { mutableStateOf<ReqresUserDto?>(null) }
    var followingList by remember { mutableStateOf<List<ReqresUserDto>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    fun fetchData() {
        coroutineScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val userResponse = RetrofitClient.api.getUser(1)
                val usersResponse = RetrofitClient.api.getUsers()

                user = userResponse.data
                followingList = usersResponse.data
            } catch (e: Exception) {
                errorMessage = "오류 발생: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        fetchData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color.White)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            errorMessage != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = errorMessage ?: "오류가 발생했습니다.",
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    OutlinedButton(
                        onClick = { fetchData() }
                    ) {
                        Text(text = "다시 시도")
                    }
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    user?.let {
                        ProfileTopSection(user = it)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    ProfileMenuRow()

                    Spacer(modifier = Modifier.height(18.dp))

                    BenefitSection()

                    Spacer(modifier = Modifier.height(10.dp))

                    FollowingPager(followingList = followingList)

                    Spacer(modifier = Modifier.height(50.dp))

                    Text(
                        text = "회원 가입일: 2025년 9월",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Composable
fun ProfileTopSection(user: ReqresUserDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.avatar,
            contentDescription = "${user.fullName} 프로필 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(78.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = user.fullName,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedButton(
            onClick = { },
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(horizontal = 38.dp, vertical = 8.dp),
            modifier = Modifier.height(42.dp)
        ) {
            Text(
                text = "프로필 수정",
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProfileMenuRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileMenuItem(icon = "▰", label = "주문")
        VerticalLine()
        ProfileMenuItem(icon = "▣", label = "패스")
        VerticalLine()
        ProfileMenuItem(icon = "▰", label = "이벤트")
        VerticalLine()
        ProfileMenuItem(icon = "⚙", label = "설정")
    }
}

@Composable
fun ProfileMenuItem(
    icon: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = label,
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun VerticalLine() {
    Box(
        modifier = Modifier
            .height(28.dp)
            .width(1.dp)
            .background(Color(0xFFE5E5E5))
    )
}

@Composable
fun BenefitSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "나이키 멤버 혜택",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "0개 사용 가능",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Text(
                text = ">",
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FollowingPager(followingList: List<ReqresUserDto>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 18.dp, bottom = 28.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "팔로잉 (${followingList.size})",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "편집",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (followingList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "팔로잉 유저가 없습니다.",
                    color = Color.Gray
                )
            }
        } else {
            val pagerState = rememberPagerState(
                pageCount = { followingList.size }
            )

            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fixed(88.dp),
                pageSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(92.dp)
            ) { page ->
                FollowingImageItem(user = followingList[page])
            }
        }
    }
}

@Composable
fun FollowingImageItem(user: ReqresUserDto) {
    AsyncImage(
        model = user.avatar,
        contentDescription = "${user.fullName} 팔로잉 이미지",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(88.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFD9D9D9))
    )
}
