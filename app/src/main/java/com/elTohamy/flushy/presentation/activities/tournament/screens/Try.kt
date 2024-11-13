package com.elTohamy.flushy.presentation.activities.tournament.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun Standing() {
    val dark = LocalTheme.current.isDark
    NavigationItemsPrev()
}

@Composable
private fun DropDownSample() {
    var expanded by remember { mutableStateOf(false) }
    var touchPoint: Offset by remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current

    BoxWithConstraints(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Cyan)
            .pointerInput(Unit) {
                detectTapGestures {
                    Log.d("TAG", "onCreate: ${it}")
                    touchPoint = it
                    expanded = true

                }

            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            Text(
                text = "Hello",
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        val (xDp, yDp) = with(density) {
            (touchPoint.x.toDp()) to (touchPoint.y.toDp())
        }
        DropdownMenu(
            expanded = expanded,
            offset = DpOffset(xDp, -maxHeight + yDp),
            onDismissRequest = {
                expanded = false
            }
        ) {

            DropdownMenuItem(
                onClick = {
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Text("Copy")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Text("Get Balance")
                }
            )
        }
    }
}

@Composable
inline fun NavigationItems(
    topIconButtonId: Int,
    noinline onTopIconButtonClick: () -> Unit,
    tabIndex: Int,
    crossinline onTabIndexChanged: (Int) -> Unit,
    contentC: @Composable ColumnScope.(@Composable (Int, String, Int) -> Unit) -> Unit,
    contentR: @Composable RowScope.(@Composable (Int, String, Int) -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    val dark = LocalTheme.current.isDark
    val isLandscapeA = isLandscape
    val transition = updateTransition(targetState = tabIndex, label = null)
    if (isLandscapeA) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(50.dp)
            ) {
                Image(
                    painter = painterResource(topIconButtonId),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onTopIconButtonClick)
                        .padding(all = 12.dp)
                        .size(22.dp)
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                contentC { index, text, icon ->
                    val dothAlpha by transition.animateFloat(label = "") {
                        if (it == index) 1f else 0f
                    }
                    val iconContent: @Composable () -> Unit = {
                        Image(
                            modifier = Modifier
                                .size(16.dp)
                                .graphicsLayer {
                                    alpha = dothAlpha
                                    translationX = (1f - dothAlpha) * -48.dp.toPx()
                                },
                            painter = painterResource(id = icon),
                            contentDescription = "Equalizer"
                        )
                    }
                    val textContent: @Composable () -> Unit = {
                        BasicText(
                            text = text,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                    }
                    val contentModifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickable(onClick = { onTabIndexChanged(index) })
                    Column(
                        modifier = contentModifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        iconContent()
                        textContent()
                    }
                }
            }
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .horizontalScroll(rememberScrollState())
                .padding(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(50.dp)
            ) {
                Image(
                    painter = painterResource(topIconButtonId),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onTopIconButtonClick)
                        .padding(all = 12.dp)
                        .size(22.dp)
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                contentR { index, text, icon ->
                    val dothAlpha by transition.animateFloat(label = "") {
                        if (it == index) 1f else 0f
                    }
                    val iconContent: @Composable () -> Unit = {
                        Image(
                            modifier = Modifier
                                .size(12.dp)
                                .graphicsLayer {
                                    alpha = dothAlpha
                                    translationY = (1f - dothAlpha) * -48.dp.toPx()
                                },
                            painter = painterResource(id = icon),
                            contentDescription = "Equalizer"
                        )
                    }
                    val textContent: @Composable () -> Unit = {
                        BasicText(
                            text = text,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                    }
                    val contentModifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .clickable(onClick = { onTabIndexChanged(index) })
                    Column(
                        modifier = contentModifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        iconContent()
                        textContent()
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Scaffold(
    topIconButtonId: Int,
    onTopIconButtonClick: () -> Unit,
    tabIndex: Int,
    onTabChanged: (Int) -> Unit,
    tabColumnContent: @Composable ColumnScope.(@Composable (Int, String, Int) -> Unit) -> Unit,
    tabRowContent: @Composable RowScope.(@Composable (Int, String, Int) -> Unit) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable AnimatedVisibilityScope.(Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        NavigationItems(
            topIconButtonId = topIconButtonId,
            onTopIconButtonClick = onTopIconButtonClick,
            tabIndex = tabIndex,
            onTabIndexChanged = onTabChanged,
            contentC = tabColumnContent,
            contentR = tabRowContent
        )

        AnimatedContent(
            targetState = tabIndex,
            transitionSpec = {
                val slideDirection = when (targetState > initialState) {
                    true -> AnimatedContentTransitionScope.SlideDirection.Left
                    false -> AnimatedContentTransitionScope.SlideDirection.Right
                }

                val animationSpec = spring(
                    dampingRatio = 0.9f,
                    stiffness = Spring.StiffnessLow,
                    visibilityThreshold = IntOffset.VisibilityThreshold
                )

                slideIntoContainer(slideDirection, animationSpec) with
                        slideOutOfContainer(slideDirection, animationSpec)
            },
            content = content, label = ""
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun NavigationItemsPrev() {
    Scaffold(
        topIconButtonId = R.drawable.equalizer,
        onTopIconButtonClick = { /*TODO*/ },
        tabIndex = 0,
        onTabChanged = {},
        tabColumnContent = { item ->
            item(0, "Quick picks", R.drawable.sparkles)
            item(1, "Songs", R.drawable.musical_notes)
            item(2, "Playlists", R.drawable.playlist)
            item(3, "Artists", R.drawable.person)
            item(4, "Albums", R.drawable.disc)
        },
        tabRowContent = { item ->
            item(0, "Quick picks", R.drawable.sparkles)
            item(1, "Songs", R.drawable.musical_notes)
            item(2, "Playlists", R.drawable.playlist)
            item(3, "Artists", R.drawable.person)
            item(4, "Albums", R.drawable.disc)
        }
    ) { currentTabIndex ->
        when (currentTabIndex) {
            0 -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "QuickBits")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevA() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        Row(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 3.dp),
                        text = "2024-04-30",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = "Tuesday",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = "09:00",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        ),
                        maxLines = 1
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(4f)
                    .width(IntrinsicSize.Max)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(2f)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Image(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(id = R.drawable.c_tot),
                                    contentDescription = "Club Home"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Tottenham",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = "3",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Image(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(id = R.drawable.c_tot),
                                    contentDescription = "Club Home"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Tottenham",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = "3",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = 1)
@Composable
fun Prev() {
    val linearGradient: List< Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(0.5f),
        LightRed.copy(0.5f)
    )
    val barsLinearGradient: List<Color> = listOf(
        Color.White,
        Color.White
    )
    val configuration = LocalConfiguration.current

    //val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val dark = LocalTheme.current
    Column(
        modifier = Modifier
            .background(Color.Black)
            .drawBehind {
                //Vertical Rectangle Left
                drawRect(
                    brush = Brush.linearGradient(linearGradient),
                    topLeft = Offset(x = center.x / 5, y = 0f),
                    size = Size(width = size.width / 1.25f, height = size.height)
                )
                //Horizontal Bar
                drawRect(
                    brush = Brush.linearGradient(barsLinearGradient),
                    topLeft = Offset(x = center.x / 2.2f, y = center.y / 1.2f),
                    size = Size(width = size.width / 1.8f, height = size.height / 9)
                )
                //Vertical Bar Left
                drawRect(
                    brush = Brush.linearGradient(barsLinearGradient),
                    topLeft = Offset(x = center.x / 2.2f, y = center.y),
                    size = Size(width = size.width / 43, height = size.height / 2)
                )
                //Vertical Bar Right
                drawRect(
                    brush = Brush.linearGradient(barsLinearGradient),
                    topLeft = Offset(x = center.x / 0.658f, y = center.y),
                    size = Size(width = size.width / 43, height = size.height / 2)
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 3.dp, vertical = 8.dp)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.Top
        ) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(4f)
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val shotsOffGoalHomeText = "25"
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = shotsOffGoalHomeText,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        text = stringResource(id = R.string.offTarget),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                    val shotsOffGoalAwayText = "12"
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = shotsOffGoalAwayText,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Row(
            modifier = Modifier.width(screenWidth / 2)
        ) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(4f)
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val shotsOnGoalHomeText = "9"
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = shotsOnGoalHomeText,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        text = stringResource(id = R.string.onTarget),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                    val shotsOnGoalAwayText = "7"
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        text = shotsOnGoalAwayText,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

fun Modifier.vertical(enabled: Boolean = true) =
    if (enabled)
        layout { measurable, constraints ->
            val placeable = measurable.measure(constraints.copy(maxWidth = Int.MAX_VALUE))
            layout(placeable.height, placeable.width) {
                placeable.place(
                    x = -(placeable.width / 2 - placeable.height / 2),
                    y = -(placeable.height / 2 - placeable.width / 2)
                )
            }
        } else this

val isLandscape
    @Composable
    @ReadOnlyComposable
    get() = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

@Composable
fun FootballPitch(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxWidth().height(300.dp)) { // Adjust height as needed
        val pitchWidth = size.width
        val pitchHeight = size.height
        val centerX = pitchWidth /2
        val centerY = pitchHeight / 2
        val penaltyAreaWidth = pitchWidth * 0.25f
        val penaltyAreaHeight = pitchHeight * 0.4f
        val goalPostWidth = pitchWidth * 0.05f
        val centerCircleRadius = pitchWidth * 0.1f
        val penaltySpotRadius = 5.dp.toPx()

        // Fill the pitch with green color
        drawRect(
            color = Color(0xFF32CD32), // Green color
            topLeft = Offset.Zero,
            size = size
        )

        // Draw outer lines
        drawRect(
            color = Color.White,
            topLeft = Offset.Zero,
            size = size,
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw center line
        drawLine(
            color = Color.White,
            start = Offset(centerX, 0f),
            end = Offset(centerX, pitchHeight),
            strokeWidth = 2.dp.toPx()
        )

        // Draw center circle
        drawCircle(color = Color.White,
            center = Offset(centerX, centerY),
            radius = centerCircleRadius,
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw center spot
        drawCircle(
            color = Color.White,
            center = Offset(centerX, centerY),
            radius = penaltySpotRadius
        )

        // Draw left penalty area
        drawRect(
            color = Color.White,
            topLeft = Offset(0f, (pitchHeight - penaltyAreaHeight) / 2),
            size = Size(penaltyAreaWidth, penaltyAreaHeight),
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw left goal post
        drawRect(
            color = Color.White,
            topLeft = Offset(0f, (pitchHeight - goalPostWidth) / 2),
            size = Size(goalPostWidth / 2, goalPostWidth),
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw left penalty spot
        drawCircle(
            color = Color.White,
            center = Offset(penaltyAreaWidth * 0.4f, centerY),
            radius = penaltySpotRadius
        )

        // Draw right penalty area (mirrored)
        drawRect(
            color = Color.White,
            topLeft = Offset(pitchWidth - penaltyAreaWidth, (pitchHeight - penaltyAreaHeight) / 2),
            size = Size(penaltyAreaWidth, penaltyAreaHeight),
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw right goal post (mirrored)
        drawRect(
            color = Color.White,
            topLeft = Offset(pitchWidth - goalPostWidth / 2, (pitchHeight - goalPostWidth) / 2),
            size = Size(goalPostWidth / 2, goalPostWidth),
            style = Stroke(width = 2.dp.toPx())
        )

        // Draw right penalty spot (mirrored)
        drawCircle(
            color = Color.White,
            center = Offset(pitchWidth - penaltyAreaWidth * 0.4f, centerY),
            radius = penaltySpotRadius
        )

        // TODO: Addplayer positions as needed
    }
}

@Preview(showBackground = true)
@Composable
fun Prev2() {
    FootballPitch()
}