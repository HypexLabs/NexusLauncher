package com.hypexlabs.NexusLauncher.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.InputStream

object WallpaperManager {
    fun setWallpaper(context: Context, uri: Uri): Boolean {
        return try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bitmap = inputStream?.use { stream ->
                android.graphics.BitmapFactory.decodeStream(stream)
            }
            if (bitmap != null) {
                val wm = context.getSystemService(Context.WALLPAPER_SERVICE) as android.app.WallpaperManager
                wm.setBitmap(bitmap)
                true
            } else false
        } catch (_: Exception) { false }
    }

    fun setWallpaper(context: Context, bitmap: Bitmap): Boolean {
        return try {
            val wm = context.getSystemService(Context.WALLPAPER_SERVICE) as android.app.WallpaperManager
            wm.setBitmap(bitmap)
            true
        } catch (_: Exception) { false }
    }
}
