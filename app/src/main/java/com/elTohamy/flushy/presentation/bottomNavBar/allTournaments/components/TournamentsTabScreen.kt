package com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.state.LeaguesState
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.screens.AllTournaments
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.screens.FavouriteTournamentScreen
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.LocalTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun TournamentsLazyColumn(
    modifier: Modifier = Modifier
) {
    val tabData = getTabList()
    //val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabData.size })
    Scaffold(
        topBar = {
        }
    ) { paddingValues->
        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CustomTabSample(list = tabData)
        }
    }
}

@Composable
fun FetchAllLeague(mainViewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = null) {
        if (!mainViewModel.isLeaguesInitialized.value) {
            mainViewModel.getLeagues()
            mainViewModel.isLeaguesInitialized.value = true
        }
    }
    val db = Firebase.firestore
    db.app
    when (val state = mainViewModel.leaguesState.collectAsState().value) {
        is LeaguesState.Empty -> {}
        is LeaguesState.Loading -> {}
        is LeaguesState.Error -> {}
        is LeaguesState.Success -> {
            AllTournaments(leagues = state.data.body()!!.response!!)
        }
    }
}

private fun getTabList(): List<Int> {
    return listOf(
        R.string.favourite_l,
        R.string.all_l
    )
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
            Color.White
        } else {
            Color.Black
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
    items: List<Int?>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 180.dp,
    onClick: (index: Int) -> Unit
) {
    val dark = LocalTheme.current.isDark
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing), label = "",
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(if (dark) Color.LightGray else Color.Gray)
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
                    text = stringResource(id = text!!),
                )
            }
        }
    }
    when (selectedItemIndex) {
        0 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                FavouriteTournamentScreen()
            }
        }
        1 -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FetchAllLeague()
            }
        }
    }
}

@Composable
fun CustomTabSample(
    list: List<Int?>
) {
    val (selected, setSelected) = rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTab(
            selectedItemIndex = selected,
            items = list,
            onClick = setSelected
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TournamentLazyColumnPrev() {
    TournamentsLazyColumn()
}
