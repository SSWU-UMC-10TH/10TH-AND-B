package com.example.umcproject1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umcproject1.ui.theme.Umcproject1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Umcproject1Theme {
                EmotionStampScreen()
            }
        }
    }
}

data class EmotionStamp(
    val imageRes: Int,
    val text: String,
    val selectedColor: Color
)

@Composable
fun EmotionStampScreen() {
    val emotionList = listOf(
        EmotionStamp(
            imageRes = R.drawable.ic_happy,
            text = "더없이 행복한 하루였어요",
            selectedColor = Color(0xFFE5C14C)
        ),
        EmotionStamp(
            imageRes = R.drawable.ic_excited,
            text = "들뜨고 흥분돼요",
            selectedColor = Color(0xFF6FA8DC)
        ),
        EmotionStamp(
            imageRes = R.drawable.ic_normal,
            text = "평범한 하루였어요",
            selectedColor = Color(0xFF8E7CC3)
        ),
        EmotionStamp(
            imageRes = R.drawable.ic_sad,
            text = "생각이 많아지고 불안해요",
            selectedColor = Color(0xFF93C47D)
        ),
        EmotionStamp(
            imageRes = R.drawable.ic_angry,
            text = "부글부글 화가 나요",
            selectedColor = Color(0xFFE06666)
        )
    )

    var selectedIndex by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 70.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "오늘 하루는 어떠셨나요?",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "감정우표를 선택해주세요",
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "선택한 감정우표를 기반으로 맞춤형 질문이 배달됩니다",
                fontSize = 11.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            emotionList.forEachIndexed { index, item ->
                EmotionStampItem(
                    stamp = item,
                    isSelected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun EmotionStampItem(
    stamp: EmotionStamp,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = stamp.imageRes),
            contentDescription = stamp.text,
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stamp.text,
            fontSize = 16.sp,
            color = if (isSelected) stamp.selectedColor else Color.Black
        )
    }
}