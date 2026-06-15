package com.hypexlabs.NexusLauncher.theme

import androidx.compose.ui.graphics.Color

object NexusColors {
    val Orange = Color(0xFFFF6C00)
    val Blue = Color(0xFF007AFF)
    val Teal = Color(0xFF34C759)
    val Red = Color(0xFFFF3B30)
    val Surface = Color(0xFFF5F5F5)
    val SurfaceDark = Color(0xFF1C1C1E)
    val SurfaceLight = Color(0xFFF9F9F9)
    val OnSurface = Color(0xFF1C1C1E)
    val OnSurfaceDark = Color(0xFFF2F2F7)
    val OnSurfaceLight = Color(0xFF1C1C1E)
    val DockBg = Color(0x99FFFFFF)
    val DockBgDark = Color(0x992C2C2E)
    val SearchBg = Color(0xFFFFFFFF)
    val SearchBgDark = Color(0xFF2C2C2E)
    val DrawerBg = Color(0xFFF2F2F7)
    val DrawerBgDark = Color(0xFF1C1C1E)
    val Divider = Color(0x1A000000)
    val DividerDark = Color(0x1AFFFFFF)

    val MaterialLight = lightColorScheme()
    val MaterialDark = darkColorScheme()
}

fun lightColorScheme() = androidx.compose.material3.lightColorScheme(
    primary = NexusColors.Orange,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = NexusColors.Blue,
    tertiary = NexusColors.Teal,
    surface = NexusColors.SurfaceLight,
    onSurface = NexusColors.OnSurfaceLight,
    surfaceContainerLow = NexusColors.SurfaceLight,
)

fun darkColorScheme() = androidx.compose.material3.darkColorScheme(
    primary = NexusColors.Orange,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = NexusColors.Blue,
    tertiary = NexusColors.Teal,
    surface = NexusColors.SurfaceDark,
    onSurface = NexusColors.OnSurfaceDark,
    surfaceContainerLow = NexusColors.SurfaceDark,
)
