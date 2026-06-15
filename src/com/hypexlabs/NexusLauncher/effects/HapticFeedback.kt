package com.hypexlabs.NexusLauncher.effects

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.view.HapticFeedbackConstants

object HapticFeedback {
    fun lightImpact(context: Context) {
        val view = (context as? android.app.Activity)?.window?.decorView
        view?.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
    }

    fun mediumImpact(context: Context) {
        val view = (context as? android.app.Activity)?.window?.decorView
        view?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
    }

    fun heavyImpact(context: Context) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vm = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vm?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
        vibrator?.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE))
    }

    fun selectionChanged(context: Context) {
        val view = (context as? android.app.Activity)?.window?.decorView
        try {
            view?.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
        } catch (_: Exception) {
            view?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }
}
