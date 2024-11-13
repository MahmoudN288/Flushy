package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.R

@Composable
fun LineupsPlayerComponent(primaryColor: Color, borderColor: Color, numberColor: Color, number: String, playerName: String) {
    val rectColors: List<Color> = listOf(
        primaryColor, primaryColor
    )
    val stripColors: List<Color> = listOf(
        primaryColor, borderColor.copy(0.5f)
    )
    //Number
    val textMeasurer = rememberTextMeasurer()
    val style = TextStyle(
        fontSize = 15.sp,
        color = numberColor
    )
    val textLayoutResult = remember(number) {
        textMeasurer.measure(number, style)
    }
    //Name
    val nameMeasurer = rememberTextMeasurer()
    val nameStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
    //Image
    val logoPainter: Painter = painterResource(id = R.drawable.c_tot)
    val goalPainter: Painter = painterResource(id = R.drawable.goal_lineups)
    val assistPainter: Painter = painterResource(id = R.drawable.assist)
    Canvas(modifier = Modifier
        .background(Color.Transparent)
        .width(90.dp)
        .height(120.dp)) {
        drawArc(
            color = borderColor,
            startAngle = 180f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(width = size.width / 3, height = size.height / 4),
            style = Stroke(width = 7f, cap = StrokeCap.Square),
            topLeft = Offset(x = center.x / 1.5f, y = center.y / 3)
        )
        drawArc(
            color = primaryColor,
            startAngle = 180f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(width = size.width / 2.863f, height = size.height / 3.5f),
            style = Stroke(width = 4f, cap = StrokeCap.Butt),
            topLeft = Offset(x = center.x / 1.54f, y = center.y / 3.6f)
        )
        //Back Border
        drawLine(
            color = borderColor,
            start = Offset(x = center.x / 1.5f, y = center.y / 1.8f),
            end = Offset(x = center.x * 1.325f, y = center.y / 1.8f),
            strokeWidth = 5f
        )
        drawArc(
            color = primaryColor.copy(0.4f),
            startAngle = 180f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(width = size.width / 3.2f, height = size.height / 4.065f),
            topLeft = Offset(x = center.x / 1.45f, y = center.y / 3.11f)
        )
        //Main Arc
        drawArc(
            color = primaryColor,
            startAngle = 170f,
            sweepAngle = -160f,
            useCenter = false,
            style = Stroke(width = 9f, cap = StrokeCap.Square),
            size = Size(width = size.width / 2.5f, height = size.height / 2.9f),
            topLeft = Offset(x = center.x / 1.67f, y = center.y / 5.31f)
        )
        //Main Rect
        drawRect(
            brush = Brush.linearGradient(rectColors),
            size = Size(width = size.width / 3, height = size.height / 2.76f),
            topLeft = Offset(x = center.x / 1.5f, y = center.y / 1.19f)
        )
        //Right Shadowed
        drawRect(
            brush = Brush.linearGradient(stripColors),
            size = Size(width = size.width / 12, height = size.height / 2.5f),
            topLeft = Offset(x = center.x * 1.335f, y = center.y / 1.3f)
        )
        //Right Strip
        drawArc(
            color = primaryColor,
            startAngle = 270f,
            sweepAngle = -45f,
            useCenter = false,
            style = Stroke(width = 5f, cap = StrokeCap.Square),
            size = Size(width = size.width / 5, height = size.height / 5),
            topLeft = Offset(x = center.x / 2.25f, y = center.y / 1.8f)
        )
        drawLine(
            color = primaryColor,
            start = Offset(x = center.x * 1.5f, y = center.y / 1.61f),
            end = Offset(x = center.x * 1.64f, y = center.y / 1.30f),
            strokeWidth = 5.2f
        )
        drawLine(
            color = primaryColor,
            start = Offset(x = center.x * 1.37f, y = center.y / 1.71f),
            end = Offset(x = center.x * 1.6f, y = center.y / 1.27f),
            strokeWidth = 16f
        )
        drawLine(
            color = borderColor,
            start = Offset(x = center.x * 1.55f, y = center.y / 1.25f),
            end = Offset(x = center.x * 1.65f, y = center.y / 1.3f),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        //Left Shadowed
        drawRect(
            brush = Brush.linearGradient(stripColors),
            size = Size(width = size.width / 12, height = size.height / 2.5f),
            topLeft = Offset(x = center.x / 2, y = center.y / 1.3f)
        )
        //Left Strip
        drawArc(
            color = primaryColor,
            startAngle = 270f,
            sweepAngle = 45f,
            useCenter = false,
            style = Stroke(width = 5f, cap = StrokeCap.Square),
            size = Size(width = size.width / 5, height = size.height / 5),
            topLeft = Offset(x = center.x * 1.152f, y = center.y / 1.8f)
        )
        drawLine(
            color = primaryColor,
            start = Offset(x = center.x / 2, y = center.y / 1.62f),
            end = Offset(x = center.x / 2.8f, y = center.y / 1.31f),
            strokeWidth = 5.2f
        )
        drawLine(
            color = primaryColor,
            start = Offset(x = center.x / 1.6f, y = center.y / 1.76f),
            end = Offset(x = center.x / 2.42f, y = center.y / 1.27f),
            strokeWidth = 14f
        )
        drawLine(
            color = borderColor,
            start = Offset(x = center.x / 2.25f, y = center.y / 1.25f),
            end = Offset(x = center.x / 2.85f, y = center.y / 1.3f),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        //Right Comp
        drawRect(
            brush = Brush.linearGradient(rectColors),
            size = Size(width = size.width / 19.5f, height = size.height / 20),
            topLeft = Offset(x = center.x * 1.232f, y = center.y / 1.3f)
        )
        //Left Comp
        drawRect(
            brush = Brush.linearGradient(rectColors),
            size = Size(width = size.width / 21, height = size.height / 20),
            topLeft = Offset(x = center.x / 1.5f, y = center.y / 1.3f)
        )
        //Left
        val pathLeftA = Path()
        pathLeftA.moveTo(x = center.x / 1.5f, y = center.y / 1.3f)
        pathLeftA.lineTo(x = center.x / 1.7f, y = center.y / 1.5f)
        pathLeftA.lineTo(x = center.x / 2f, y = center.y / 1.3f)
        drawPath(
            path = pathLeftA,
            brush = Brush.linearGradient(stripColors)
        )
        val pathLeftB = Path()
        pathLeftB.moveTo(x = center.x / 1.5f, y = center.y / 1.3f)
        pathLeftB.lineTo(x = center.x / 1.7f, y = center.y / 1.5f)
        pathLeftB.lineTo(x = center.x / 2f, y = center.y / 1.3f)
        drawPath(
            path = pathLeftB,
            brush = Brush.linearGradient(stripColors)
        )
        //Right
        val pathRightA = Path()
        pathRightA.moveTo(x = center.x * 1.335f, y = center.y / 1.3f)
        pathRightA.lineTo(x = center.x * 1.4f, y = center.y / 1.5f)
        pathRightA.lineTo(x = center.x * 1.5f, y = center.y / 1.3f)
        drawPath(
            path = pathRightA,
            brush = Brush.linearGradient(stripColors)
        )
        val pathRightB = Path()
        pathRightB.moveTo(x = center.x * 1.335f, y = center.y / 1.3f)
        pathRightB.lineTo(x = center.x * 1.4f, y = center.y / 1.5f)
        pathRightB.lineTo(x = center.x * 1.5f, y = center.y / 1.3f)
        drawPath(
            path = pathRightB,
            brush = Brush.linearGradient(stripColors)
        )
        //Number
        drawText(
            textMeasurer = textMeasurer,
            text = number,
            style = style,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width,
                y = center.y - textLayoutResult.size.height / 1.6f,
            )
        )
        //Name
        drawText(
            textMeasurer = nameMeasurer,
            text = playerName,
            style = nameStyle,
            topLeft = Offset(
                x = center.x / 2.4f,
                y = center.y * 1.6f,
            )
        )
        //Name
        drawText(
            textMeasurer = nameMeasurer,
            text = playerName,
            style = nameStyle,
            topLeft = Offset(
                x = center.x / 2.4f,
                y = center.y * 1.6f,
            )
        )
        //Images
        //Club Logo
        translate(
            left = center.x * 1.05f,
            top = center.y / 1.15f
        ) {
            with(logoPainter) {
                draw(size = Size(15.dp.toPx(), 15.dp.toPx()))
            }
        }
        //Goal
        translate(
            left = center.x * 1.05f,
            top = center.y / 1.15f
        ) {
            with(goalPainter) {
                draw(size = Size(15.dp.toPx(), 15.dp.toPx()))
            }
        }
        //Assist
        translate(
            left = center.x * 1.05f,
            top = center.y / 1.15f
        ) {
            with(assistPainter) {
                draw(size = Size(15.dp.toPx(), 15.dp.toPx()))
            }
        }
    }
}