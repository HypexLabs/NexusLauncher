package com.hypexlabs.NexusLauncher.home

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hypexlabs.NexusLauncher.effects.rememberParallaxState
import com.hypexlabs.NexusLauncher.effects.parallaxOffset
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.util.AppProvider
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@Composable
fun iOSHomeScreen(
    onOpenAppLibrary: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var apps by remember { mutableStateOf(AppProvider.getInstalledApps(context)) }
    val dockApps = remember(apps) { apps.take(4) }
    val pageApps = remember(apps) { splitIntoPages(apps.drop(4)) }
    val pagerState = rememberPagerState(pageCount = { pageApps.size.coerceAtLeast(1) })
    val hazeState = remember { HazeState() }
    val parallax = rememberParallaxState()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState),
        ) {
            Spacer(Modifier.weight(0.3f))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.Top,
            ) { page ->
                if (page < pageApps.size) {
                    HomePage(
                        apps = pageApps[page],
                        pageIndex = page,
                        totalPages = pageApps.size,
                        onOpenAppLibrary = onOpenAppLibrary,
                        onOpenSettings = onOpenSettings,
                        hazeState = hazeState,
                    )
                }
            }

            Dock(
                apps = dockApps,
                onOpenAppLibrary = onOpenAppLibrary,
                modifier = Modifier.padding(bottom = 8.dp),
                hazeState = hazeState,
            )
        }
    }
}

private fun splitIntoPages(apps: List<AppInfo>): List<List<AppInfo>> {
    val iconsPerPage = 24
    return apps.chunked(iconsPerPage)
}
