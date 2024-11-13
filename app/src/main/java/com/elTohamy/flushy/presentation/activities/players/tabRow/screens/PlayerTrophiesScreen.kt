package com.elTohamy.flushy.presentation.activities.players.tabRow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.trophies.TrophiesResponseItem
import com.elTohamy.flushy.data.state.PlayerTrophiesState
import com.elTohamy.flushy.presentation.activities.players.viewModel.PlayerViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.Runner_Up
import com.elTohamy.flushy.presentation.ui.theme.Winner
import com.elTohamy.flushy.utils.BoxWithLayout

@Composable
fun PlayerTrophiesScreen(player: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchPlayerTrophiesData(player = player)
    }
}

@Composable
fun FetchPlayerTrophiesData(
    playerViewModel: PlayerViewModel = hiltViewModel(), player: Int
) {
    LaunchedEffect(key1 = playerViewModel.playerTrophiesState) {
        if (!playerViewModel.isPlayerTrophiesInitialized.value) {
            playerViewModel.getPlayerTrophies(player)
            playerViewModel.isPlayerTrophiesInitialized.value = true
        }
    }

    when (
        val state =
            playerViewModel.playerTrophiesState.collectAsState().value
    ) {
        is PlayerTrophiesState.Empty -> {}
        is PlayerTrophiesState.Loading -> {}
        is PlayerTrophiesState.Error -> {}
        is PlayerTrophiesState.Success -> {
            PlayerTrophiesItems(trophiesResponseItems = state.data.body()!!.response)
        }
    }
}

@Composable
fun PlayerTrophiesItems(
    trophiesResponseItems: List<TrophiesResponseItem?>?
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(id = R.string.playerTrophiesHeader),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }
        itemsIndexed(
            trophiesResponseItems!!,
            itemContent = { index, item ->
                PlayerTrophyItem(
                    trophiesResponseItem = item, isLast = index == 0,
                    isFirst = index == trophiesResponseItems.size - 1,
                    id = index + 1
                )
            }
        )
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            ) {
            }
        }
    }
}

@Composable
fun PlayerTrophyItem(
    trophiesResponseItem: TrophiesResponseItem?,
    isLast: Boolean, isFirst: Boolean, id: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape =
        if (isLast) {
            RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
        } else if (isFirst) {
            RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
        } else RectangleShape,
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .weight(5.2f)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(0.4f),
                    text = id.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                if (trophiesResponseItem!!.place == "Winner") {
                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .weight(0.5f),
                        painter = painterResource(id = R.drawable.trophy),
                        contentDescription = "Trophy"
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .weight(0.5f),
                        painter = painterResource(id = R.drawable.second_place),
                        contentDescription = "Trophy"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3.05f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = trophiesResponseItem.league ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "(",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Text(
                            text = trophiesResponseItem.country ?: stringResource(id = R.string.notDefined),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Text(
                            text = ")",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                }
                Text(
                    modifier = Modifier.weight(1.2f),
                    text = trophiesResponseItem.season ?: stringResource(id = R.string.notDefined),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                )
                Box(
                    modifier = Modifier
                        .weight(0.05f)
                        .fillMaxHeight()
                        .background(
                            if (trophiesResponseItem.place == "Winner") {
                                Winner
                            } else {
                                Runner_Up
                            }
                        )
                )
            }
        }
    }
}