package com.aquabrowser.app.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun DynamicAquaTheme(content: @Composable () -> Unit) {
    val themeManager = remember { ThemeManager.instance }

    // Map Chrome Theme Colors to Material 3 Colors
    val dynamicColorScheme = darkColorScheme(
        background = themeManager.frameColor.value,
        surface = themeManager.toolbarColor.value,
        onBackground = themeManager.textColor.value,
        onSurface = themeManager.textColor.value,
        primary = themeManager.accentColor.value,
        secondary = themeManager.tabText.value
    )

    MaterialTheme(
        colorScheme = dynamicColorScheme,
        content = content
    )
}
