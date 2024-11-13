package com.elTohamy.flushy.components.searchComponents.searchComponents.data

import androidx.compose.runtime.MutableState

data class FavouriteData(
    var name: String,
    var description: String,
    var painter: Int,
    var expandedState: MutableState<Boolean>,
    var rotationState: Float? = null
)