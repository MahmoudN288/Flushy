package com.elTohamy.flushy.presentation.animations.possession

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.presentation.ui.theme.DarkRed

@Composable
fun PossessionAnimation(
    receivedValue: String = "60%",
    teamHomePossessionValue: Int = receivedValue.substring(0, 2).toInt(),
    teamAwayPossessionValue: Int = 100 - teamHomePossessionValue
) {
    Column {
        val slices = listOf(
            Slice(
                value = teamHomePossessionValue.toFloat(),
                color = Color.Blue.copy(0.5f),
                possession = "$teamHomePossessionValue%"
            ),
            Slice(
                value = teamAwayPossessionValue.toFloat(),
                color = DarkRed.copy(0.5f),
                possession = "$teamAwayPossessionValue%"),
        )

        StackedBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            slices = slices
        )
    }
}

@Composable
private fun StackedBar(modifier: Modifier, slices: List<Slice>) {

    val textMeasurer = rememberTextMeasurer()

    val textLayoutResults = remember {
        mutableListOf<TextLayoutResult>().apply {
            slices.forEach {
                val textLayoutResult: TextLayoutResult =
                    textMeasurer.measure(
                        text = AnnotatedString(it.possession),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    )
                add(textLayoutResult)
            }
        }
    }

    Canvas(modifier = modifier.clip(RoundedCornerShape(15.dp))) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        var currentX = 0f
        slices.forEachIndexed { index: Int, slice: Slice ->
            val width = (slice.value) / 100f * canvasWidth

            // Draw Rectangles
            drawRoundRect(
                color = slice.color, topLeft = Offset(currentX, 0f),
                size = Size(width, canvasHeight)
            )

            // Draw Text
            val textSize = textLayoutResults[index].size
            val style = textLayoutResults[index].layoutInput.style
            drawText(
                textMeasurer = textMeasurer, 
                text = slice.possession, topLeft = Offset(
                    x = currentX + (width - textSize.height) / 2,
                    y = (canvasHeight - textSize.height) / 2
                ),
                style = style
            )

            // Update start position of next rectangle
            currentX += width
        }
    }
}

data class Slice(
    val value: Float, val color: Color, val possession: String
)

@Preview(showBackground = true, locale = "en")
@Composable
fun PossessionAnimationPreview() {
    PossessionAnimation()
}