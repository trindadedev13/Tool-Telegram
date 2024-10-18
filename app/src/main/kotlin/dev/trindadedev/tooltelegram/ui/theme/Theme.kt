package dev.trindadedev.tooltelegram.ui.theme

import android.app.Activity
import android.os.Build

import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ToolTelegramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    highContrastDarkTheme: Boolean = false,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && supportsDynamicTheming() -> {
                val context = LocalContext.current
                when {
                    darkTheme && highContrastDarkTheme ->
                        dynamicDarkColorScheme(context)
                            .copy(background = Color.Black, surface = Color.Black)
                    darkTheme -> dynamicDarkColorScheme(context)
                    else -> dynamicLightColorScheme(context)
                }
            }

            darkTheme && highContrastDarkTheme ->
                DarkColorScheme.copy(background = Color.Black, surface = Color.Black)
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).apply {
                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightStatusBars = !darkTheme
                    isAppearanceLightNavigationBars = !darkTheme
                }
            }
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
