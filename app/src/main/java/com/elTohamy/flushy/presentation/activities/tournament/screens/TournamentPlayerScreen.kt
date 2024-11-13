package com.elTohamy.flushy.presentation.activities.tournament.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.wear.compose.material3.ripple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.state.TopAssistsState
import com.elTohamy.flushy.data.state.TopRedCardsState
import com.elTohamy.flushy.data.state.TopScorersState
import com.elTohamy.flushy.data.state.TopYellowCardsState
import com.elTohamy.flushy.data.remote.dto.players.topAssists.TopAssistsResponseItem
import com.elTohamy.flushy.data.remote.dto.players.topRedCards.TopRedResponseItem
import com.elTohamy.flushy.data.remote.dto.players.topScorers.TopScorersResponseItem
import com.elTohamy.flushy.data.remote.dto.players.topYellowCards.TopYellowResponseItem
import com.elTohamy.flushy.presentation.activities.players.PlayerActivity
import com.elTohamy.flushy.presentation.activities.playersDetailedStats.PlayersDetailedActivity
import com.elTohamy.flushy.presentation.activities.tournament.viewModel.TournamentViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun TournamentPlayerScreen(navController: NavHostController, leagueId: Int, currentSeason: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            text = stringResource(id = R.string.tournamentsPlayersStats),
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Start
        )
        FetchPlayersStats(leagueId = leagueId, currentSeason = currentSeason)
    }
}

@Composable
fun FetchPlayersStats(tournamentViewModel: TournamentViewModel = hiltViewModel(), leagueId: Int, currentSeason: Int) {
    LaunchedEffect(key1 = tournamentViewModel) {
        if (!tournamentViewModel.isTopScorersInitialized.value) {
            tournamentViewModel.getTopScorersByLeague(leagueId, currentSeason)
            tournamentViewModel.isTopScorersInitialized.value = true
        }
    }
    LaunchedEffect(key1 = tournamentViewModel) {
        if (!tournamentViewModel.isTopAssistsInitialized.value) {
            tournamentViewModel.getTopAssistsByLeague(leagueId, currentSeason)
            tournamentViewModel.isTopAssistsInitialized.value = true
        }
    }
    LaunchedEffect(key1 = tournamentViewModel) {
        if (!tournamentViewModel.isTopYellowCardsInitialized.value) {
            tournamentViewModel.getTopYellowCardsByLeague(leagueId, currentSeason)
            tournamentViewModel.isTopYellowCardsInitialized.value = true
        }
    }
    LaunchedEffect(key1 = tournamentViewModel) {
        if (!tournamentViewModel.isTopRedCardsInitialized.value) {
            tournamentViewModel.getTopRedCardsByLeague(leagueId, currentSeason)
            tournamentViewModel.isTopRedCardsInitialized.value = true
        }
    }
    when (val stateTopScorers = tournamentViewModel.topScorersState.collectAsState().value) {
        is TopScorersState.Empty -> {}
        is TopScorersState.Loading -> {}
        is TopScorersState.Error -> {}
        is TopScorersState.Success -> {
            when (val stateTopAssists = tournamentViewModel.topAssistsState.collectAsState().value) {
                is TopAssistsState.Empty -> {}
                is TopAssistsState.Loading -> {}
                is TopAssistsState.Error -> {}
                is TopAssistsState.Success -> {
                    when (val stateTopYellow = tournamentViewModel.topYellowCardsState.collectAsState().value) {
                        is TopYellowCardsState.Empty -> {}
                        is TopYellowCardsState.Loading -> {}
                        is TopYellowCardsState.Error -> {}
                        is TopYellowCardsState.Success -> {
                            when (val stateTopRed = tournamentViewModel.topRedCardsState.collectAsState().value) {
                                is TopRedCardsState.Empty -> {}
                                is TopRedCardsState.Loading -> {}
                                is TopRedCardsState.Error -> {}
                                is TopRedCardsState.Success -> {
                                    PlayersStats(
                                        topScorer = stateTopScorers.data.body()!!.response!!,
                                        topAssists = stateTopAssists.data.body()!!.response!!,
                                        topBookedYellow = stateTopYellow.data.body()!!.response!!,
                                        topBookedRed = stateTopRed.data.body()!!.response!!
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlayersStats(
    topScorer: List<TopScorersResponseItem?>,
    topAssists: List<TopAssistsResponseItem?>,
    topBookedYellow: List<TopYellowResponseItem?>,
    topBookedRed: List<TopRedResponseItem?>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        TopScorers(topScorer = topScorer)
        TopAssists(topAssists = topAssists)
        TopYellowCards(topBookedYellow = topBookedYellow)
        TopRedCards(topBookedRed = topBookedRed)
    }
}

@Composable
fun TopScorers(topScorer: List<TopScorersResponseItem?>) {
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
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topScorer[0]!!.player!!.id)
                            intent.putExtra("currentSeason", topScorer[0]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topScorer[0]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topScorer[0]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topScorer[0]!!.statistics?.get(0)!!.goals!!.total.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topScorer[0]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topScorer[1]!!.player!!.id)
                            intent.putExtra("currentSeason", topScorer[1]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topScorer[1]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topScorer[1]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topScorer[1]!!.statistics?.get(0)!!.goals!!.total.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topScorer[1]!!.statistics?.get(0)!!.team!!.logo, contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topScorer[2]!!.player!!.id)
                            intent.putExtra("currentSeason", topScorer[2]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topScorer[2]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topScorer[2]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topScorer[2]!!.statistics?.get(0)!!.goals!!.total.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topScorer[2]!!.statistics?.get(0)!!.team!!.logo, contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topScorer[3]!!.player!!.id)
                            intent.putExtra("currentSeason", topScorer[3]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topScorer[3]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topScorer[3]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topScorer[3]!!.statistics?.get(0)!!.goals!!.total.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topScorer[3]!!.statistics?.get(0)!!.team!!.logo, contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topScorer[4]!!.player!!.id)
                            intent.putExtra("currentSeason", topScorer[4]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topScorer[4]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topScorer[4]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topScorer[4]!!.statistics?.get(0)!!.goals!!.total.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topScorer[4]!!.statistics?.get(0)!!.team!!.logo, contentDescription = ""
                        )
                    }
                }
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(vertical = 8.dp)
                .background(Color.LightGray))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                    .clickable {
                        val intent = Intent(context, PlayersDetailedActivity::class.java)
                        intent.putExtra("top", "topScorer")
                        intent.putExtra("league", topScorer[0]!!.statistics?.get(0)!!.league!!.name)
                        intent.putExtra("leagueLogo", topScorer[0]!!.statistics?.get(0)!!.league!!.logo)
                        intent.putExtra("leagueId", topScorer[0]!!.statistics?.get(0)!!.league!!.id)
                        intent.putExtra("flag", topScorer[0]!!.statistics?.get(0)!!.league!!.flag)
                        intent.putExtra("season", topScorer[0]!!.statistics?.get(0)!!.league!!.season)
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.tournamentPlayersTopScorers),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.size(20.dp).padding(horizontal = 5.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Open Details Icon",
                    tint = MaterialTheme.customColorsPalette.blackToWhite
                )
            }
        }
    }
}

@Composable
fun TopAssists(topAssists: List<TopAssistsResponseItem?>) {
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
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topAssists[0]!!.player!!.id)
                            intent.putExtra("currentSeason", topAssists[0]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topAssists[0]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topAssists[0]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topAssists[0]!!.statistics?.get(0)!!.goals!!.assists.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topAssists[0]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topAssists[1]!!.player!!.id)
                            intent.putExtra("currentSeason", topAssists[1]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topAssists[1]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topAssists[1]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topAssists[1]!!.statistics?.get(0)!!.goals!!.assists.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topAssists[1]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topAssists[2]!!.player!!.id)
                            intent.putExtra("currentSeason", topAssists[2]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topAssists[2]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topAssists[2]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topAssists[2]!!.statistics?.get(0)!!.goals!!.assists.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topAssists[2]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topAssists[3]!!.player!!.id)
                            intent.putExtra("currentSeason", topAssists[3]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topAssists[3]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topAssists[3]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topAssists[3]!!.statistics?.get(0)!!.goals!!.assists.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topAssists[3]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topAssists[4]!!.player!!.id)
                            intent.putExtra("currentSeason", topAssists[4]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topAssists[4]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topAssists[4]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topAssists[4]!!.statistics?.get(0)!!.goals!!.assists.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topAssists[4]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(vertical = 8.dp)
                .background(Color.LightGray))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                    .clickable {
                        val intent = Intent(context, PlayersDetailedActivity::class.java)
                        intent.putExtra("top", "topAssist")
                        intent.putExtra("league", topAssists[0]!!.statistics?.get(0)!!.league!!.name)
                        intent.putExtra("leagueLogo", topAssists[0]!!.statistics?.get(0)!!.league!!.logo)
                        intent.putExtra("leagueId", topAssists[0]!!.statistics?.get(0)!!.league!!.id)
                        intent.putExtra("flag", topAssists[0]!!.statistics?.get(0)!!.league!!.flag)
                        intent.putExtra("season", topAssists[0]!!.statistics?.get(0)!!.league!!.season)
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.tournamentPlayersTopAssists),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.size(20.dp).padding(horizontal = 5.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Open Details Icon",
                    tint = MaterialTheme.customColorsPalette.blackToWhite
                )
            }
        }
    }
}

@Composable
fun TopYellowCards(topBookedYellow: List<TopYellowResponseItem?>) {
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
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedYellow[0]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedYellow[0]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedYellow[0]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedYellow[0]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedYellow[0]!!.statistics?.get(0)!!.cards!!.yellow.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedYellow[0]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedYellow[1]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedYellow[1]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedYellow[1]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedYellow[1]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedYellow[1]!!.statistics?.get(0)!!.cards!!.yellow.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedYellow[1]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedYellow[2]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedYellow[2]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedYellow[2]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedYellow[2]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedYellow[2]!!.statistics?.get(0)!!.cards!!.yellow.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedYellow[2]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedYellow[3]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedYellow[3]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedYellow[3]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedYellow[3]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedYellow[3]!!.statistics?.get(0)!!.cards!!.yellow.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedYellow[3]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedYellow[4]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedYellow[4]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedYellow[4]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedYellow[4]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedYellow[4]!!.statistics?.get(0)!!.cards!!.yellow.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedYellow[4]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(vertical = 8.dp)
                .background(Color.LightGray))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                    .clickable {
                        val intent = Intent(context, PlayersDetailedActivity::class.java)
                        intent.putExtra("top", "topY")
                        intent.putExtra("league", topBookedYellow[0]!!.statistics?.get(0)!!.league!!.name)
                        intent.putExtra("leagueLogo", topBookedYellow[0]!!.statistics?.get(0)!!.league!!.logo)
                        intent.putExtra("leagueId", topBookedYellow[0]!!.statistics?.get(0)!!.league!!.id)
                        intent.putExtra("flag", topBookedYellow[0]!!.statistics?.get(0)!!.league!!.flag)
                        intent.putExtra("season", topBookedYellow[0]!!.statistics?.get(0)!!.league!!.season)
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.tournamentPlayersTopYellow),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.size(20.dp).padding(horizontal = 5.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Open Details Icon",
                    tint = MaterialTheme.customColorsPalette.blackToWhite
                )
            }
        }
    }
}

@Composable
fun TopRedCards(topBookedRed: List<TopRedResponseItem?>) {
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
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedRed[0]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedRed[0]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedRed[0]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedRed[0]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedRed[0]!!.statistics?.get(0)!!.cards!!.red.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedRed[0]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedRed[1]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedRed[1]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedRed[1]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedRed[1]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedRed[1]!!.statistics?.get(0)!!.cards!!.red.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedRed[1]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedRed[2]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedRed[2]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedRed[2]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedRed[2]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedRed[2]!!.statistics?.get(0)!!.cards!!.red.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedRed[2]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedRed[3]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedRed[3]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedRed[3]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedRed[3]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedRed[3]!!.statistics?.get(0)!!.cards!!.red.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedRed[3]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, PlayerActivity::class.java)
                            intent.putExtra("playerId", topBookedRed[4]!!.player!!.id)
                            intent.putExtra("currentSeason", topBookedRed[4]!!.statistics!![0]!!.league!!.season)
                            context.startActivity(intent)
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = if (dark) Color.LightGray else Color.Gray
                            )
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(30.dp),
                                model = topBookedRed[4]!!.player!!.photo, contentDescription = ""
                            )
                        }
                        Text(
                            text = topBookedRed[4]!!.player!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topBookedRed[4]!!.statistics?.get(0)!!.cards!!.red.toString(),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        AsyncImage(
                            modifier = Modifier.size(30.dp),
                            model = topBookedRed[4]!!.statistics?.get(0)!!.team!!.logo!!,
                            contentDescription = ""
                        )
                    }
                }
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(vertical = 8.dp)
                .background(Color.LightGray))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                    .clickable {
                        val intent = Intent(context, PlayersDetailedActivity::class.java)
                        intent.putExtra("top", "topR")
                        intent.putExtra("league", topBookedRed[0]!!.statistics?.get(0)!!.league!!.name)
                        intent.putExtra("leagueLogo", topBookedRed[0]!!.statistics?.get(0)!!.league!!.logo)
                        intent.putExtra("leagueId", topBookedRed[0]!!.statistics?.get(0)!!.league!!.id)
                        intent.putExtra("flag", topBookedRed[0]!!.statistics?.get(0)!!.league!!.flag)
                        intent.putExtra("season", topBookedRed[0]!!.statistics?.get(0)!!.league!!.season)
                        context.startActivity(intent)
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.tournamentPlayersTopRed),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.size(20.dp).padding(horizontal = 5.dp),
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Open Details Icon",
                    tint = MaterialTheme.customColorsPalette.blackToWhite
                )
            }
        }
    }
}