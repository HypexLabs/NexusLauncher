package com.hypexlabs.NexusLauncher.home

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.util.iOSIconShape

@Composable
fun IconCell(
    app: AppInfo,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 60.dp,
    labelVisible: Boolean = true,
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.88f else 1f,
        animationSpec = spring(
            dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
            stiffness = androidx.compose.animation.core.Spring.StiffnessMedium,
        ),
        label = "iconPress",
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(72.dp)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                    },
                    onTap = { onClick() },
                    onLongPress = { onLongClick() },
                )
            },
    ) {
        Box(
            modifier = Modifier
                .size(iconSize)
                .clip(iOSIconShape),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.foundation.Image(
                painter = app.icon.toComposePainter(),
                contentDescription = app.label,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize(),
            )
        }

        if (labelVisible) {
            Spacer(Modifier.height(2.dp))
            Text(
                text = app.label,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                modifier = Modifier.width(72.dp),
            )
        }
    }
}

fun Drawable.toComposePainter(): BitmapPainter {
    val bitmap = (this as? BitmapDrawable)?.bitmap
        ?: android.graphics.Bitmap.createBitmap(1, 1, android.graphics.Bitmap.Config.ARGB_8888)
    return BitmapPainter(bitmap.asImageBitmap())
}
