package com.elTohamy.flushy.utils

enum class ViewState {
    COLLAPSED, EXPANDED;

    fun opposite() =
        if (this == COLLAPSED) EXPANDED
        else COLLAPSED

}