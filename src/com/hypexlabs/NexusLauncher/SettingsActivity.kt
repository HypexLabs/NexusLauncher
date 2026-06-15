package com.hypexlabs.NexusLauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hypexlabs.NexusLauncher.settings.SettingsScreen
import com.hypexlabs.NexusLauncher.theme.NexusTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexusTheme {
                SettingsScreen(onBack = { finish() })
            }
        }
    }
}
