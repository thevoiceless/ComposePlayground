package com.example.composeplayground.ui.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.composeplayground.ThemeSettings
import com.example.composeplayground.ThemeSettingsCompositionLocal

private val DarkColorPalette = darkColors(
        primary = Purple200,
        primaryVariant = Purple700,
        secondary = Teal200
)

private val LightColorPalette = lightColors(
        primary = Purple500,
        primaryVariant = Purple700,
        secondary = Teal200
)

enum class AppTheme {
    Dark,
    Light,
    Auto;

    companion object {
        fun fromOrdinal(ordinal: Int): AppTheme = values()[ordinal]
    }
}

/**
 * It's customary to define an object matching the name of our custom theme;
 * use it to provide access to the default material attributes and any custom attributes that we add
 */
object ComposePlaygroundTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}

@Composable
fun ThemedContent(
    themeSettings: ThemeSettings = ThemeSettings(LocalContext.current.getSharedPreferences("prefs", Context.MODE_PRIVATE)),
    content: @Composable ((AppTheme) -> Unit) -> Unit
) {
    val appTheme = themeSettings.themeFlow.collectAsState()
    val changeTheme: (AppTheme) -> Unit = { themeSettings.theme = it }

    CompositionLocalProvider(
        ThemeSettingsCompositionLocal provides themeSettings
    ) {
        when (appTheme.value) {
            AppTheme.Auto -> {
                if (isSystemInDarkTheme()) DarkTheme { content(changeTheme) }
                else LightTheme { content(changeTheme) }
            }
            AppTheme.Dark -> DarkTheme { content(changeTheme) }
            AppTheme.Light -> LightTheme { content(changeTheme) }
        }
    }
}

@Composable
fun ComposePlaygroundTheme(
    colors: Colors,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun DarkTheme(content: @Composable () -> Unit) {
    ComposePlaygroundTheme(
        colors = DarkColorPalette,
        content = content
    )
}

@Composable
fun LightTheme(content: @Composable () -> Unit) {
    ComposePlaygroundTheme(
        colors = LightColorPalette,
        content = content
    )
}
