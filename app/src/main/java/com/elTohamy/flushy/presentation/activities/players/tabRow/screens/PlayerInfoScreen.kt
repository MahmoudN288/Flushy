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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.players.PlayerInfoResponseItem
import com.elTohamy.flushy.data.remote.dto.players.squads.SquadsResponseItem
import com.elTohamy.flushy.data.state.PlayerByIdState
import com.elTohamy.flushy.data.state.PlayerSeasonsState
import com.elTohamy.flushy.data.state.PlayerSquadsState
import com.elTohamy.flushy.presentation.activities.players.viewModel.PlayerViewModel
import com.elTohamy.flushy.presentation.activities.team.TeamActivity
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun PlayerInfoScreen(player: Int) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 10.dp),
            text = stringResource(id = R.string.playerMainInfo),
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            ),
            textAlign = TextAlign.Start
        )
        FetchPlayerInfo(playerViewModel = hiltViewModel(), player = player)
    }
}

@Composable
fun FetchPlayerInfo(
    playerViewModel: PlayerViewModel = hiltViewModel(),
    player: Int
) {
    LaunchedEffect(key1 = playerViewModel.playerSquadsState) {
        if (!playerViewModel.isPlayerSquadsInitialized.value) {
            playerViewModel.getPlayerSquads(player)
            playerViewModel.isPlayerSquadsInitialized.value = true
        }
    }
    LaunchedEffect(key1 = playerViewModel.playerSeasonsState) {
        if (!playerViewModel.isPlayerSeasonsInitialized.value) {
            playerViewModel.getPlayerSeasons(player)
            playerViewModel.isPlayerSeasonsInitialized.value = true
        }
    }
    when (val playerState = playerViewModel.playerByIdState.collectAsState().value) {
        is PlayerByIdState.Empty -> {}
        is PlayerByIdState.Loading -> {}
        is PlayerByIdState.Error -> {}
        is PlayerByIdState.Success -> {
            when (val squadsState = playerViewModel.playerSquadsState.collectAsState().value) {
                is PlayerSquadsState.Empty -> {}
                is PlayerSquadsState.Loading -> {}
                is PlayerSquadsState.Error -> {}
                is PlayerSquadsState.Success -> {
                    when (val seasonsState = playerViewModel.playerSeasonsState.collectAsState().value) {
                        is PlayerSeasonsState.Empty -> {}
                        is PlayerSeasonsState.Loading -> {}
                        is PlayerSeasonsState.Error -> {}
                        is PlayerSeasonsState.Success -> {
                            PlayerMainInformation(
                                playerInfoResponseItem = playerState.data.body()?.response?.get(0)!!,
                                squadsResponseItems = squadsState.data.body()!!.response!!,
                                playerSeasons = seasonsState.data.body()!!.response
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerMainInformation(
    playerInfoResponseItem: PlayerInfoResponseItem,
    squadsResponseItems: List<SquadsResponseItem?>,
    playerSeasons: List<Int?>?
) {
    val context = LocalContext.current
    val dark = LocalTheme.current.isDark
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(4f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.padding(5.dp),
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(width = 100.dp, height = 100.dp),
                            model = playerInfoResponseItem.player!!.photo ?: "",
                            placeholder = painterResource(id = R.drawable.flushy_logo_t),
                            contentDescription = "Player Image"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxHeight()
                            .padding(horizontal = 5.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = playerInfoResponseItem.player!!.name ?: stringResource(id = R.string.notDefined),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = playerInfoResponseItem.player.nationality ?: stringResource(id = R.string.notDefined),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.8f),
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            ),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.playerAge),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = (playerInfoResponseItem.player!!.age ?: 0).toString(),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.playerBirth),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = playerInfoResponseItem.player!!.birth!!.date ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.playerHeight),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = playerInfoResponseItem.player!!.height ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.playerWeight),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = playerInfoResponseItem.player!!.weight ?: stringResource(id = R.string.notDefined),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.playerStatus),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text =
                        if ((playerInfoResponseItem.player!!.injured == true).toString() == "True")
                            stringResource(id = R.string.playerIsInjured)
                        else stringResource(id = R.string.playerNotInjured),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.playerActiveYears),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            modifier = Modifier,
                            text = (playerSeasons!![0] ?: stringResource(id = R.string.notDefined)).toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier,
                            text = "-",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Text(
                            modifier = Modifier,
                            text = (playerSeasons[playerSeasons.size - 1] ?: stringResource(id = R.string.notDefined)).toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.playerSquads),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    squadsResponseItems.forEach {
                        BoxWithLayout {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(75.dp)
                                    .weight(3f)
                                    .clickable {
                                        val intent = Intent(context, TeamActivity::class.java)
                                        intent.putExtra("teamId", it!!.team!!.id)
                                        intent.putExtra(
                                            "currentSeason",
                                            playerSeasons?.get(playerSeasons.size - 1)
                                        )
                                        intent.putExtra("teamName", it.team!!.name)
                                        context.startActivity(intent)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier
                                        .weight(1.5f)
                                        .fillMaxHeight(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                                ) {
                                    AsyncImage(
                                        modifier = Modifier.size(30.dp),
                                        model = it!!.team!!.logo ?: stringResource(id = R.string.notDefined),
                                        contentDescription = "Squad Logo"
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = it.team!!.name ?: stringResource(id = R.string.notDefined),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 12.sp
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                }
                                Box(
                                    modifier = Modifier.weight(0.8f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        modifier = Modifier.size(30.dp),
                                        painter = painterResource(id = if (dark) R.drawable.shirt_w else R.drawable.shirt_b),
                                        contentDescription = "Squad Shirt"
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = (it!!.players!![0]!!.number ?: 0).toString(),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 10.sp
                                        ),
                                        textAlign = TextAlign.Start
                                    )
                                }
                                Text(
                                    modifier = Modifier.weight(0.7f),
                                    text = it!!.players!![0]!!.position ?: stringResource(id = R.string.notDefined),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp
                                    ),
                                    textAlign = TextAlign.Start,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}