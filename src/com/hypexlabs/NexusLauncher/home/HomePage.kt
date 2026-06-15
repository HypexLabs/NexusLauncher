package com.hypexlabs.NexusLauncher.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hypexlabs.NexusLauncher.effects.HapticFeedback
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.menus.IconContextMenu
import com.hypexlabs.NexusLauncher.util.AppProvider
import dev.chrisbanes.haze.HazeState

@Composable
fun HomePage(
    apps: List<AppInfo>,
    pageIndex: Int,
    totalPages: Int,
    onOpenAppLibrary: () -> Unit,
    onOpenSettings: () -> Unit,
    hazeState: HazeState,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var showContextMenu by remember { mutableStateOf<AppInfo?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 80.dp,
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled = false,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(apps, key = { it.uniqueKey }) { app ->
                IconCell(
                    app = app,
                    onClick = {
                        HapticFeedback.lightImpact(context)
                        AppProvider.launchApp(context, app.packageName)
                    },
                    onLongClick = {
                        HapticFeedback.mediumImpact(context)
                        showContextMenu = app
                    },
                )
            }
        }

        HomePageIndicator(
            currentPage = pageIndex,
            totalPages = totalPages,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 72.dp),
        )

        showContextMenu?.let { app ->
            IconContextMenu(
                app = app,
                onDismiss = { showContextMenu = null },
                onOpenSettings = onOpenSettings,
            )
        }
    }
}
