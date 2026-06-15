package com.hypexlabs.NexusLauncher.home

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.util.AppProvider
import com.hypexlabs.NexusLauncher.util.iOSIconShape
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint

@Composable
fun Dock(
    apps: List<AppInfo>,
    onOpenAppLibrary: () -> Unit,
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    iconSize: Dp = 56.dp,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .then(
                Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White.copy(alpha = 0.15f))
                    .hazeEffect(
                        style = HazeStyle(
                            backgroundColor = Color(0x99E5E5EA),
                            blurRadius = 30.dp,
                            noiseFactor = 0.04f,
                            tints = listOf(HazeTint(Color.White.copy(alpha = 0.25f))),
                        )
                    ),
            )
            .padding(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            apps.forEach { app ->
                DockIcon(
                    app = app,
                    onClick = {
                                AppProvider.launchApp(
                                    context,
                            app.packageName,
                        )
                    },
                    iconSize = iconSize,
                )
            }

            AppLibraryIcon(
                onClick = onOpenAppLibrary,
                iconSize = iconSize,
            )
        }
    }
}

@Composable
private fun AppLibraryIcon(
    onClick: () -> Unit,
    iconSize: Dp,
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(iconSize),
    ) {
        Box(
            modifier = Modifier
                .size(iconSize - 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Apps,
                contentDescription = "App Library",
                tint = Color.White,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}
