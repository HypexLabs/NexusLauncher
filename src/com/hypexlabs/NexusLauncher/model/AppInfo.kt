package com.hypexlabs.NexusLauncher.model

import android.graphics.drawable.Drawable

data class AppInfo(
    val packageName: String,
    val activityName: String,
    val label: String,
    val icon: Drawable,
    val category: String = "Other",
    val isSuggested: Boolean = false,
    val badgeCount: Int = 0,
    val isSystemApp: Boolean = false,
) {
    val uniqueKey: String get() = "$packageName/$activityName"
}
