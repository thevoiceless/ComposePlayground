package com.example.composeplayground

import android.content.SharedPreferences
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.content.edit
import com.example.composeplayground.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.Delegates

class ThemeSettings(
    private val prefs: SharedPreferences
) {
    private val persistedTheme = AppTheme.fromOrdinal(prefs.getInt("theme", AppTheme.Auto.ordinal))
    var theme: AppTheme by Delegates.observable(persistedTheme) { _, _, newTheme ->
        _themeFlow.value = newTheme
        prefs.edit { putInt("theme", newTheme.ordinal) }
    }

    private val _themeFlow: MutableStateFlow<AppTheme> = MutableStateFlow(theme)
    val themeFlow: StateFlow<AppTheme> = _themeFlow
}

val ThemeSettingsCompositionLocal = staticCompositionLocalOf<ThemeSettings> {
    error("No theme settings")
}