package com.elTohamy.flushy.presentation.activities.team.tabRow.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material3.Icon
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.transfers.TransfersResponseItem
import com.elTohamy.flushy.data.state.TeamTransfersState
import com.elTohamy.flushy.presentation.activities.team.TeamActivity
import com.elTohamy.flushy.presentation.activities.team.viewModel.TeamViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.search.SearchBar
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme
import kotlinx.coroutines.launch

@Composable
fun TeamTransfersScreen(team: Int, season: Int) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            if (remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value != 0) {
                FloatingActionButton(
                    shape = FloatingActionButtonDefaults.smallShape,
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer,
                    elevation = FloatingActionButtonDefaults.elevation(4.dp),
                    onClick = {
                        scope.launch { lazyListState.animateScrollToItem(0) }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(width = 25.dp, height = 25.dp),
                        imageVector = Icons.Default.KeyboardDoubleArrowUp,
                        contentDescription = "Go To First Index",
                        tint = MaterialTheme.customColorsPalette.blackToWhite
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            FetchPlayersTransfers(team = team, season = season, lazyListState = lazyListState)
        }
    }
}

@Composable
fun FetchPlayersTransfers(
    teamViewModel: TeamViewModel = hiltViewModel(), team: Int, season: Int, lazyListState: LazyListState
) {
    LaunchedEffect(key1 = teamViewModel.isTeamTransfersInitialized.value) {
        if (!teamViewModel.isTeamTransfersInitialized.value) {
            teamViewModel.getTeamTransfers(team)
            teamViewModel.isTeamTransfersInitialized.value = true
        }
    }

    when (
        val state = teamViewModel.teamTransfersState.collectAsState().value
    ) {
        is TeamTransfersState.Empty -> {}
        is TeamTransfersState.Loading -> {}
        is TeamTransfersState.Error -> {}
        is TeamTransfersState.Success -> {
            PlayersTransfers(
                transferResponseItems = state.data.body()!!.response,
                team = team, season = season, lazyListState = lazyListState
            )
        }
    }
}

@Composable
fun PlayersTransfers(
    transferResponseItems: List<TransfersResponseItem?>?,
    team: Int, season: Int, lazyListState: LazyListState
) {
    val myList = transferResponseItems!!.groupBy { item ->
        item!!.transfers!!.sortedBy { it!!.date }
    }
    val textState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val searchedText = textState.value.text
    LazyColumn(
        state = lazyListState
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .systemBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                SearchBar(textState = textState)
            }
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(id = R.string.teamTransfersScreenHeader),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }
        myList.forEach { (_, item) ->
            items(item.filter { it!!.player!!.name!!.contains(searchedText, ignoreCase = true) }) {
                PlayerTransferItem(transfersResponseItem = it, team = team, season = season)
            }
        }
    }
}

@Composable
fun PlayerTransferItem(
    transfersResponseItem: TransfersResponseItem?,
    team: Int, season: Int
) {
    val dark = LocalTheme.current.isDark
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = transfersResponseItem!!.player!!.name!!,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    ),
                    maxLines = 1
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.2.dp)
                    .background(if (dark) Color.LightGray else Color.Gray)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            transfersResponseItem!!.transfers!!.forEach {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f)
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .weight(1.9f)
                                .width(IntrinsicSize.Max),
                            shape = RoundedCornerShape(5.dp),
                            onClick = {
                                if (it!!.teams!!.out!!.id != team) {
                                    val intent = Intent(context, TeamActivity::class.java)
                                    intent.putExtra("teamId", it.teams!!.out!!.id)
                                    intent.putExtra("currentSeason", season)
                                    intent.putExtra("teamName", it.teams.out!!.name)
                                    context.startActivity(intent)
                                }
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(25.dp),
                                    model = it!!.teams!!.out!!.logo,
                                    contentDescription = "Club Logo"
                                )
                                Text(
                                    text = it.teams!!.out!!.name ?: stringResource(id = R.string.notDefined),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.weight(1.2f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text =
                                if (
                                    (it!!.type ?: stringResource(id = R.string.notDefined)) == "Loan"
                                ) stringResource(id = R.string.transferLoan)
                                else if (
                                    (it.type ?: stringResource(id = R.string.notDefined)) == "Free"
                                ) stringResource(id = R.string.transferFree)
                                else if (
                                    (it.type ?: stringResource(id = R.string.notDefined)) == "N/A"
                                ) stringResource(id = R.string.transferNon)
                                else it.type ?: stringResource(id = R.string.notDefined),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1
                            )
                            Image(
                                modifier = Modifier.size(15.dp),
                                painter = painterResource(id = R.drawable.sub_in),
                                contentDescription = "Transfer To"
                            )
                            Text(
                                text = it.date ?: stringResource(id = R.string.notDefined),
                                style = TextStyle(
                                    color = if (dark) Color.LightGray else Color.Gray,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1
                            )
                        }
                        Card(
                            modifier = Modifier
                                .weight(1.9f)
                                .width(IntrinsicSize.Max),
                            shape = RoundedCornerShape(5.dp),
                            onClick = {
                                if (it!!.teams!!.inL!!.id != team) {
                                    val intent = Intent(context, TeamActivity::class.java)
                                    intent.putExtra("teamId", it.teams!!.inL!!.id)
                                    intent.putExtra("currentSeason", season)
                                    intent.putExtra("teamName", it.teams.inL!!.name)
                                    context.startActivity(intent)
                                }
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(25.dp),
                                    model = it!!.teams!!.inL!!.logo,
                                    contentDescription = "Club Logo"
                                )
                                Text(
                                    text = it.teams!!.inL!!.name ?: stringResource(id = R.string.notDefined),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
                                    ),
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