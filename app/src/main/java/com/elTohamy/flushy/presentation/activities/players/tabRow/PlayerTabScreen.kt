package com.elTohamy.flushy.presentation.activities.players.tabRow

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.players.PlayerInfoResponseItem
import com.elTohamy.flushy.data.state.PlayerByIdState
import com.elTohamy.flushy.presentation.activities.players.tabRow.screens.PlayerInfoScreen
import com.elTohamy.flushy.presentation.activities.players.tabRow.screens.PlayerSidelinedScreen
import com.elTohamy.flushy.presentation.activities.players.tabRow.screens.PlayerStatisticsScreen
import com.elTohamy.flushy.presentation.activities.players.tabRow.screens.PlayerTransfersScreen
import com.elTohamy.flushy.presentation.activities.players.tabRow.screens.PlayerTrophiesScreen
import com.elTohamy.flushy.presentation.activities.players.viewModel.PlayerViewModel
import com.elTohamy.flushy.presentation.animations.screens.LiveGamesShimmer
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.customTabIndicatorOffset
import com.elTohamy.flushy.utils.findActivity
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlayerTabScreen(id: Int, season: Int) {
    val tabData = getTabList()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabData.size })
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FetchPlayerInfo(id = id, season = season)
                TabLayout(tabData = tabData, pagerState = pagerState)
            }
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
        ) {
            TabContent(tabData, pagerState, id)
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

@Composable
fun FetchPlayerInfo(
    playerViewModel: PlayerViewModel = hiltViewModel(), id: Int, season: Int
) {
    LaunchedEffect(key1 = playerViewModel.isPlayerByIdInitialized) {
        if (!playerViewModel.isPlayerByIdInitialized.value) {
            playerViewModel.getPlayerById(id, season)
            playerViewModel.isPlayerByIdInitialized.value = true
        }
    }
    when (val state = playerViewModel.playerByIdState.collectAsState().value) {
        is PlayerByIdState.Empty -> {}
        is PlayerByIdState.Loading -> {
            LiveGamesShimmer()
        }
        is PlayerByIdState.Error -> {}
        is PlayerByIdState.Success -> {
            PlayerProfileHeader(playerInfoResponseItem = state.data.body()!!.response!![0])
        }
    }
}

@Composable
fun PlayerProfileHeader(playerInfoResponseItem: PlayerInfoResponseItem?) {
    val context = LocalContext.current
    val dark = LocalTheme.current.isDark
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { context.findActivity()!!.finish() }) {
                Icon(
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = MaterialTheme.customColorsPalette.blackToWhite
                )
            }
            Card(
                modifier = Modifier,
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = if (dark) Color.LightGray else Color.White
                )
            ) {
                AsyncImage(
                    modifier = Modifier.size(30.dp),
                    model = playerInfoResponseItem!!.player!!.photo,
                    contentDescription = "Player Image"
                )
            }
            Text(
                modifier = Modifier,
                text = playerInfoResponseItem!!.player!!.name!!,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }
    }
}

//List<Pair<String, ImageVector>>

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TabContent(tabData: List<Int>, pagerState: PagerState, player: Int) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> {
                PlayerInfoScreen(player = player)
            }
            1 -> {
                PlayerStatisticsScreen()
            }
            2 -> {
                PlayerTransfersScreen(player = player)
            }
            3 -> {
                PlayerSidelinedScreen(player = player)
            }
            4 -> {
                PlayerTrophiesScreen(player = player)
            }
        }
    }

}

private fun getTabList(): List<Int> {
    return listOf(
        R.string.playerInfoScreen,
        R.string.playerStatsScreen,
        R.string.playerTransfersScreen,
        R.string.playerSidelinedScreen,
        R.string.playerTrophiesScreen
    )
}