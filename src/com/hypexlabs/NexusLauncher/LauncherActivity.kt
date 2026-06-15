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
import com.hypexlabs.NexusLauncher.home.AppLibrary
import com.hypexlabs.NexusLauncher.home.iOSHomeScreen
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
    var showAppLibrary by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = showAppLibrary,
            transitionSpec = {
                if (targetState) {
                    slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = androidx.compose.animation.core.spring(
                            dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
                            stiffness = androidx.compose.animation.core.Spring.StiffnessMedium,
                        ),
                    ) + fadeIn() togetherWith
                        slideOutVertically(
                            targetOffsetY = { -it / 3 },
                            animationSpec = androidx.compose.animation.core.spring(),
                        ) + fadeOut()
                } else {
                    slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = androidx.compose.animation.core.spring(),
                    ) + fadeIn() togetherWith
                        slideOutVertically(
                            targetOffsetY = { it },
                            animationSpec = androidx.compose.animation.core.spring(
                                dampingRatio = androidx.compose.animation.core.Spring.DampingRatioMediumBouncy,
                                stiffness = androidx.compose.animation.core.Spring.StiffnessMedium,
                            ),
                        ) + fadeOut()
                }
            },
            label = "appLibraryTransition",
        ) { libraryOpen ->
            if (libraryOpen) {
                AppLibrary(onClose = { showAppLibrary = false })
            } else {
                iOSHomeScreen(
                    onOpenAppLibrary = { showAppLibrary = true },
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
