package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.eventsScreen

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material3.ripple
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.EventsItem
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponseItem
import com.elTohamy.flushy.data.state.MatchInfoState
import com.elTohamy.flushy.presentation.activities.match.viewModel.MatchViewModel
import com.elTohamy.flushy.presentation.activities.players.PlayerActivity
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventsScreens(
    homeId: Int, awayId: Int, isRefreshing: Boolean, onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchEvents(
            homeId = homeId, awayId = awayId,
            isRefreshing = isRefreshing,
            onRefresh = { onRefresh() }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FetchEvents(
    matchViewModel: MatchViewModel = hiltViewModel(),
    homeId: Int, awayId: Int, isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    Column {
        when (val stateA = matchViewModel.matchInfoState.collectAsState().value) {
            is MatchInfoState.Empty -> {}
            is MatchInfoState.Loading -> {}
            is MatchInfoState.Error -> {}
            is MatchInfoState.Success -> {
                EventsLazy(
                    matchItem = stateA.data.body()!!.response?.get(0)!!,
                    eventsList = stateA.data.body()!!.response?.get(0)?.events!!,
                    homeId = homeId,
                    awayId = awayId,
                    isRefreshing = isRefreshing,
                    onRefresh = { onRefresh() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsLazy(
    matchItem: MatchByIdResponseItem, eventsList: List<EventsItem?>,
    homeId: Int, awayId: Int, isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val dark = LocalTheme.current.isDark
    val normalEventsList = eventsList.filter {
        it!!.comments != "Penalty Shootout"
    }
    val penaltiesList = eventsList.filter {
        it!!.comments == "Penalty Shootout"
    }
    val pullToRefreshState = rememberPullToRefreshState()

    val scaleFraction = {
        if (isRefreshing) 1f
        else LinearOutSlowInEasing.transform(pullToRefreshState.distanceFraction).coerceIn(0f, 1f)
    }
    if (matchItem.fixture!!.status!!.elapsed != null) {
        Box(
            modifier =
            Modifier.pullToRefresh(
                state = pullToRefreshState,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                state = rememberLazyListState()
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        text = stringResource(id = R.string.matchEvents)
                    )
                }
                item {
                    if (
                        matchItem.fixture.status!!.jsonMemberShort == "FT" ||
                        matchItem.fixture.status.jsonMemberShort == "AET" ||
                        matchItem.fixture.status.jsonMemberShort == "PEN"
                    ) {
                        BoxWithLayout {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .weight(5f)) {
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                                    .weight(1.5f)
                                    .height(1.dp)) {}
                                Column(
                                    modifier = Modifier.weight(2f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(
                                            id = if (dark) R.drawable.whistle_w else R.drawable.whistle_b
                                        ),
                                        contentDescription = "Whistle End"
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = "Match Finished",
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        ),
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )
                                }
                                Row(modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                                    .weight(1.5f)
                                    .height(1.dp)) {}
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                    )
                }
                items(normalEventsList.size) {
                    Event(
                        eventsItem = normalEventsList[it]!!,
                        homeId = homeId,
                        awayId = awayId,
                        isLast = (it == (normalEventsList.size -1)),
                        isFirst = (it == 0),
                        matchItem = matchItem
                    )
                }
                items(penaltiesList.size) {
                    PenaltyShootoutEvent(
                        eventsItem = penaltiesList[it]!!,
                        homeId = homeId,
                        awayId = awayId,
                        isLast = (it == (penaltiesList.size - 1)),
                        isFirst = (it == 0),
                        matchItem = matchItem
                    )
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
                item {
                    BoxWithLayout {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f)) {
                            Row(modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .weight(1.5f)
                                .height(1.dp)) {}
                            Column(
                                modifier = Modifier.weight(2f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(
                                        id = if (dark) R.drawable.whistle_w else R.drawable.whistle_b
                                    ),
                                    contentDescription = "Whistle Starts"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Match Started",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    ),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                            Row(modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .weight(1.5f)
                                .height(1.dp)) {}
                        }
                    }
                }
            }
            if(isRefreshing) {
                LaunchedEffect(true) {
                    onRefresh()
                }
            }

            Box(
                Modifier.align(Alignment.TopCenter).graphicsLayer {
                    scaleX = scaleFraction()
                    scaleY = scaleFraction()
                }
            ) {
                PullToRefreshDefaults.Indicator(state = pullToRefreshState, isRefreshing = isRefreshing)
            }
        }
    }
}

@Composable
fun Event(
    eventsItem: EventsItem, homeId: Int, awayId: Int,
    isLast: Boolean, isFirst: Boolean,
    matchItem: MatchByIdResponseItem
) {
    val context = LocalContext.current
    val dark = LocalTheme.current.isDark
    var expanded by remember { mutableStateOf(false) }
    var touchPoint: Offset by remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current
    // Create element height in pixel state
    var cardHeightPx by remember {
        mutableFloatStateOf(0f)
    }
    // Create element height in dp state
    var cardHeightDp by remember {
        mutableStateOf(0.dp)
    }

    if (eventsItem.comments != "Penalty Shootout") {
        Card(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures {
                        touchPoint = it
                        expanded = true
                    }
                }
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    cardHeightPx = coordinates.size.height.toFloat()
                    cardHeightDp = with(density) {
                        coordinates.size.height.toDp()
                    }
                }
                .fillMaxWidth(),
            shape =
            if (isLast) RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            else if (isFirst) RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            else RectangleShape,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (eventsItem.team!!.id == homeId) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(7f),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .weight(3f)
                                    .padding(horizontal = 4.dp, vertical = 10.dp)
                            ) {
                                BoxWithLayout {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(4f)
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(3f),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            Text(
                                                modifier = Modifier,
                                                text = eventsItem.player!!.name ?: "Player Name",
                                                style = TextStyle(
                                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp
                                                )
                                            )
                                            if (
                                                eventsItem.type == "Goal" ||
                                                eventsItem.type == "subst" ||
                                                eventsItem.type == "Var" ||
                                                eventsItem.type == "Card"
                                            ) {
                                                Text(
                                                    modifier = Modifier,
                                                    text =
                                                    if (eventsItem.type == "Goal" || eventsItem.type == "subst") {
                                                        if (eventsItem.assist!!.name != null) {
                                                            eventsItem.assist.name.toString()
                                                        } else {
                                                            ""
                                                        }
                                                    } else if (eventsItem.type == "Card") {
                                                        eventsItem.comments
                                                            ?: (eventsItem.detail ?: stringResource(id = R.string.notDefined))
                                                    } else {
                                                        if (eventsItem.detail != null) {
                                                            eventsItem.detail.toString()
                                                        } else {
                                                            ""
                                                        }
                                                    },
                                                    style = TextStyle(
                                                        color = if (dark) Color.LightGray else Color.Gray,
                                                        fontWeight = FontWeight.Normal,
                                                        fontSize = 12.sp
                                                    )
                                                )
                                            }
                                        }
                                        Image(
                                            modifier = Modifier
                                                .weight(1f)
                                                .size(25.dp),
                                            painter = when (eventsItem.type) {
                                                "Card" -> {
                                                    when (eventsItem.detail) {
                                                        "Yellow Card" -> {
                                                            painterResource(id = R.drawable.y_card)
                                                        }

                                                        "Red Card" -> {
                                                            painterResource(id = R.drawable.r_card)
                                                        }

                                                        else -> {
                                                            painterResource(id = R.drawable.y_card)
                                                        }
                                                    }
                                                }

                                                "subst" -> painterResource(id = R.drawable.sub)
                                                "Goal" -> painterResource(
                                                    id = when (eventsItem.detail) {
                                                        "Normal Goal" -> {
                                                            R.drawable.goal_lineups
                                                        }

                                                        "Penalty" -> {
                                                            if (dark) R.drawable.scored_pen_dark
                                                            else R.drawable.scored_pen_light
                                                        }

                                                        "Missed Penalty" -> {
                                                            R.drawable.missed_pen
                                                        }

                                                        else -> {
                                                            R.drawable.goal_lineups
                                                        }
                                                    }
                                                )

                                                "Var" -> painterResource(id = R.drawable.`var`)
                                                else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                            },
                                            contentDescription = "Event Icon"
                                        )
                                    }
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .weight(1f),
                                text = "${eventsItem.time!!.elapsed}'",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                            Row(
                                modifier = Modifier
                                    .weight(3f)
                                    .width(IntrinsicSize.Max),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                            }
                        }
                    }
                } else if (eventsItem.team.id == awayId) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max)
                                .weight(7f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(3f)
                                    .width(IntrinsicSize.Max),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                            }
                            Text(
                                modifier = Modifier
                                    .weight(1f),
                                text = eventsItem.time!!.elapsed.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                            Row(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .weight(3f)
                                    .padding(4.dp)
                            ) {
                                BoxWithLayout {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(4f)
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .weight(1f)
                                                .size(25.dp),
                                            painter = when (eventsItem.type) {
                                                "Card" -> {
                                                    when (eventsItem.detail) {
                                                        "Yellow Card" -> {
                                                            painterResource(id = R.drawable.y_card)
                                                        }

                                                        "Red Card" -> {
                                                            painterResource(id = R.drawable.r_card)
                                                        }

                                                        else -> {
                                                            painterResource(id = R.drawable.y_card)
                                                        }
                                                    }
                                                }

                                                "subst" -> painterResource(id = R.drawable.sub)
                                                "Goal" -> painterResource(
                                                    id = when (eventsItem.detail) {
                                                        "Normal Goal" -> {
                                                            R.drawable.goal_lineups
                                                        }

                                                        "Penalty" -> {
                                                            if (dark) R.drawable.scored_pen_dark
                                                            else R.drawable.scored_pen_light
                                                        }

                                                        "Missed Penalty" -> {
                                                            R.drawable.missed_pen
                                                        }

                                                        else -> {
                                                            R.drawable.goal_lineups
                                                        }
                                                    }
                                                )

                                                "Var" -> painterResource(id = R.drawable.`var`)
                                                else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                            },
                                            contentDescription = "Event Icon"
                                        )
                                        Column(
                                            modifier = Modifier.weight(3f),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                modifier = Modifier,
                                                text = eventsItem.player!!.name!!,
                                                style = TextStyle(
                                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp
                                                )
                                            )
                                            if (
                                                eventsItem.type == "Goal" ||
                                                eventsItem.type == "subst" ||
                                                eventsItem.type == "Var" ||
                                                eventsItem.type == "Card"
                                            ) {
                                                Text(
                                                    modifier = Modifier,
                                                    text =
                                                    if (eventsItem.type == "Goal" || eventsItem.type == "subst") {
                                                        if (eventsItem.assist!!.name != null) {
                                                            eventsItem.assist.name.toString()
                                                        } else {
                                                            ""
                                                        }
                                                    } else if (eventsItem.type == "Card") {
                                                        eventsItem.comments
                                                            ?: (eventsItem.detail ?: stringResource(id = R.string.notDefined))
                                                    } else {
                                                        if (eventsItem.detail != null) {
                                                            eventsItem.detail.toString()
                                                        } else {
                                                            ""
                                                        }
                                                    },
                                                    style = TextStyle(
                                                        color = if (dark) Color.LightGray else Color.Gray,
                                                        fontWeight = FontWeight.Normal,
                                                        fontSize = 12.sp
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            val (xDp, yDp) = with(density) {
                (touchPoint.x.toDp()) to (touchPoint.y.toDp())
            }
            DropdownMenu(
                expanded = expanded,
                offset = DpOffset(xDp, -cardHeightDp + yDp),
                onDismissRequest = {
                    expanded = false
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        if (eventsItem.player!!.id != null) {
                            val intent = Intent(context, PlayerActivity::class.java)
                            if (eventsItem.type != "subst") {
                                intent.putExtra("playerId", eventsItem.player.id)
                            } else {
                                intent.putExtra("playerId", eventsItem.assist!!.id)
                            }
                            intent.putExtra("currentSeason", matchItem.league!!.season)
                            context.startActivity(intent)
                            expanded = false
                        } else {
                            expanded = false
                        }
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier
                                .weight(2f)
                                .height(IntrinsicSize.Max),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .size(25.dp),
                                    painter = when (eventsItem.type) {
                                        "Card" -> {
                                            when (eventsItem.detail) {
                                                "Yellow Card" -> {
                                                    painterResource(id = R.drawable.y_card)
                                                }

                                                "Red Card" -> {
                                                    painterResource(id = R.drawable.r_card)
                                                }

                                                else -> {
                                                    painterResource(id = R.drawable.y_card)
                                                }
                                            }
                                        }

                                        "subst" -> painterResource(id = R.drawable.sub)
                                        "Goal" -> painterResource(
                                            id = when (eventsItem.detail) {
                                                "Normal Goal" -> {
                                                    R.drawable.goal_lineups
                                                }

                                                "Penalty" -> {
                                                    if (dark) R.drawable.scored_pen_dark
                                                    else R.drawable.scored_pen_light
                                                }

                                                "Missed Penalty" -> {
                                                    R.drawable.missed_pen
                                                }

                                                else -> {
                                                    R.drawable.goal_lineups
                                                }
                                            }
                                        )

                                        "Var" -> painterResource(id = R.drawable.`var`)
                                        else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                    },
                                    contentDescription = "Event Icon"
                                )
                            }
                            Text(
                                modifier = Modifier.weight(1f),
                                text = if (eventsItem.type != "subst") {
                                    eventsItem.player!!.name!!
                                } else {
                                    eventsItem.assist!!.name!!
                                },
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        if (eventsItem.type == "Goal") {
                            if (eventsItem.assist!!.name != null) {
                                val intent = Intent(context, PlayerActivity::class.java)
                                intent.putExtra("playerId", eventsItem.assist.id)
                                intent.putExtra("currentSeason", matchItem.league!!.season)
                                context.startActivity(intent)
                                expanded = false
                            }
                        } else if (eventsItem.type == "subst") {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", eventsItem.player!!.id)
                            intent.putExtra("currentSeason", matchItem.league!!.season)
                            context.startActivity(intent)
                            expanded = false
                        } else {
                            expanded = false
                        }
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier
                                .weight(2f)
                                .height(IntrinsicSize.Max),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(25.dp)
                                        .padding(3.dp),
                                    painter = when (eventsItem.type) {
                                        "Card" -> {
                                            when (eventsItem.detail) {
                                                "Yellow Card" -> {
                                                    painterResource(id = R.drawable.yellow_p)
                                                }

                                                "Red Card" -> {
                                                    painterResource(id = R.drawable.red_p)
                                                }

                                                else -> {
                                                    painterResource(id = R.drawable.red_p)
                                                }
                                            }
                                        }

                                        "subst" -> {
                                            painterResource(id = R.drawable.sub_out)
                                        }
                                        "Goal" -> {
                                            if (eventsItem.assist!!.name != null) {
                                                painterResource(id = R.drawable.assist)
                                            } else painterResource(id = R.drawable.flushy_logo_t)
                                        }
                                        else -> { painterResource(id = R.drawable.flushy_logo_t) }
                                    },
                                    contentDescription = "Event Icon"
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                text = when (eventsItem.type) {
                                    "Card" -> {
                                        eventsItem.comments ?: stringResource(id = R.string.notDefined)
                                    }
                                    "subst" -> {
                                        eventsItem.player!!.name ?: stringResource(id = R.string.notDefined)
                                    }
                                    "Goal" -> {
                                        eventsItem.assist!!.name ?: stringResource(id = R.string.notDefined)
                                    }
                                    "Var" -> {
                                        eventsItem.comments
                                            ?: (eventsItem.detail ?: stringResource(id = R.string.notDefined))
                                    }
                                    else -> {
                                        stringResource(id = R.string.notDefined)
                                    }
                                },
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "I Love It",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.love),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Let's Party",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.party),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "It's Cool",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.cool),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "I'm So Sad",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.sad),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "I'm Angry",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.angry),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PenaltyShootoutEvent(
    eventsItem: EventsItem, homeId: Int, awayId: Int,
    isLast: Boolean, isFirst: Boolean,
    matchItem: MatchByIdResponseItem
) {
    val context = LocalContext.current
    val dark = LocalTheme.current.isDark
    var expanded by remember { mutableStateOf(false) }
    var touchPoint: Offset by remember { mutableStateOf(Offset.Zero) }
    val density = LocalDensity.current
    // Create element height in pixel state
    var cardHeightPx by remember {
        mutableFloatStateOf(0f)
    }
    // Create element height in dp state
    var cardHeightDp by remember {
        mutableStateOf(0.dp)
    }

    if (
        eventsItem.type == "Goal" &&
        eventsItem.comments == "Penalty Shootout"
    ) {
        Card(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures {
                        touchPoint = it
                        expanded = true
                    }
                }
                .onGloballyPositioned { coordinates ->
                    // Set column height using the LayoutCoordinates
                    cardHeightPx = coordinates.size.height.toFloat()
                    cardHeightDp = with(density) {
                        coordinates.size.height.toDp()
                    }
                }
                .fillMaxWidth(),
            shape =
            if (isLast) RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            else if (isFirst) RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            else RectangleShape,
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (eventsItem.team!!.id == homeId) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(7f),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .weight(3f)
                                    .padding(horizontal = 4.dp, vertical = 10.dp)
                            ) {
                                BoxWithLayout {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(4f)
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(3f),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            Text(
                                                modifier = Modifier,
                                                text = eventsItem.player!!.name!!,
                                                style = TextStyle(
                                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp
                                                )
                                            )
                                        }
                                        Image(
                                            modifier = Modifier
                                                .weight(1f)
                                                .size(25.dp),
                                            painter = when (eventsItem.detail) {
                                                "Penalty" -> {
                                                    painterResource(
                                                        id =
                                                        if (dark) R.drawable.scored_pen_dark
                                                        else R.drawable.scored_pen_light
                                                    )
                                                }
                                                "Missed Penalty" -> {
                                                    painterResource(id = R.drawable.missed_pen)
                                                }
                                                else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                            },
                                            contentDescription = "Event Icon"
                                        )
                                    }
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .weight(1f),
                                text = "${eventsItem.time!!.elapsed}'",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                            Row(
                                modifier = Modifier
                                    .weight(3f)
                                    .width(IntrinsicSize.Max),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                            }
                        }
                    }
                } else if (eventsItem.team.id == awayId) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max)
                                .weight(7f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(3f)
                                    .width(IntrinsicSize.Max),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                            }
                            Text(
                                modifier = Modifier
                                    .weight(1f),
                                text = eventsItem.time!!.elapsed.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                            Row(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .weight(3f)
                                    .padding(4.dp)
                            ) {
                                BoxWithLayout {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(4f)
                                            .padding(vertical = 4.dp)
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .weight(1f)
                                                .size(25.dp),
                                            painter = when (eventsItem.detail) {
                                                "Penalty" -> {
                                                    painterResource(
                                                        id =
                                                        if (dark) R.drawable.scored_pen_dark
                                                        else R.drawable.scored_pen_light
                                                    )
                                                }
                                                "Missed Penalty" -> {
                                                    painterResource(id = R.drawable.missed_pen)
                                                }
                                                else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                            },
                                            contentDescription = "Event Icon"
                                        )
                                        Column(
                                            modifier = Modifier.weight(3f),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                modifier = Modifier,
                                                text = eventsItem.player!!.name!!,
                                                style = TextStyle(
                                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            val (xDp, yDp) = with(density) {
                (touchPoint.x.toDp()) to (touchPoint.y.toDp())
            }
            DropdownMenu(
                expanded = expanded,
                offset = DpOffset(xDp, -cardHeightDp + yDp),
                onDismissRequest = {
                    expanded = false
                }
            ) {
                DropdownMenuItem(
                    onClick = {
                        val intent = Intent(context, PlayerActivity::class.java)
                        intent.putExtra("playerId", eventsItem.player!!.id)
                        intent.putExtra("currentSeason", matchItem.league!!.season)
                        context.startActivity(intent)
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier
                                .weight(2f)
                                .height(IntrinsicSize.Max),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .size(25.dp),
                                    painter = when (eventsItem.detail) {
                                        "Penalty" -> {
                                            painterResource(
                                                id =
                                                if (dark) R.drawable.scored_pen_dark
                                                else R.drawable.scored_pen_light
                                            )
                                        }
                                        "Missed Penalty" -> {
                                            painterResource(id = R.drawable.missed_pen)
                                        }
                                        else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                    },
                                    contentDescription = "Event Icon"
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                text = eventsItem.player!!.name!!,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "I Love It",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.love),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Let's Party",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.party),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "It's Cool",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.cool),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "I'm So Sad",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.sad),
                                contentDescription = ""
                            )
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    text = {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "I'm Angry",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.angry),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun TryEvent(awayId: Int, type: String, detail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, color = MaterialTheme.colorScheme.primary),
                onClick = {}
            )
    ) {
        if (awayId == 0) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(7f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .weight(3f)
                            .padding(horizontal = 4.dp, vertical = 10.dp)
                    ) {
                        BoxWithLayout {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(4f)
                                    .padding(vertical = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.weight(3f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = "Mahmoud Nabil",
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = "Ayman Nabil",
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                }
                                Image(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(25.dp),
                                    painter = when (type) {
                                        "Card" -> {
                                            when (detail) {
                                                "Yellow Card" -> { painterResource(id = R.drawable.y_card) }
                                                "Red Card" -> { painterResource(id = R.drawable.r_card) }
                                                else -> { painterResource(id = R.drawable.y_card) }
                                            }
                                        }
                                        "subst" -> painterResource(id = R.drawable.sub)
                                        "Goal" -> painterResource(id = R.drawable.goal_lineups)
                                        "Var" -> painterResource(id = R.drawable.`var`)
                                        else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                    },
                                    contentDescription = "Event Icon"
                                )
                            }
                        }
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = "21",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                    Row(modifier = Modifier.weight(3f)) {
                    }
                }
            }
        } else if (awayId == 1) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .weight(7f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.weight(3f)) {
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = "51",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                    Row(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .weight(3f)
                            .padding(4.dp)
                    ) {
                        BoxWithLayout {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(4f)
                                    .padding(vertical = 4.dp)
                            ) {
                                Image(
                                    modifier = Modifier
                                        .weight(1f)
                                        .size(25.dp),
                                    painter = when (type) {
                                        "Card" -> {
                                            when (detail) {
                                                "Yellow Card" -> { painterResource(id = R.drawable.y_card) }
                                                "Red Card" -> { painterResource(id = R.drawable.r_card) }
                                                else -> { painterResource(id = R.drawable.y_card) }
                                            }
                                        }
                                        "subst" -> painterResource(id = R.drawable.sub)
                                        "Goal" -> painterResource(id = R.drawable.goal_lineups)
                                        "Var" -> painterResource(id = R.drawable.`var`)
                                        else -> painterResource(id = R.drawable.ic_launcher_foreground)
                                    },
                                    contentDescription = "Event Icon"
                                )
                                Column(
                                    modifier = Modifier.weight(3f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = "Nabil Mohamed",
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = "Fatima Mohamed",
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TryEventPrevA() {
    TryEvent(awayId = 1, type = "subst", detail = "Yellow Card")
}

@Preview(showBackground = true)
@Composable
fun TryEventPrevB() {
    TryEvent(awayId = 0, type = "subst", detail = "Yellow Card")
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    Column {
        TryEvent(awayId = 1, type = "Goal", detail = "Yellow Card")
        TryEvent(awayId = 0, type = "Card", detail = "Yellow Card")
        TryEvent(awayId = 1, type = "Goal", detail = "Yellow Card")
        TryEvent(awayId = 0, type = "Card", detail = "Yellow Card")
    }
}