package com.hypexlabs.NexusLauncher.home

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hypexlabs.NexusLauncher.model.AppInfo
import com.hypexlabs.NexusLauncher.theme.iOSColors
import com.hypexlabs.NexusLauncher.util.AppProvider
import com.hypexlabs.NexusLauncher.util.iOSIconShape

@Composable
fun AppLibrary(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var apps by remember { mutableStateOf(AppProvider.getInstalledApps(context)) }
    var query by remember { mutableStateOf("") }

    val filtered = remember(apps, query) {
        if (query.isBlank()) apps
        else apps.filter { it.label.contains(query, ignoreCase = true) }
    }

    val categorized = remember(filtered) {
        if (query.isNotBlank()) emptyMap()
        else filtered.groupBy { it.category }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(iOSColors.lightBackground)
            .padding(top = 48.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.9f))
                    .padding(horizontal = 12.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                cursorBrush = SolidColor(iOSColors.systemBlue),
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Rounded.Search, null, Modifier.size(18.dp), tint = Color.Gray)
                        Spacer(Modifier.width(6.dp))
                        if (query.isEmpty()) {
                            Text("Search", color = Color.Gray, fontSize = 16.sp)
                        }
                        innerTextField()
                    }
                },
            )
            Spacer(Modifier.width(8.dp))
            TextButton(onClick = onClose) {
                Text("Cancel", color = iOSColors.systemBlue, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(Modifier.height(12.dp))

        if (query.isNotBlank()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f),
            ) {
                items(filtered, key = { it.uniqueKey }) { app ->
                    AppGridItem(app = app, onClick = {
                        AppProvider.launchApp(context, app.packageName)
                        onClose()
                    })
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                categorized.forEach { (category, apps) ->
                    item(key = "header_$category") {
                        Text(
                            text = category,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    item(key = "grid_$category") {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.height(80.dp * ((apps.size + 3) / 4)),
                            userScrollEnabled = false,
                        ) {
                            items(apps, key = { it.uniqueKey }) { app ->
                                AppGridItem(app = app, onClick = {
                                    AppProvider.launchApp(context, app.packageName)
                                    onClose()
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AppGridItem(app: AppInfo, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(72.dp)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier.size(56.dp).clip(iOSIconShape),
            contentAlignment = Alignment.Center,
        ) {
            androidx.compose.foundation.Image(
                painter = app.icon.toComposePainter(),
                contentDescription = app.label,
                modifier = Modifier.fillMaxSize(),
            )
        }
        Spacer(Modifier.height(2.dp))
        Text(
            text = app.label,
            fontSize = 10.sp,
            maxLines = 1,
        )
    }
}
