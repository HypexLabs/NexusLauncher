package com.hypexlabs.NexusLauncher.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodayPage(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Today",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "June 16, 2026",
            fontSize = 15.sp,
            color = Color.White.copy(alpha = 0.6f),
        )

        Spacer(Modifier.height(24.dp))

        TodayWidgetCard(
            title = "Clock",
            content = { Text("7:42", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White) },
        )

        Spacer(Modifier.height(12.dp))

        TodayWidgetCard(
            title = "Calendar",
            content = { Text("No upcoming events", color = Color.White.copy(alpha = 0.7f)) },
        )

        Spacer(Modifier.height(12.dp))

        TodayWidgetCard(
            title = "Weather",
            content = { Text("73°F  Mostly Clear", fontSize = 18.sp, color = Color.White) },
        )
    }
}

@Composable
private fun TodayWidgetCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.15f))
            .padding(16.dp),
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.6f),
            fontWeight = FontWeight.Medium,
        )
        Spacer(Modifier.height(8.dp))
        content()
    }
}
