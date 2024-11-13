package com.elTohamy.flushy.presentation.activities.tournament.tab

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.leagues.leagueById.LeaguesByIdResponseItem
import com.elTohamy.flushy.data.remote.dto.leagues.leagueById.SeasonsItem
import com.elTohamy.flushy.data.state.TournamentLeagueInfoState
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentGamesScreen
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentNewsScreen
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentOverviewScreen
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentPlayerScreen
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentSeasonsScreen
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentStandingsScreen
import com.elTohamy.flushy.presentation.activities.tournament.screens.TournamentTeamScreen
import com.elTohamy.flushy.presentation.activities.tournament.viewModel.TournamentViewModel
import com.elTohamy.flushy.presentation.animations.shimmer.TournamentActivityShimmer
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.Constants
import com.elTohamy.flushy.utils.Constants.TAG
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.customTabIndicatorOffset
import com.elTohamy.flushy.utils.findActivity
import com.elTohamy.flushy.utils.getTournamentName
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TournamentTabScreen(
    tournamentViewModel: TournamentViewModel = hiltViewModel(),
    navHostController: NavHostController, leagueId: Int,
    currentSeason: Int, locale: Locale?, country: String?, leagueName: String?
) {
    val context = LocalContext.current
    val tabData = getTabList()

    val pagerState = rememberPagerState(initialPage = 1, pageCount = { tabData.size })
    val state = rememberCollapsingToolbarScaffoldState()
    var imageData by remember { mutableStateOf<Any?>(null) }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar = {
            val progress = state.toolbarState.progress

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                // Parallax effect for FetchLeagueInfo
                FetchLeagueInfo(
                    tournamentViewModel = tournamentViewModel,
                    modifier = Modifier
                        .parallax(0.5f)
                        .graphicsLayer { alpha = progress },
                    onLeagueIdLoaded = { imageData = it },
                    onImageLoaded = { imageData = it }
                )

                // Back button and other actions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                        .align(Alignment.TopStart),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        modifier = Modifier.width(25.dp),
                        onClick = { context.findActivity()?.finish() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = MaterialTheme.customColorsPalette.blackToWhite
                        )
                    }

                    /*imageData?.let { data ->
                        ToolbarImage(
                            imageData = data,
                            progress = progress
                        )
                    }*/

                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .padding(horizontal = 5.dp),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.NotificationsActive,
                            contentDescription = "Follow Game",
                            tint = MaterialTheme.customColorsPalette.blackToWhite
                        )
                    }
                }
            }
        }
    ) {
        // Body content
        Column(modifier = Modifier.fillMaxSize()) {
            TabLayout(tabData, pagerState)
            TabContent(
                navHostController = navHostController,
                pagerState,
                leagueId = leagueId,
                currentSeason = currentSeason,
                locale = locale,
                country = country,
                leagueName = leagueName
            )
        }
    }
    /*CollapsingLayout(
        collapsingTop = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.customColorsPalette.mainAppBar),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Content that collapses
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
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
                                    tint = MaterialTheme.customColorsPalette.blackToWhite
                                )
                            }
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier
                                        .border(
                                            width = 1.dp,
                                            shape = RoundedCornerShape(10.dp),
                                            color = MaterialTheme.customColorsPalette.blackToWhite
                                        )
                                        .background(MaterialTheme.colorScheme.background)
                                        .padding(8.dp),
                                    text = stringResource(id = R.string.tournamentFavourite)
                                )
                                IconButton(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .padding(horizontal = 5.dp),
                                    onClick = {  }) {
                                    Icon(
                                        imageVector = Icons.Filled.NotificationsActive,
                                        contentDescription = "Follow Game",
                                        tint = MaterialTheme.customColorsPalette.blackToWhite
                                    )
                                }
                            }
                        }
                        FetchLeagueInfo(tournamentViewModel)
                    }
                }
            }
        },
        bodyContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Content below the collapsing section
                TabLayout(tabData, pagerState)
                TabContent(
                    navHostController = navHostController,
                    pagerState,
                    leagueId = leagueId,
                    currentSeason = currentSeason,
                    locale = locale,
                    country = country,
                    leagueName = leagueName
                )
            }
        }
    )*/
}

@Composable
fun FetchLeagueInfo(
    modifier: Modifier = Modifier,
    tournamentViewModel: TournamentViewModel = hiltViewModel(),
    onLeagueIdLoaded: (Int) -> Unit,
    onImageLoaded: (String) -> Unit
) {
    Column(modifier = modifier) {
        when (val state = tournamentViewModel.leagueInfoState.collectAsState().value) {
            is TournamentLeagueInfoState.Empty -> {
                repeat(3) {
                    TournamentActivityShimmer()
                }
            }
            is TournamentLeagueInfoState.Loading -> {
                repeat(3) {
                    TournamentActivityShimmer()
                }
            }
            is TournamentLeagueInfoState.Error -> {
                Log.d(TAG, state.message)
            }
            is TournamentLeagueInfoState.Success -> {
                val leagueItem = state.data.body()!!.response?.get(0)!!
                onLeagueIdLoaded(leagueItem.league!!.id!!)
                onImageLoaded(leagueItem.league.logo!!)
                TournamentHeader(
                    leagueItem = leagueItem
                )
            }
        }
    }
}

@Composable
fun ToolbarImage(imageData: Any, progress: Float) {
    val imageSize = (40 + (60 - 40) * progress).dp // Animate size
    val offsetX = (1 - progress) * 100f // Animate X offset

    LoadLeagueImage(
        imageData = imageData,
        modifier = Modifier
            .size(imageSize)
            .offset(x = offsetX.dp)
    )
}

@Composable
fun LoadLeagueImage(
    imageData: Any, modifier: Modifier = Modifier
) {
    val dark = LocalTheme.current
    when (imageData) {
        Constants.PREMIER_LEAGUE_ID -> {
            Image(
                modifier = modifier,
                painter = if (dark.isDark) painterResource(id = R.drawable.pl_white)
                else painterResource(id = R.drawable.p_league),
                contentDescription = ""
            )
        }
        Constants.LA_LIGA -> {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.la_liga),
                contentDescription = ""
            )
        }
        Constants.SERIE_A -> {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.serie_a),
                contentDescription = ""
            )
        }
        Constants.BUNDESLIGA -> {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.bundes_ligue),
                contentDescription = ""
            )
        }
        Constants.LIGUE_1 -> {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.ligue1),
                contentDescription = ""
            )
        }
        else -> {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = imageData,
                contentDescription = "Tournament Header Logo",
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
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
    navHostController: NavHostController, pagerState: PagerState,
    leagueId: Int, currentSeason: Int, locale: Locale?,
    country: String?, leagueName: String?, tournamentViewModel: TournamentViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        if (!tournamentViewModel.isInitializedLeagueInfo.value) {
            tournamentViewModel.getLeagueInfo(leagueId)
            tournamentViewModel.isInitializedLeagueInfo.value = true
        }
    }
    var myList: List<SeasonsItem?> = emptyList()
    when (val state = tournamentViewModel.leagueInfoState.collectAsState().value) {
        is TournamentLeagueInfoState.Empty -> {
            repeat(3) {
                TournamentActivityShimmer()
            }
        }
        is TournamentLeagueInfoState.Loading -> {
            repeat(3) {
                TournamentActivityShimmer()
            }
        }
        is TournamentLeagueInfoState.Error -> {
            Log.d(TAG, state.message)
        }
        is TournamentLeagueInfoState.Success -> {
            myList = state.data.body()!!.response?.get(0)!!.seasons!!.filter { it!!.current!! }
        }
    }
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                TournamentOverviewScreen(navController = navHostController)
            }

            1 -> {
                TournamentGamesScreen(
                    navController = navHostController,
                    leagueId = leagueId, currentSeason = currentSeason
                )
            }

            2 -> {
                if (myList[0]?.coverage?.standings != null) {
                    TournamentStandingsScreen(
                        navController = navHostController,
                        leagueId = leagueId, currentSeason = currentSeason
                    )
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Standings not available for this tournament",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        )
                    }
                }
            }

            3 -> {
                TournamentNewsScreen(
                    navController = navHostController, locale = locale,
                    country = country, leagueName = leagueName
                )
            }

            4 -> {
                TournamentTeamScreen(navController = navHostController)
            }

            5 -> {
                if (myList[0]?.coverage?.topScorers == false &&
                    myList[0]?.coverage?.topAssists == false &&
                    myList[0]?.coverage?.topCards == false
                    ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Players Stats not available for this tournament",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        )
                    }
                } else {
                    TournamentPlayerScreen(
                        navController = navHostController,
                        leagueId = leagueId,
                        currentSeason = currentSeason
                    )
                }
            }

            6 -> {
                TournamentSeasonsScreen(navController = navHostController)
            }
        }
    }

}

@Composable
fun TournamentHeader(leagueItem: LeaguesByIdResponseItem) {
    val dark = LocalTheme.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColorsPalette.mainAppBar)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leagueItem.league!!.id == Constants.PREMIER_LEAGUE_ID) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = if (dark.isDark) painterResource(id = R.drawable.pl_white)
                              else painterResource(id = R.drawable.p_league),
                    contentDescription = ""
                )
            } else if (leagueItem.league.id == Constants.LA_LIGA) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.la_liga),
                    contentDescription = ""
                )
            } else if (leagueItem.league.id == Constants.SERIE_A) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.serie_a),
                    contentDescription = ""
                )
            } else if (leagueItem.league.id == Constants.BUNDESLIGA) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.bundes_ligue),
                    contentDescription = ""
                )
            } else if (leagueItem.league.id == Constants.LIGUE_1) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.ligue1),
                    contentDescription = ""
                )
            }
            else {
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = leagueItem.league.logo,
                    contentDescription = "Tournament Header Logo",
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = getTournamentName(
                        id = leagueItem.league.id!!,
                        jsonName = leagueItem.league.name!!
                    ),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    modifier = Modifier,
                    text = leagueItem.league.type!!,
                    style = TextStyle(
                        color = if (dark.isDark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

private fun getTabList(): List<Int> {
    return listOf(
        R.string.tournamentOverview,
        R.string.tournamentGames,
        R.string.tournamentStand,
        R.string.tournamentNews,
        R.string.tournamentTeams,
        R.string.tournamentPlayers,
        R.string.tournamentSeasons
    )
}