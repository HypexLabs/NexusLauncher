package com.hypexlabs.NexusLauncher.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import com.hypexlabs.NexusLauncher.model.AppInfo

@Composable
fun Dock(
    apps: List<AppInfo>,
    onAppClick: (AppInfo) -> Unit,
    onAllAppsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White.copy(alpha = 0.4f))
            .then(
                Modifier.background(MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.6f))
            )
            .padding(8.dp),
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items(apps) { app ->
                DockIcon(app = app, onClick = { onAppClick(app) })
            }
            item { AllAppsIcon(onClick = onAllAppsClick) }
        }
    }
}

@Composable
private fun DockIcon(app: AppInfo, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(56.dp)) {
        Icon(
            painter = app.icon.toComposePainter(),
            contentDescription = app.label,
            tint = Color.Unspecified,
        )
    }
}

@Composable
private fun AllAppsIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(56.dp)) {
        Icon(
            imageVector = Icons.Default.Apps,
            contentDescription = "All Apps",
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

fun Drawable.toComposePainter(): BitmapPainter {
    val bitmap = (this as? BitmapDrawable)?.bitmap
        ?: Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    return BitmapPainter(bitmap.asImageBitmap())
}
