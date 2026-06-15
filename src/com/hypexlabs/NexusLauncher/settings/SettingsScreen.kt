package com.hypexlabs.NexusLauncher.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hypexlabs.NexusLauncher.theme.iOSColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nexus Launcher", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = iOSColors.lightBackground,
                ),
            )
        },
        modifier = modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(iOSColors.lightBackground)
                .verticalScroll(rememberScrollState()),
        ) {
            SettingsSection("Home Screen") {
                SettingsRow("Icon Size", "Default")
                SettingsRow("Grid Layout", "4 × 5")
                SettingsRow("Show Labels", "Yes")
                SettingsRow("Badge Style", "Count")
            }

            SettingsSection("Dock") {
                SettingsRow("Max Dock Icons", "4")
                SettingsRow("Show Recents", "No")
                SettingsRow("Dock Style", "Frosted")
            }

            SettingsSection("App Library") {
                SettingsRow("Search Engine", "Device")
                SettingsRow("Suggestions", "On")
                SettingsRow("Category Order", "Automatic")
            }

            SettingsSection("Wallpaper") {
                SettingsRow("Change Wallpaper", "Pick from gallery")
            }

            SettingsSection("Gestures") {
                SettingsRow("Swipe Up", "Open App Library")
                SettingsRow("Swipe Down", "Search")
                SettingsRow("Double Tap", "Lock Screen")
            }

            SettingsSection("About") {
                SettingsRow("Version", "1.0.0")
                SettingsRow("Build", "2026.06.16")
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            color = iOSColors.systemGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 4.dp, bottom = 8.dp),
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            content = content,
        )
    }
}

@Composable
private fun SettingsRow(
    title: String,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
        )
        if (subtitle != null) {
            Text(
                text = subtitle,
                fontSize = 15.sp,
                color = iOSColors.systemGray,
            )
        }
    }
}
