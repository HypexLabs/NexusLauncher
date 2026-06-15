package com.hypexlabs.NexusLauncher.effects

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class ParallaxState {
    var offsetX by mutableStateOf(0f)
    var offsetY by mutableStateOf(0f)
}

@Composable
fun rememberParallaxState(maxOffsetDp: Float = 10f): ParallaxState {
    val context = LocalContext.current
    val state = remember { ParallaxState() }

    DisposableEffect(context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var lastX = 0f
        var lastY = 0f

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val lpf = 0.3f
                val ax = event.values[0]
                val ay = event.values[1]
                lastX = lastX * (1 - lpf) + ax * lpf
                lastY = lastY * (1 - lpf) + ay * lpf
                state.offsetX = (lastX / 10f).coerceIn(-maxOffsetDp, maxOffsetDp)
                state.offsetY = (lastY / 10f).coerceIn(-maxOffsetDp, maxOffsetDp)
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager?.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager?.unregisterListener(listener)
        }
    }

    return state
}

fun Modifier.parallaxOffset(state: ParallaxState): Modifier = composed {
    val density = androidx.compose.ui.platform.LocalDensity.current
    val x = with(density) { state.offsetX.dp.roundToPx() }
    val y = with(density) { state.offsetY.dp.roundToPx() }
    this.then(
        Modifier.offset { IntOffset(x, y) }
    )
}
