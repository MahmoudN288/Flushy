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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.sidelined.SidelinedResponseItem
import com.elTohamy.flushy.data.state.PlayerSidelinedState
import com.elTohamy.flushy.presentation.activities.players.viewModel.PlayerViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun PlayerSidelinedScreen(player: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchSidelinedData(player = player)
    }
}

@Composable
fun FetchSidelinedData(
    playerViewModel: PlayerViewModel = hiltViewModel(), player: Int
) {
    LaunchedEffect(key1 = playerViewModel.playerSidelinedState) {
        if (!playerViewModel.isPlayerSidelinedInitialized.value) {
            playerViewModel.getPlayerSidelined(player)
            playerViewModel.isPlayerSidelinedInitialized.value = true
        }
    }

    when (
        val state =
            playerViewModel.playerSidelinedState.collectAsState().value
    ) {
        is PlayerSidelinedState.Empty -> {}
        is PlayerSidelinedState.Loading -> {}
        is PlayerSidelinedState.Error -> {}
        is PlayerSidelinedState.Success -> {
            PlayerSidelinedItems(
                sidelinedResponseItems = state.data.body()!!.response
            )
        }
    }
}

@Composable
fun PlayerSidelinedItems(
    sidelinedResponseItems: List<SidelinedResponseItem?>?
) {
    val dark = LocalTheme.current.isDark
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(id = R.string.playerSidelinedHeader),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                )
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.3f)
                                .background(Color.Transparent)
                        )
                        Text(
                            modifier = Modifier
                                .weight(1.2f)
                                .padding(vertical = 4.dp),
                            text = stringResource(id = R.string.playerSidelinedStart),
                            style = TextStyle(
                                color = if (dark) Color.LightGray else Color.DarkGray,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(vertical = 8.dp)
                                .fillMaxHeight(),
                            text = stringResource(id = R.string.playerSidelinedType),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .weight(1.2f)
                                .padding(vertical = 4.dp),
                            text = stringResource(id = R.string.playerSidelinedEnd),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(0.3f)
                                .background(Color.Transparent)
                        )
                    }
                }
            }
        }
        itemsIndexed(
            sidelinedResponseItems!!,
            itemContent = { index, item ->
                PlayerSidelinedItem(
                    sidelinedResponseItem = item,
                    isFirst = (index == sidelinedResponseItems.size -1),
                    isLast = (index == 0)
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
fun PlayerSidelinedItem(
    sidelinedResponseItem: SidelinedResponseItem?,
    isFirst: Boolean, isLast: Boolean
) {
    val dark = LocalTheme.current.isDark
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape =
        if (isFirst) RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
        else RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f),
                    shape = if (isLast) {
                        RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    } else if (isFirst) {
                        RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                    } else RectangleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = LightRed
                    )
                ) {
                }
                Text(
                    modifier = Modifier
                        .weight(1.2f)
                        .padding(vertical = 4.dp),
                    text = sidelinedResponseItem!!.start ?: stringResource(id = R.string.playerSidelinedNF),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.DarkGray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(vertical = 8.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.injury),
                        contentDescription = "Injury"
                    )
                    Text(
                        text = sidelinedResponseItem.type ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1.2f)
                        .padding(vertical = 4.dp),
                    text = (
                        sidelinedResponseItem.end ?:
                            stringResource(id = R.string.playerSidelinedNF)
                    ),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f),
                    shape = if (isLast) {
                        RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    } else if (isFirst) {
                        RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                    } else RectangleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = LightGreen
                    )
                ) {
                }
            }
        }
    }
}