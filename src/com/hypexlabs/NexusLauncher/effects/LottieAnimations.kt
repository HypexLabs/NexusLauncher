package com.hypexlabs.NexusLauncher.effects

import androidx.compose.runtime.*
import com.airbnb.lottie.compose.*
import com.airbnb.lottie.LottieComposition

object LottieAnimations {
    @Composable
    fun rememberIconTapComposition(): LottieComposition? {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
            android.R.drawable.ic_menu_gallery
        ))
        return composition
    }

    @Composable
    fun LoadingSpinner(
        modifier: Modifier = Modifier,
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(android.R.drawable.ic_menu_rotate)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = modifier,
        )
    }
}
