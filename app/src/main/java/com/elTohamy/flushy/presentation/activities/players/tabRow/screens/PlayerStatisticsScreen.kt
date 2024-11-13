package com.elTohamy.flushy.presentation.activities.players.tabRow.screens

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material3.Icon
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.players.PlayerStatisticsItem
import com.elTohamy.flushy.data.state.PlayerByIdState
import com.elTohamy.flushy.presentation.activities.players.viewModel.PlayerViewModel
import com.elTohamy.flushy.presentation.activities.tournament.TournamentActivity
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.presentation.ui.theme.Orange
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.getTournamentName

@Composable
fun PlayerStatisticsScreen() {
    FetchPlayerStats()
}

@Composable
fun FetchPlayerStats(
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    when (
        val state = playerViewModel.playerByIdState.collectAsState().value
    ) {
        is PlayerByIdState.Empty -> {}
        is PlayerByIdState.Loading -> {}
        is PlayerByIdState.Error -> {}
        is PlayerByIdState.Success -> {
            PlayerStatsPerLeague(
                playerStatsItems =
                state.data.body()!!.response!![0]!!.statistics
            )

        }
    }
}

@Composable
fun PlayerStatsPerLeague(
    playerStatsItems: List<PlayerStatisticsItem?>?
) {
    val lazyColumnState = rememberLazyListState()
    val myList = playerStatsItems!!.groupBy { it!!.league!!.id }
    val collapseState = remember(myList) { myList.map { false }.toMutableStateList() }
    if (myList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyColumnState
        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = stringResource(id = R.string.playerStatisticsHeader),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                )
            }
            myList.entries.forEachIndexed { id, items ->
                val collapsed = collapseState[id]
                item {
                    PlayerStatsHeader(
                        playerStatsItem = items.value[0],
                        isCollapsed = collapseState[id]
                    ) {
                        collapseState[id] = !collapsed
                    }
                }
                if (!collapsed) {
                    itemsIndexed(
                        items.value,
                        itemContent = { _, item ->
                            PlayerStatsItem(playerStatsItem = item)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerStatsHeader(
    playerStatsItem: PlayerStatisticsItem?,
    isCollapsed: Boolean, onClick: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = Intent(context, TournamentActivity::class.java)
                intent.putExtra("leagueId", playerStatsItem!!.league!!.id)
                intent.putExtra("currentSeason", playerStatsItem.league!!.season)
                intent.putExtra("leagueCountry", playerStatsItem.league.country)
                intent.putExtra("leagueName", playerStatsItem.league.name)
            },
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(2.3f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(25.dp),
                        model = playerStatsItem!!.league!!.logo,
                        contentDescription = "League Logo"
                    )
                    Text(
                        text = getTournamentName(
                            id = playerStatsItem.league!!.id!!,
                            jsonName = playerStatsItem.league.name!!
                        ),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        text = playerStatsItem.league.season.toString(),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier.weight(0.7f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        modifier = Modifier.size(25.dp),
                        model = playerStatsItem!!.team!!.logo,
                        contentDescription = "Club Logo"
                    )
                    val rotationState by animateFloatAsState(
                        targetValue = if (isCollapsed) 180f else 0f, label = ""
                    )
                    IconButton(
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(rotationState)
                            .alpha(ContentAlpha.medium),
                        onClick = { onClick() }
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Default.ArrowDropUp,
                            contentDescription = "Drop Down Arrow",
                            tint = MaterialTheme.customColorsPalette.blackToWhite
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerStatsItem(
    playerStatsItem: PlayerStatisticsItem?
) {
    val configuration = LocalConfiguration.current

    //val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .size(75.dp)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.goal_lineups),
                                    contentDescription = "Goal Icon"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.goals!!.total ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.playerGoals),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .size(75.dp)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.assist),
                                    contentDescription = "Goal Icon"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.goals!!.assists ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.playerAssist),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .size(75.dp)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.y_card),
                                    contentDescription = "Yellow Icon"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.cards!!.yellow ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.playerYellowCards),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .size(75.dp)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(35.dp),
                                    painter = painterResource(id = R.drawable.r_card),
                                    contentDescription = "Red Icon"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.cards!!.red ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.playerRedCards),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
            val linearGradient: List< Color> = listOf(
                MaterialTheme.colorScheme.primary.copy(0.5f),
                LightRed.copy(0.5f)
            )
            val barsLinearGradient: List<Color> = listOf(
                Color.White,
                Color.White
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
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
                                .fillMaxWidth()
                                .padding(
                                    start = 50.dp, end = 50.dp,
                                    bottom = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.offTarget),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.shots),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = (playerStatsItem!!.shots!!.total ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp
                                    ),
                                    maxLines = 1,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text =
                                (
                                    (playerStatsItem!!.shots!!.total ?: 0) -
                                    (playerStatsItem.shots!!.on ?: 0)
                                ).toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.width(screenWidth / 2),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 25.dp,
                                    end = 25.dp,
                                    bottom = 10.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.onTarget),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier,
                                text = (playerStatsItem!!.shots!!.on ?: 0).toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.playerGames),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerAppearances),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.games!!.appearences ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerStarts),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.games!!.lineups ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerSubOut),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.substitutes!!.out ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerSubIn),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.substitutes!!.inL ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerOnBench),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.substitutes!!.bench ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.playerOtherStats),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerNumber),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.games!!.number ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerRating),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                val playerRating = 
                                    if (playerStatsItem!!.games!!.rating != null) {
                                        playerStatsItem.games!!.rating!!.substring(0, 3)
                                    } else {
                                        "0.0"
                                    }
                                Card(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(3.dp)),
                                    elevation = CardDefaults.cardElevation(4.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor =
                                        if (playerRating.toDouble() in 0.1..3.9)
                                            Color.Red
                                        else if (playerRating.toDouble() in 4.0..5.9)
                                            LightRed
                                        else if (playerRating.toDouble() in 6.0..6.9)
                                            Orange
                                        else if (playerRating.toDouble() in 7.0..7.9)
                                            LightGreen
                                        else if (playerRating.toDouble() in 8.0..10.0)
                                            Color.Green
                                        else Color.White
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier.padding(4.dp),
                                        text = if (playerRating == "0.0")
                                            stringResource(id = R.string.playerNotInvolved)
                                        else playerRating,
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerMinutes),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.games!!.minutes ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPosition),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.games!!.position ?: "None"),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerCaptain),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.games!!.captain ?: false).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.playerPenalties),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPenWon),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.penalty!!.won ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPenScored),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.penalty!!.scored ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPenMiss),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.penalty!!.missed ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPenCom),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.penalty!!.commited ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPenSaved),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.penalty!!.saved ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.playerDuels),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerDuelsTotal),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.duels!!.total ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerDuelsWon),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.duels!!.won ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.playerFouls),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerFoulsComm),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.fouls!!.committed ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerFoulsDrawn),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.fouls!!.drawn ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .width(IntrinsicSize.Max)
                            .height(IntrinsicSize.Max),
                        shape = RoundedCornerShape(15.dp),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.playerGoalsStats),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerGoalsScored),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.goals!!.total ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerGoalsConceded),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.goals!!.conceded ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerGoalsSaved),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.goals!!.saves ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.playerDribbles),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerDribblesAttempts),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.dribbles!!.attempts ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerDribblesSuccess),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.dribbles!!.success ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerDribblesFailed),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (
                                        (playerStatsItem!!.dribbles!!.attempts ?: 0) -
                                        (playerStatsItem.dribbles!!.success ?: 0)
                                    ).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.playerPasses),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPassesTotal),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.passes!!.total ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPassesCompleted),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.passes!!.accuracy ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPassesMissed),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (
                                        (playerStatsItem!!.passes!!.total ?: 0) -
                                        (playerStatsItem.passes!!.accuracy ?: 0)
                                    ).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPassesAccuracy),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text =
                                    if (
                                        playerStatsItem!!.passes!!.accuracy != null &&
                                        playerStatsItem.passes!!.total != null
                                    ) {
                                        (((playerStatsItem.passes.accuracy ?: 0) * 100)
                                            .div(playerStatsItem.passes.total ?: 0)
                                                ).toString()
                                    } else {
                                        stringResource(id = R.string.playerNotInvolved)
                                    },
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerPassesKey),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.passes!!.key ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Text(
                                text = stringResource(id = R.string.playerTackles),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerTacklesTotal),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.tackles!!.total ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerTacklesBlocks),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.tackles!!.blocks ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.playerTacklesInter),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = (playerStatsItem!!.tackles!!.interceptions ?: 0).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
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