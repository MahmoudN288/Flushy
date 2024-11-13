package com.elTohamy.flushy.presentation.animations.unidirectional

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.DarkRed
import com.elTohamy.flushy.utils.BoxWithLayout

@Composable
fun UnidirectionalProgressIndicator(
    statsType: String = "Possession",
    canvasSize: Dp = 50.dp,
    homeIndicatorValue: Int = 0,
    awayIndicatorValue: Int = 0,
    maxHomeIndicatorValue: Int = 100,
    maxAwayIndicatorValue: Int = 100,
    bigTextHomeFontSize: TextUnit = 10.sp,
    bigTextHomeColor: Color = MaterialTheme.colorScheme.onSurface,
    bigTextAwayFontSize: TextUnit = 10.sp,
    bigTextAwayColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundHomeIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundHomeIndicatorStrokeWidth: Float = 8f,
    foregroundHomeIndicatorStrokeWidth: Float = 8f,
    backgroundAwayIndicatorColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    backgroundAwayIndicatorStrokeWidth: Float = 8f,
    foregroundAwayIndicatorStrokeWidth: Float = 8f,
    bigTextHomeSuffix: String = "FH",
    smallHomeText: String = "Live",
    smallTextHomeFontSize: TextUnit = 8.sp,
    smallTextHomeColor: Color = DarkRed,
    bigTextAwaySuffix: String = "FH",
    smallAwayText: String = "Live",
    smallTextAwayFontSize: TextUnit = 8.sp,
    smallTextAwayColor: Color = DarkRed,
    gradientHomeForegroundColors: List<Color> = listOf(
        DarkRed,
        MaterialTheme.colorScheme.primary
    ),
    gradientAwayForegroundColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        DarkRed
    )
) {
    var allowedHomeIndicatorValue by remember {
        mutableIntStateOf(maxHomeIndicatorValue)
    }

    var allowedAwayIndicatorValue by remember {
        mutableIntStateOf(maxAwayIndicatorValue)
    }

    allowedHomeIndicatorValue = if (homeIndicatorValue <= maxHomeIndicatorValue) {
        homeIndicatorValue
    } else {
        maxHomeIndicatorValue
    }

    allowedAwayIndicatorValue = if (awayIndicatorValue <= maxAwayIndicatorValue) {
        awayIndicatorValue
    } else {
        maxAwayIndicatorValue
    }

    var animatedHomeIndicatorValue by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(key1 = allowedHomeIndicatorValue) {
        animatedHomeIndicatorValue = allowedHomeIndicatorValue.toFloat()
    }

    val receivedHomeValue by animateIntAsState(
        targetValue = allowedHomeIndicatorValue,
        animationSpec = tween(1000), label = ""
    )

    val receivedAwayValue by animateIntAsState(
        targetValue = allowedAwayIndicatorValue,
        animationSpec = tween(1000), label = ""
    )

    val animatedHomeBigTextColor by animateColorAsState(
        targetValue = if (allowedHomeIndicatorValue == 0)
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        else
            bigTextHomeColor,
        animationSpec = tween(1000), label = ""
    )

    val animatedAwayBigTextColor by animateColorAsState(
        targetValue = if (allowedAwayIndicatorValue == 0)
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        else
            bigTextAwayColor,
        animationSpec = tween(1000), label = ""
    )

    BoxWithLayout {
        Row(
            modifier = Modifier
                .weight(7f)
                .height(IntrinsicSize.Max)
                .padding(5.dp),
        ) {
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .weight(2.9f)
                    .fillMaxHeight()
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .weight(5f)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(0.8f)
                                .fillMaxHeight()
                                .padding(2.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            UnidirectionalElements(
                                bigText = receivedHomeValue,
                                bigTextFontSize = bigTextHomeFontSize,
                                bigTextColor = animatedHomeBigTextColor,
                                bigTextSuffix = bigTextHomeSuffix,
                                smallText = smallHomeText,
                                smallTextColor = smallTextHomeColor,
                                smallTextFontSize = smallTextHomeFontSize
                            )
                        }
                        Column(
                            modifier = Modifier.weight(4.2f)
                                .padding(horizontal = 2.dp)
                                .fillMaxHeight()
                                .size(canvasSize)
                                .drawBehind {
                                    backgroundHomeIndicator(
                                        indicatorColor = backgroundHomeIndicatorColor,
                                        indicatorStrokeWidth = backgroundHomeIndicatorStrokeWidth,
                                    )
                                    foregroundHomeIndicator(
                                        gradientColors = gradientHomeForegroundColors,
                                        indicatorStrokeWidth = foregroundHomeIndicatorStrokeWidth,
                                    )
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {}
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = statsType,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.5.sp
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .weight(2.9f)
                    .fillMaxHeight()
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .fillMaxWidth()
                            .weight(5f)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(4.2f)
                                .fillMaxHeight()
                                .padding(horizontal = 2.dp)
                                .size(canvasSize)
                                .drawBehind {
                                    backgroundAwayIndicator(
                                        indicatorColor = backgroundAwayIndicatorColor,
                                        indicatorStrokeWidth = backgroundAwayIndicatorStrokeWidth,
                                    )
                                    foregroundAwayIndicator(
                                        gradientColors = gradientAwayForegroundColors,
                                        indicatorStrokeWidth = foregroundAwayIndicatorStrokeWidth,
                                    )
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {}
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.8f)
                                .padding(2.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            UnidirectionalElements(
                                bigText = receivedAwayValue,
                                bigTextFontSize = bigTextAwayFontSize,
                                bigTextColor = animatedAwayBigTextColor,
                                bigTextSuffix = bigTextAwaySuffix,
                                smallText = smallAwayText,
                                smallTextColor = smallTextAwayColor,
                                smallTextFontSize = smallTextAwayFontSize
                            )
                        }
                    }
                }
            }
        }
    }
}

//Home
fun DrawScope.backgroundAwayIndicator(
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    drawLine(
        start = Offset(x = canvasWidth, y = canvasHeight / 2),
        end = Offset(x = 0f, y = canvasHeight / 2),
        color = indicatorColor,
        strokeWidth = indicatorStrokeWidth,
        cap = StrokeCap.Round
    )
}

fun DrawScope.foregroundAwayIndicator(
    gradientColors: List<Color>,
    indicatorStrokeWidth: Float
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    drawLine(
        start = Offset(x = canvasWidth, y = canvasHeight / 2),
        end = Offset(x = 0f, y = canvasHeight / 2),
        brush = Brush.linearGradient(colors = gradientColors),
        strokeWidth = indicatorStrokeWidth,
        cap = StrokeCap.Round
    )
}

//Away
fun DrawScope.backgroundHomeIndicator(
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    drawLine(
        start = Offset(x = canvasWidth, y = canvasHeight / 2),
        end = Offset(x = 0f, y = canvasHeight / 2),
        color = indicatorColor,
        strokeWidth = indicatorStrokeWidth,
        cap = StrokeCap.Round
    )
}

fun DrawScope.foregroundHomeIndicator(
    indicatorStrokeWidth: Float,
    gradientColors: List<Color>
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    drawLine(
        brush = Brush.linearGradient(colors = gradientColors),
        start = Offset(x = canvasWidth, y = canvasHeight / 2),
        end = Offset(x = 0f, y = canvasHeight / 2),
        strokeWidth = indicatorStrokeWidth,
        cap = StrokeCap.Round
    )
}

@Composable
fun UnidirectionalElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextColor: Color,
    smallTextFontSize: TextUnit
) {
    Text(
        modifier = Modifier
            .width(18.dp),
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Text(
        text = "$bigText ${bigTextSuffix.take(2)}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun UnidirectionalProgressIndicatorPreview() {
    UnidirectionalProgressIndicator()
}