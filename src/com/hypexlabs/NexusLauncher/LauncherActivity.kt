package com.hypexlabs.NexusLauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.hypexlabs.NexusLauncher.home.AppDrawer
import com.hypexlabs.NexusLauncher.home.HomeScreen
import com.hypexlabs.NexusLauncher.theme.NexusTheme

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexusTheme {
                LauncherContent()
            }
        }
    }
}

@Composable
fun LauncherContent() {
    var showDrawer by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = showDrawer,
            transitionSpec = {
                if (targetState) {
                    slideInVertically { it } + fadeIn() togetherWith
                        slideOutVertically { -it / 3 } + fadeOut()
                } else {
                    slideInVertically { -it } + fadeIn() togetherWith
                        slideOutVertically { it } + fadeOut()
                }
            },
            label = "drawerTransition",
        ) { drawerOpen ->
            if (drawerOpen) {
                AppDrawer(onClose = { showDrawer = false })
            } else {
                HomeScreen(
                    onOpenDrawer = { showDrawer = true },
                    onOpenSettings = {
                        context.startActivity(
                            android.content.Intent(context, SettingsActivity::class.java)
                        )
                    },
                )
            }
        }
    }
}
