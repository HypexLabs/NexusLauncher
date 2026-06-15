package com.hypexlabs.NexusLauncher.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.hypexlabs.NexusLauncher.model.AppInfo

object AppProvider {
    private var cachedApps: List<AppInfo> = emptyList()
    private var lastCacheTime = 0L

    fun getInstalledApps(context: Context, forceRefresh: Boolean = false): List<AppInfo> {
        val now = System.currentTimeMillis()
        if (!forceRefresh && cachedApps.isNotEmpty() && now - lastCacheTime < 5000) {
            return cachedApps
        }

        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val activities = pm.queryIntentActivities(intent, 0)

        val seen = mutableSetOf<String>()
        val apps = activities
            .filter { ri ->
                val key = "${ri.activityInfo.packageName}/${ri.activityInfo.name}"
                seen.add(key)
            }
            .sortedBy { it.loadLabel(pm).toString() }
            .map { ri ->
                val isSystem = try {
                    (ri.activityInfo.applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_SYSTEM) != 0
                } catch (_: Exception) { false }

                AppInfo(
                    packageName = ri.activityInfo.packageName,
                    activityName = ri.activityInfo.name,
                    label = ri.loadLabel(pm).toString(),
                    icon = ri.activityInfo.applicationInfo.loadIcon(pm),
                    category = guessCategory(ri.activityInfo.packageName),
                    isSystemApp = isSystem,
                )
            }

        cachedApps = apps
        lastCacheTime = now
        return apps
    }

    fun launchApp(context: Context, packageName: String): Boolean {
        return try {
            val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context.startActivity(intent)
                true
            } else false
        } catch (_: Exception) { false }
    }

    private fun guessCategory(packageName: String): String {
        return when {
            packageName.contains("chrome") || packageName.contains("browser") -> "Productivity & Tools"
            packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("instagram") || packageName.contains("whatsapp") || packageName.contains("telegram") || packageName.contains("snapchat") || packageName.contains("messenger") || packageName.contains("linkedin") || packageName.contains("reddit") -> "Social"
            packageName.contains("maps") || packageName.contains("navi") || packageName.contains("travel") -> "Travel"
            packageName.contains("music") || packageName.contains("spotify") || packageName.contains("soundcloud") || packageName.contains("shazam") -> "Music"
            packageName.contains("video") || packageName.contains("youtube") || packageName.contains("netflix") || packageName.contains("prime") || packageName.contains("hulu") || packageName.contains("disney") -> "Entertainment"
            packageName.contains("game") || packageName.contains("play") || packageName.contains("casino") || packageName.contains("puzzle") -> "Games"
            packageName.contains("photo") || packageName.contains("camera") || packageName.contains("gallery") || packageName.contains("image") -> "Photo & Video"
            packageName.contains("clock") || packageName.contains("calendar") || packageName.contains("weather") || packageName.contains("note") || packageName.contains("reminder") -> "Utilities"
            packageName.contains("health") || packageName.contains("fit") || packageName.contains("sport") || packageName.contains("run") -> "Health & Fitness"
            packageName.contains("shop") || packageName.contains("store") || packageName.contains("buy") || packageName.contains("amazon") || packageName.contains("flipkart") -> "Shopping"
            packageName.contains("bank") || packageName.contains("pay") || packageName.contains("fin") || packageName.contains("wallet") || packageName.contains("money") -> "Finance"
            else -> "Other"
        }
    }
}
