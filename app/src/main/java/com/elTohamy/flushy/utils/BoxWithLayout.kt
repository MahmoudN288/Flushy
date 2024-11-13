package com.elTohamy.flushy.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BoxWithLayout(content: @Composable RowScope.()->Unit){
    Row(
        modifier = Modifier.background(Color.Transparent)
    ) {
        content()
    }
}