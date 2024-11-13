package com.elTohamy.flushy.presentation.activities.team.tabRow.screens

import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.teams.squads.PlayersItem
import com.elTohamy.flushy.data.state.TeamPlayersState
import com.elTohamy.flushy.presentation.activities.players.PlayerActivity
import com.elTohamy.flushy.presentation.activities.team.viewModel.TeamViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette

@Composable
fun TeamPlayersScreen(team: Int, season: Int) {
    FetchTeamPlayers(team = team, season = season)
}

@Composable
fun FetchTeamPlayers(
    teamViewModel: TeamViewModel = hiltViewModel(), team: Int, season: Int
) {
    LaunchedEffect(key1 = teamViewModel.isTeamPlayersInitialized.value) {
        if (!teamViewModel.isTeamPlayersInitialized.value) {
            teamViewModel.getTeamPlayers(team)
            teamViewModel.isTeamPlayersInitialized.value = true
        }
    }

    when (val state = teamViewModel.teamPlayersState.collectAsState().value) {
        is TeamPlayersState.Empty -> {}
        is TeamPlayersState.Loading -> {}
        is TeamPlayersState.Error -> { state.message }
        is TeamPlayersState.Success -> {
            TeamPlayersList(
                playersItems = state.data.body()!!.response!![0]!!.players!!,
                season = season
            )
        }
    }
}

@Composable
fun TeamPlayersList(
    playersItems: List<PlayersItem?>,
    season: Int
) {
    val lazyGridState = rememberLazyGridState()
    val myList = playersItems.groupBy { it!!.position }
    val collapseState = remember(myList) {
        myList.map { false }.toMutableStateList()
    }
    val expandState = remember(playersItems) {
        playersItems.map { false }.toMutableStateList()
    }
    LazyVerticalGrid(
        state = lazyGridState,
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2)
    ) {
        header {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = "Current Squad",
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }
        myList.values.forEachIndexed { id, items ->
            val collapsed = collapseState[id]
            header {
                TeamPlayersHeader(
                    playersItem = items[id],
                    isCollapsed = collapseState[id]
                ) {
                    collapseState[id] = !collapsed
                }
            }
            if (!collapsed) {
                itemsIndexed(
                    items,
                    itemContent = { index, item ->
                        val expanded = expandState[index]
                        TeamPlayerItem(
                            playersItem = item,
                            isExpanded = expandState[index],
                            season = season
                        ) {
                            expandState[index] = !expanded
                        }
                    }
                )
            }
        }
    }
}

fun LazyGridScope.header(
    content: @Composable LazyGridItemScope.() -> Unit
) {
    item(span = { GridItemSpan(this.maxLineSpan) }, content = content)
}

@Composable
fun TeamPlayersHeader(
    playersItem: PlayersItem?,
    isCollapsed: Boolean, onClick: () -> Unit
) {
    val rotationState by animateFloatAsState(
        targetValue = if (isCollapsed) 180f else 0f, label = ""
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier,
                text = playersItem!!.position!!,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            IconButton(
                modifier = Modifier
                    .size(15.dp)
                    .rotate(rotationState)
                    .alpha(ContentAlpha.medium),
                onClick = { onClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropUp,
                    contentDescription = "Arrow Drop Up"
                )
            }
        }
    }
}

@Composable
fun TeamPlayerItem(
    playersItem: PlayersItem?,
    season: Int,
    isExpanded: Boolean, onClick: () -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Card(
        modifier = Modifier
            .width(screenWidth / 2)
            .height(IntrinsicSize.Max)
            .width(IntrinsicSize.Max)
            .padding(5.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("playerId", playersItem!!.id)
            intent.putExtra("currentSeason", season)
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ConstraintLayout {
                val (photo, name, number, age) = createRefs()
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .constrainAs(photo) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black
                    )
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(150.dp),
                        model = playersItem!!.photo,
                        contentDescription = "Player Photo"
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(name) {
                            top.linkTo(photo.bottom, margin = 5.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = (playersItem!!.name ?: stringResource(id = R.string.noName)),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Card(
                    modifier = Modifier
                        .constrainAs(number) {
                            top.linkTo(parent.top, margin = 5.dp)
                            start.linkTo(parent.start, margin = 5.dp)
                        },
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .size(20.dp)
                            .layout { measurable, constraints ->
                                val placeable =
                                    measurable.measure(
                                        // This is how wrapContent works
                                        constraints.copy(minWidth = 0, minHeight = 0)
                                    )
                                layout(constraints.maxWidth, constraints.maxHeight) {
                                    // This is how wrapContent alignment works
                                    val x = (constraints.maxWidth - placeable.width) / 2
                                    val y = (constraints.maxHeight - placeable.height) / 2
                                    placeable.placeRelative(x, y)
                                }
                            },
                        text = (playersItem.number ?: 0).toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .width(IntrinsicSize.Max)
                        .height(20.dp)
                        .animateContentSize(
                            animationSpec = tween(1000)
                        )
                        .constrainAs(age) {
                            top.linkTo(parent.top, margin = 5.dp)
                            end.linkTo(parent.end, margin = 5.dp)
                        },
                    onClick = { onClick() },
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Age :",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1
                        )
                        if (isExpanded) {
                            Text(
                                text = "${playersItem.age} Years Old",
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}