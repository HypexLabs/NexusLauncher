package com.hypexlabs.NexusLauncher.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomePageIndicator(
    currentPage: Int,
    totalPages: Int,
    modifier: Modifier = Modifier,
) {
    if (totalPages <= 1) return

    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(8.dp),
    ) {
        repeat(totalPages) { index ->
            val isSelected = index == currentPage
            val color by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.White.copy(alpha = 0.4f),
                animationSpec = spring(),
                label = "dotColor",
            )
            Box(
                modifier = Modifier
                    .size(if (isSelected) 7.dp else 6.dp)
                    .clip(CircleShape)
                    .background(color),
            )
        }
    }
}
