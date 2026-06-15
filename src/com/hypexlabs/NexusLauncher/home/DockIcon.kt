package com.hypexlabs.NexusLauncher.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.util.iOSIconShape

@Composable
fun DockIcon(
    app: AppInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 56.dp,
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.85f else 1f,
        animationSpec = spring(
            dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
            stiffness = androidx.compose.animation.core.Spring.StiffnessMedium,
        ),
        label = "dockPress",
    )

    Box(
        modifier = modifier
            .size(iconSize)
            .scale(scale)
            .clip(iOSIconShape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                    },
                    onTap = { onClick() },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.foundation.Image(
            painter = app.icon.toComposePainter(),
            contentDescription = app.label,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
