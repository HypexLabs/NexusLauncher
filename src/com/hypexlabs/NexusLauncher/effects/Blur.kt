package com.hypexlabs.NexusLauncher.effects

import androidx.compose.ui.Modifier
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.materials.HazeMaterials
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import com.hypexlabs.NexusLauncher.theme.iOSColors
import androidx.compose.ui.graphics.Color

fun Modifier.frostedDock(dark: Boolean = false): Modifier = this.then(
    hazeEffect(
        style = HazeStyle(
            backgroundColor = if (dark) Color(0x662C2C2E) else Color(0xCCD1D1D6),
            blurRadius = 30.dp,
            noiseFactor = 0.04f,
            tints = listOf(
                HazeTint(if (dark) Color(0x33FFFFFF) else Color(0x33FFFFFF))
            ),
        )
    )
)

fun Modifier.frostedSearch(dark: Boolean = false): Modifier = this.then(
    hazeEffect(
        style = HazeStyle(
            backgroundColor = if (dark) Color(0x1AFFFFFF) else Color(0x1A767680),
            blurRadius = 20.dp,
            noiseFactor = 0.02f,
            tints = emptyList(),
        )
    )
)

fun Modifier.frostedAppLibrary(dark: Boolean = false): Modifier = this.then(
    hazeEffect(
        style = HazeStyle(
            backgroundColor = if (dark) Color(0xCC1C1C1E) else Color(0xCCF2F2F7),
            blurRadius = 40.dp,
            noiseFactor = 0.03f,
            tints = listOf(
                HazeTint(if (dark) Color(0x44000000) else Color(0x44FFFFFF))
            ),
        )
    )
)

fun Modifier.frostedFolder(dark: Boolean = false): Modifier = this.then(
    hazeEffect(
        style = HazeStyle(
            backgroundColor = if (dark) Color(0x991C1C1E) else Color(0x99FFFFFF),
            blurRadius = 60.dp,
            noiseFactor = 0.05f,
            tints = listOf(
                HazeTint(if (dark) Color(0x66000000) else Color(0x66FFFFFF))
            ),
        )
    )
)

private val Int.dp get() = androidx.compose.ui.unit.Dp(this.toFloat())
