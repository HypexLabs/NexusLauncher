package com.hypexlabs.NexusLauncher.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object iOSColors {
    val systemRed = Color(0xFFFF3B30)
    val systemOrange = Color(0xFFFF9500)
    val systemYellow = Color(0xFFFFCC00)
    val systemGreen = Color(0xFF34C759)
    val systemTeal = Color(0xFF5AC8FA)
    val systemBlue = Color(0xFF007AFF)
    val systemIndigo = Color(0xFF5856D6)
    val systemPurple = Color(0xFFAF52DE)
    val systemPink = Color(0xFFFF2D55)
    val systemGray = Color(0xFF8E8E93)

    val lightBackground = Color(0xFFF2F2F7)
    val darkBackground = Color(0xFF1C1C1E)
    val lightLabel = Color(0xFF000000)
    val darkLabel = Color(0xFFFFFFFF)

    val dockLight = Color(0xCCD1D1D6)
    val dockDark = Color(0x662C2C2E)
    val badgeRed = Color(0xFFFF3B30)
}

fun lightColorScheme() = lightColorScheme(
    primary = iOSColors.systemBlue,
    onPrimary = Color.White,
    secondary = iOSColors.systemGreen,
    tertiary = iOSColors.systemOrange,
    surface = iOSColors.lightBackground,
    onSurface = iOSColors.lightLabel,
)

fun darkColorScheme() = darkColorScheme(
    primary = iOSColors.systemBlue,
    onPrimary = Color.White,
    secondary = iOSColors.systemGreen,
    tertiary = iOSColors.systemOrange,
    surface = iOSColors.darkBackground,
    onSurface = iOSColors.darkLabel,
)
