package com.elTohamy.flushy.presentation.activities.players.tabRow.screens

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.layoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.transfers.TransfersItem
import com.elTohamy.flushy.data.remote.dto.transfers.TransfersResponseItem
import com.elTohamy.flushy.data.state.PlayerSeasonsState
import com.elTohamy.flushy.data.state.PlayerTransfersState
import com.elTohamy.flushy.presentation.activities.players.viewModel.PlayerViewModel
import com.elTohamy.flushy.presentation.activities.team.TeamActivity
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.getLocale

@Composable
fun PlayerTransfersScreen(player: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchTransferData(player = player)
    }
}

@Composable
fun FetchTransferData(
    playerViewModel: PlayerViewModel = hiltViewModel(), player: Int
) {
    LaunchedEffect(key1 = playerViewModel.playerTransfersState) {
        if (!playerViewModel.isPlayerTransfersInitialized.value) {
            playerViewModel.getPlayerTransfers(player)
            playerViewModel.isPlayerTransfersInitialized.value = true
        }
    }
    when (
        val state =
            playerViewModel.playerTransfersState.collectAsState().value
    ) {
        is PlayerTransfersState.Empty -> {}
        is PlayerTransfersState.Loading -> {}
        is PlayerTransfersState.Error -> {}
        is PlayerTransfersState.Success -> {
            when (val seasonsState = playerViewModel.playerSeasonsState.collectAsState().value) {
                is PlayerSeasonsState.Empty -> {}
                is PlayerSeasonsState.Loading -> {}
                is PlayerSeasonsState.Error -> {}
                is PlayerSeasonsState.Success -> {
                    TransfersItems(
                        transfersResponseItem = state.data.body()!!.response!![0],
                        transferItems = state.data.body()!!.response!![0]!!.transfers,
                        playerSeasons = seasonsState.data.body()!!.response
                    )
                }
            }
        }
    }
}

@Composable
fun TransfersItems(
    transfersResponseItem: TransfersResponseItem?,
    transferItems: List<TransfersItem?>?,
    playerSeasons: List<Int?>?
) {
    LazyColumn {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(id = R.string.playerTransfersHeader),
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
                    .height(40.dp)
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = transfersResponseItem!!.player!!.name!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        itemsIndexed(
            transferItems!!,
            itemContent = { index, item ->
                TransferItem(
                    transferItem = item, isLast = index == 0,
                    isFirst = index == transferItems.size - 1,
                    playerSeasons = playerSeasons
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
fun TransferItem(
    transferItem: TransfersItem?,
    isLast: Boolean, isFirst: Boolean,
    playerSeasons: List<Int?>?
) {
    val context = LocalContext.current
    val dark = LocalTheme.current.isDark
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape =
        if (isFirst) RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
        else RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(5.2f)
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.2f)
                        .width(3.dp)
                        .fillMaxHeight()
                        .background(if (isLast) LightGreen else Color.LightGray)
                )
                Column(
                    modifier = Modifier
                        .weight(1.3f)
                        .padding(4.dp)
                        .clickable {
                            val intent = Intent(context, TeamActivity::class.java)
                            intent.putExtra("teamId", transferItem!!.teams!!.out!!.id)
                            intent.putExtra(
                                "currentSeason",
                                playerSeasons!![playerSeasons.size - 1]!!
                            )
                            intent.putExtra("teamName", transferItem.teams!!.out!!.name)
                            context.startActivity(intent)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(30.dp),
                        model = transferItem!!.teams!!.out!!.logo,
                        contentDescription = "Old Team"
                    )
                    Text(
                        modifier = Modifier,
                        text = transferItem.teams!!.out!!.name ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = if (dark) Color.LightGray else Color.Gray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1.4f)
                        .fillMaxHeight()
                        .padding(6.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text =
                        if (
                            (transferItem!!.type ?: stringResource(id = R.string.notDefined)) == "Loan"
                        ) stringResource(id = R.string.transferLoan)
                        else if (
                            (transferItem.type ?: stringResource(id = R.string.notDefined)) == "Free"
                        ) stringResource(id = R.string.transferFree)
                        else if (
                            (transferItem.type ?: stringResource(id = R.string.notDefined)) == "N/A"
                        ) stringResource(id = R.string.transferNon)
                        else transferItem.type ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(
                                if (getLocale()!!.layoutDirection == LayoutDirection.Rtl.ordinal) {
                                    180f
                                } else {
                                    0f
                                }
                            ),
                        painter = painterResource(id = R.drawable.sub_in),
                        contentDescription = "Transfer Icon"
                    )
                    Text(
                        modifier = Modifier,
                        text = transferItem.date ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = if (dark) Color.LightGray else Color.Gray,
                            fontWeight = if (isLast) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1.3f)
                        .padding(4.dp)
                        .clickable {
                            val intent = Intent(context, TeamActivity::class.java)
                            intent.putExtra("teamId", transferItem!!.teams!!.inL!!.id)
                            intent.putExtra(
                                "currentSeason",
                                playerSeasons!![playerSeasons.size - 1]!!
                            )
                            intent.putExtra("teamName", transferItem.teams!!.inL!!.name)
                            context.startActivity(intent)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(30.dp),
                        model = transferItem!!.teams!!.inL!!.logo,
                        contentDescription = "New Team"
                    )
                    Text(
                        modifier = Modifier,
                        text = transferItem.teams!!.inL!!.name ?: stringResource(id = R.string.notDefined),
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