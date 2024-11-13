package com.elTohamy.flushy.utils

import androidx.compose.runtime.compositionLocalOf

data class DarkTheme(val isDark: Boolean = false)

val LocalTheme = compositionLocalOf { DarkTheme() }