package com.hypexlabs.NexusLauncher.components

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hypexlabs.NexusLauncher.effects.SpringAnimations

@Composable
fun CupertinoBadge(
    count: Int,
    modifier: Modifier = Modifier,
    maxCount: Int = 99,
) {
    if (count <= 0) return

    val showCount = count.coerceAtMost(maxCount)
    val text = if (count > maxCount) "$maxCount+" else showCount.toString()
    val isSingleDigit = showCount < 10

    val animScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
            stiffness = androidx.compose.animation.core.Spring.StiffnessHigh,
        ),
        label = "badgeScale",
    )

    Box(
        modifier = modifier
            .scale(animScale)
            .height(18.dp)
            .widthIn(min = 18.dp)
            .clip(CircleShape)
            .background(Color(0xFFFF3B30))
            .padding(horizontal = if (isSingleDigit) 0.dp else 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun CupertinoBadgeDot(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(Color(0xFFFF3B30)),
    )
}
