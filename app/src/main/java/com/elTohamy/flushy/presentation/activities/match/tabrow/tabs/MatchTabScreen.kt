package com.elTohamy.flushy.presentation.activities.match.tabrow.tabs

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponseItem
import com.elTohamy.flushy.data.state.MatchInfoState
import com.elTohamy.flushy.data.state.MatchLeagueInfoState
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.MatchTeamsStatisticsScreens
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.StandingsScreens
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.detailsScreen.DetailsScreens
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.eventsScreen.EventsScreens
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.LineupsScreens
import com.elTohamy.flushy.presentation.activities.match.viewModel.MatchViewModel
import com.elTohamy.flushy.presentation.activities.team.TeamActivity
import com.elTohamy.flushy.presentation.animations.shimmer.MatchActivityShimmer
import com.elTohamy.flushy.presentation.components.InPlayMatchItem
import com.elTohamy.flushy.presentation.components.MatchFinishedItem
import com.elTohamy.flushy.presentation.components.NotAvailable
import com.elTohamy.flushy.presentation.components.NotPlayedMatchItem
import com.elTohamy.flushy.presentation.components.StoppedMatchItem
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.Constants
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.customTabIndicatorOffset
import com.elTohamy.flushy.utils.findActivity
import com.elTohamy.flushy.utils.getTimeZone
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbar
import me.onebone.toolbar.rememberCollapsingToolbarState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MatchTabScreen(matchId: Int, homeId: Int, awayId: Int, leagueId: Int) {
    val dark = LocalTheme.current.isDark
    val context = LocalContext.current
    val tabData = getTabList()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabData.size })
    val collapsingState = rememberCollapsingToolbarState()
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.customColorsPalette.mainAppBar),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth()
                    ) {
                        CollapsingToolbar(
                            collapsingToolbarState = collapsingState
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                IconButton(
                                    modifier = Modifier.width(25.dp),
                                    onClick = { context.findActivity()?.finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Arrow Back",
                                        tint = if (dark) Color.White else Color.Black
                                    )
                                }
                                Row(
                                    modifier = Modifier,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(
                                        modifier = Modifier
                                            .width(50.dp)
                                            .padding(horizontal = 5.dp),
                                        onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Share,
                                            contentDescription = "Share",
                                            tint = if (dark) Color.White else Color.Black
                                        )
                                    }
                                    IconButton(
                                        modifier = Modifier
                                            .width(50.dp)
                                            .padding(horizontal = 5.dp),
                                        onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.NotificationsActive,
                                            contentDescription = "Follow Game",
                                            tint = if (dark) Color.White else Color.Black
                                        )
                                    }
                                }
                            }
                        }
                        FetchMatchData()
                        TabLayout(tabData, pagerState)
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            TabContent(
                pagerState, homeId = homeId, awayId = awayId, matchId, leagueId = leagueId
            )
        }
    }
}

@Composable
fun TabLayout(tabData: List<Int>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabData.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            divider = {
                Spacer(modifier = Modifier.height(1.dp))
            },
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.customTabIndicatorOffset(
                        currentTabPosition = tabPositions[pagerState.currentPage],
                        tabWidth = tabWidths[pagerState.currentPage]
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.customColorsPalette.mainAppBar)
                .wrapContentHeight()
        ) {
            tabData.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(MaterialTheme.customColorsPalette.mainAppBar),
                    selected = pagerState.currentPage == index, onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = title),
                            onTextLayout = { textLayoutResult ->
                                tabWidths[index] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            }
                        )
                    }
                )
            }
        }
    } else {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            divider = {
                Spacer(modifier = Modifier.height(1.dp))
            },
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.customTabIndicatorOffset(
                        currentTabPosition = tabPositions[pagerState.currentPage],
                        tabWidth = tabWidths[pagerState.currentPage]
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.customColorsPalette.mainAppBar)
                .wrapContentHeight()
        ) {
            tabData.forEachIndexed { index, title ->
                Tab(
                    modifier = Modifier.background(MaterialTheme.customColorsPalette.mainAppBar),
                    selected = pagerState.currentPage == index, onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = stringResource(id = title),
                            onTextLayout = { textLayoutResult ->
                                tabWidths[index] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            }
                        )
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabContent(
    pagerState: PagerState, homeId: Int, awayId: Int, matchId: Int, leagueId: Int,
    matchViewModel: MatchViewModel = hiltViewModel()
) {
    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            // fetch something
            delay(1500)
            itemCount += 5
            isRefreshing = false
        }
    }
    var isEventsAvailable by remember {
        mutableStateOf(false)
    }
    var isLineupsAvailable by remember {
        mutableStateOf(false)
    }
    var isStatisticsAvailable by remember {
        mutableStateOf(false)
    }
    var isStandingsAvailable by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = null) {
        if (!matchViewModel.isInitializedLeagueInfo.value) {
            matchViewModel.getLeagueInfo(leagueId)
            matchViewModel.isInitializedLeagueInfo.value = true
        }
    }
    LaunchedEffect(key1 = Unit) {
        if (!matchViewModel.isInitializedMatchInfo.value) {
            matchViewModel.getMatchInfo(matchId, getTimeZone())
            matchViewModel.isInitializedMatchInfo.value = true
        }
    }

    val leagueInfoState = matchViewModel.leagueInfoState.collectAsState()
    val matchInfoState = matchViewModel.matchInfoState.collectAsState()

    LaunchedEffect(key1 = leagueInfoState.value, key2 = matchInfoState.value) {
        val currentLeagueInfoState = leagueInfoState.value
        val currentMatchInfoState = matchInfoState.value

        if (currentLeagueInfoState is MatchLeagueInfoState.Success &&
            currentMatchInfoState is MatchInfoState.Success
        ) {
            val leagueData = currentLeagueInfoState.data.body()?.response?.get(0)
            val matchData = currentMatchInfoState.data.body()?.response?.get(0)

            isEventsAvailable = !matchData?.events.isNullOrEmpty()
            isLineupsAvailable = matchData?.lineups?.isNotEmpty() == true
            isStatisticsAvailable = !matchData?.statistics.isNullOrEmpty()
            isStandingsAvailable = leagueData?.seasons?.find { it?.current == true }?.coverage?.standings ?: false
        }
    }
    //202645JINJYDBV17
    //2E98DE46F1A072620FEDAD66097C4FB628A447E9

    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                if (isEventsAvailable) {
                    EventsScreens(
                        homeId = homeId, awayId = awayId,
                        isRefreshing = isRefreshing,
                        onRefresh = onRefresh
                    )
                } else {
                    NotAvailable(icon = R.drawable.no_events, shortText = R.string.noEvents)
                }
            }

            1 -> {
                DetailsScreens()
            }

            2 -> {
                if (isLineupsAvailable) {
                    LineupsScreens(matchId)
                } else {
                    NotAvailable(icon = R.drawable.no_lineups, shortText = R.string.noLineups)
                }
            }

            3 -> {
                if (isStatisticsAvailable) {
                    MatchTeamsStatisticsScreens()
                } else {
                    NotAvailable(icon = R.drawable.no_stats, shortText = R.string.noStats)
                }
            }

            4 -> {
                if (isStandingsAvailable) {
                    StandingsScreens()
                } else {
                    NotAvailable(icon = R.drawable.no_standings, shortText = R.string.noStandings)
                }
            }
        }
    }

}

//
@Composable
fun FetchMatchData(
    matchViewModel: MatchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column {
        when (val state = matchViewModel.matchInfoState.collectAsState().value) {
            is MatchInfoState.Empty -> {
                Toast.makeText(context, stringResource(id = R.string.error), Toast.LENGTH_SHORT).show()
            }
            is MatchInfoState.Loading -> {
                MatchActivityShimmer()
            }
            is MatchInfoState.Error -> {
                Log.d(Constants.TAG, state.message)
            }
            is MatchInfoState.Success -> {
                TeamsHeader(matchItem = state.data.body()!!.response?.get(0)!!)
            }
        }
    }
}

@Composable
fun TeamsHeader(matchItem: MatchByIdResponseItem) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .weight(10f)
                    .fillMaxWidth()
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .clickable {
                            val intent = Intent(context, TeamActivity::class.java)
                            intent.putExtra("teamId", matchItem.teams!!.home!!.id)
                            intent.putExtra("currentSeason", matchItem.league!!.season)
                            intent.putExtra("teamName", matchItem.teams.home!!.name)
                            context.startActivity(intent)
                        },
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp),
                        model = matchItem.teams!!.home!!.logo,
                        contentDescription = "Team Home"
                    )
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = matchItem.teams.home!!.name!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(4f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    when (val shortStatusState = matchItem.fixture!!.status!!.jsonMemberShort) {
                        "1H", "HT", "2H", "ET", "BT", "P" -> {
                            InPlayMatchItem(
                                goalsHome = matchItem.goals!!.home ?: 0,
                                goalsAway = matchItem.goals.away!!,
                                elapsedTime = matchItem.fixture.status!!.elapsed ?: 0,
                                shortStatusState = shortStatusState,
                                penaltyScoreHome = matchItem.score!!.penalty!!.home ?: 0,
                                penaltyScoreAway = matchItem.score.penalty!!.away ?: 0,
                                inHeader = true
                            )
                        }
                        "FT", "AET", "PEN" -> {
                            MatchFinishedItem(
                                goalsHome = matchItem.goals!!.home ?: 0,
                                goalsAway = matchItem.goals.away ?: 0,
                                shortStatusState = shortStatusState,
                                penaltyScoreHome = matchItem.score!!.penalty!!.home ?: 0,
                                penaltyScoreAway = matchItem.score.penalty!!.away ?: 0,
                                inHeader = true
                            )
                        }
                        "SUSP", "INT" -> {
                            StoppedMatchItem(
                                goalsHome = matchItem.goals!!.home ?: 0,
                                goalsAway = matchItem.goals.away ?: 0,
                                shortStatusState = shortStatusState,
                                inHeader = true
                            )
                        }
                        "CANC", "WO", "ABD", "AWD", "PST" -> {
                            NotPlayedMatchItem(
                                shortStatusState = shortStatusState,
                                inHeader = true
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .clickable {
                            val intent = Intent(context, TeamActivity::class.java)
                            intent.putExtra("teamId", matchItem.teams!!.away!!.id)
                            intent.putExtra("currentSeason", matchItem.league!!.season)
                            intent.putExtra("teamName", matchItem.teams.away!!.name)
                            context.startActivity(intent)
                        },
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp),
                        model = matchItem.teams!!.away!!.logo,
                        contentDescription = "Team Away"
                    )
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = matchItem.teams.away!!.name!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

private fun getTabList(): List<Int> {
    return listOf(
        R.string.matchEvents,
        R.string.matchDetails,
        R.string.matchLineups,
        R.string.matchStats,
        R.string.matchStandings
    )
}