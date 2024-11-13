package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elTohamy.flushy.data.state.LineupsState
import com.elTohamy.flushy.data.state.MatchInfoState
import com.elTohamy.flushy.data.state.PlayersStatsByFixState
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponseItem
import com.elTohamy.flushy.data.remote.dto.fixtures.lineups.LineupsResponseItem
import com.elTohamy.flushy.data.remote.dto.fixtures.players.PlayersFixtureStatsResponseItem
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.screens.AwayTeam
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.screens.HomeTeam
import com.elTohamy.flushy.presentation.activities.match.viewModel.MatchViewModel
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun LineupsScreens(id: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val matchViewModel: MatchViewModel = hiltViewModel()
        FetchLineups(matchViewModel = matchViewModel, id = id)
    }
}

@Composable
fun FetchLineups(
    matchViewModel: MatchViewModel = hiltViewModel(),
    id: Int
) {
    LaunchedEffect(key1 = null) {
        if (!matchViewModel.isInitializedLineups.value) {
            matchViewModel.getLineupsByFixtureId(id)
            matchViewModel.isInitializedLineups.value = true
        }
    }
    LaunchedEffect(key1 = null) {
        if (!matchViewModel.isInitializedPlayersStatsByFix.value) {
            matchViewModel.getPlayersStatsByFixtureId(id)
            matchViewModel.isInitializedPlayersStatsByFix.value = true
        }
    }
    when (val stateLineups = matchViewModel.lineupsState.collectAsState().value) {
        is LineupsState.Empty -> {}
        is LineupsState.Loading -> {}
        is LineupsState.Error -> {}
        is LineupsState.Success -> {
            when (val statePlayersState = matchViewModel.playersStatsByFixState.collectAsState().value) {
                is PlayersStatsByFixState.Empty -> {}
                is PlayersStatsByFixState.Loading -> {}
                is PlayersStatsByFixState.Error -> {}
                is PlayersStatsByFixState.Success -> {
                    when (val eventsState = matchViewModel.matchInfoState.collectAsState().value) {
                        is MatchInfoState.Empty -> {}
                        is MatchInfoState.Loading -> {}
                        is MatchInfoState.Error -> {}
                        is MatchInfoState.Success -> {
                            CustomTabSample(
                                list = listOf(
                                    stateLineups.data.body()!!.response!![0]!!.team!!.name!!,
                                    stateLineups.data.body()!!.response!![1]!!.team!!.name
                                ),
                                lineupsResponseItemList = stateLineups.data.body()!!.response!!,
                                playersStatsList = statePlayersState.data.body()!!.response!!,
                                matchByIdResponseItem = eventsState.data.body()!!.response!![0]!!
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTabs(
    list: List<String?>,
    lineupsResponseItemList: List<LineupsResponseItem?>,
    playersStatsList: List<PlayersFixtureStatsResponseItem?>,
    matchByIdResponseItem: MatchByIdResponseItem
) {
    val dark = LocalTheme.current.isDark
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .background(if (dark) Black else White)
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(50)),
            indicator = { tabPositions: List<TabPosition> ->
                Box {}
            }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    modifier = if (selected) Modifier
                        .height(60.dp)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            White
                        )
                    else Modifier
                        .height(60.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            MaterialTheme.colorScheme.primary
                        ),
                    selected = selected,
                    onClick = { selectedIndex = index },
                    text = {
                        Text(
                            text = text!!,
                            color = if (selected) MaterialTheme.colorScheme.primary
                            else White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
            }
        }
        when (selectedIndex) {
            0 -> {
                HomeTeam(
                    lineupsResponseItemList = lineupsResponseItemList,
                    playersStatsList = playersStatsList,
                    matchByIdResponseItem = matchByIdResponseItem
                )
            }
            1 -> {
                AwayTeam(
                    lineupsResponseItemList = lineupsResponseItemList,
                    playersStatsList = playersStatsList,
                    matchByIdResponseItem = matchByIdResponseItem
                )
            }
        }
    }
}

@Composable
private fun MyTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(
                width = indicatorWidth,
            )
            .offset(
                x = indicatorOffset,
            )
            .clip(
                shape = CircleShape,
            )
            .background(
                color = indicatorColor,
            ),
    )
}

@Composable
private fun MyTabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            White
        } else {
            Black
        },
        animationSpec = tween(easing = LinearEasing), label = "",
    )
    Text(
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .width(tabWidth)
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp,
            ),
        text = text,
        color = tabTextColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun CustomTab(
    selectedItemIndex: Int,
    items: List<String?>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 180.dp,
    onClick: (index: Int) -> Unit,
    lineupsResponseItemList: List<LineupsResponseItem?>,
    playersStatsList: List<PlayersFixtureStatsResponseItem?>,
    matchByIdResponseItem: MatchByIdResponseItem
) {
    val dark = LocalTheme.current.isDark
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing), label = "",
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(if (dark) LightGray else Gray)
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        MyTabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = MaterialTheme.colorScheme.primary,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clip(CircleShape),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                MyTabItem(
                    isSelected = isSelected,
                    onClick = {
                        onClick(index)
                    },
                    tabWidth = tabWidth,
                    text = text!!,
                )
            }
        }
    }
    when (selectedItemIndex) {
        0 -> {
            HomeTeam(
                lineupsResponseItemList = lineupsResponseItemList,
                playersStatsList = playersStatsList,
                matchByIdResponseItem = matchByIdResponseItem
            )
        }
        1 -> {
            AwayTeam(
                lineupsResponseItemList = lineupsResponseItemList,
                playersStatsList = playersStatsList,
                matchByIdResponseItem = matchByIdResponseItem
            )
        }
    }
}

@Composable
fun CustomTabSample(
    list: List<String?>,
    lineupsResponseItemList: List<LineupsResponseItem?>,
    playersStatsList: List<PlayersFixtureStatsResponseItem?>,
    matchByIdResponseItem: MatchByIdResponseItem
) {
    val (selected, setSelected) = rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTab(
            items = list,
            selectedItemIndex = selected,
            onClick = setSelected,
            lineupsResponseItemList = lineupsResponseItemList,
            playersStatsList = playersStatsList,
            matchByIdResponseItem = matchByIdResponseItem
        )
    }
}