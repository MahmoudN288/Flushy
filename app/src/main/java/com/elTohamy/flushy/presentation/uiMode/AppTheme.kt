package com.elTohamy.flushy.presentation.uiMode

enum class AppTheme {
    MODE_DAY,
    MODE_NIGHT,
    MODE_AUTO;

    companion object {
        fun fromOrdinal(ordinal: Int) = entries[ordinal]
    }
}