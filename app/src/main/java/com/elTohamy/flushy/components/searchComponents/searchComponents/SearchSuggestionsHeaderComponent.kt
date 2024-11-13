package com.elTohamy.flushy.components.searchComponents.searchComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.ripple

@Composable
fun SearchSuggestionsHeaderComponent() {
    Row(
        modifier = Modifier.wrapContentHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                onClick = { },
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(start = 3.dp, top = 3.dp, bottom = 3.dp),
            text = "Section Header Name",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
fun SearchSuggestionsHeaderComponentPreview() {
    SearchSuggestionsHeaderComponent()
}