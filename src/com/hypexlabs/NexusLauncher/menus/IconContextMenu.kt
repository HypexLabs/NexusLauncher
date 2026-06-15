package com.hypexlabs.NexusLauncher.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.util.AppProvider
import com.hypexlabs.NexusLauncher.util.iOSIconShape

@Composable
fun IconContextMenu(
    app: AppInfo,
    onDismiss: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
        ) {
            ContextMenuItem("Open") {
                AppProvider.launchApp(context, app.packageName)
                onDismiss()
            }
            ContextMenuItem("Remove from Home Screen") { onDismiss() }
            ContextMenuItem("Share App") { onDismiss() }
            ContextMenuItem("Edit Home Screen") {
                onOpenSettings()
                onDismiss()
            }
        }
    }
}

@Composable
private fun ContextMenuItem(
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
    )
}
