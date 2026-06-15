package com.hypexlabs.NexusLauncher.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.hypexlabs.NexusLauncher.model.AppInfo

object AppProvider {

    fun getInstalledApps(context: Context, excludeSelf: Boolean = true): List<AppInfo> {
        val pm = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val activities = pm.queryIntentActivities(intent, 0)
            .sortedBy { it.loadLabel(pm).toString() }

        return activities
            .filter { ri ->
                if (excludeSelf) ri.activityInfo.packageName != context.packageName
                else true
            }
            .map { ri ->
                AppInfo(
                    packageName = ri.activityInfo.packageName,
                    label = ri.loadLabel(pm).toString(),
                    icon = ri.activityInfo.applicationInfo.loadIcon(pm),
                )
            }
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
}
