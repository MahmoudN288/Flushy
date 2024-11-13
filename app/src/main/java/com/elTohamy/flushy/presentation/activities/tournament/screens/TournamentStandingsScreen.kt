package com.elTohamy.flushy.presentation.activities.tournament.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material3.ripple
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.leagues.leagueById.LeaguesByIdResponseItem
import com.elTohamy.flushy.data.remote.dto.standings.StandingsItemItem
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.AFCCL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.AFCCup
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.AFCON
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.ArabCup
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Argentina
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.AsianCup
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Belgium
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Botola
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Brazil
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Bundesliga
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.CAFCL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.CAFCONF
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.CONCL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.CONMEBOL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.CopaAmerica
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.EgyptianPL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Euro
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FCWC
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWC
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCQAfrica
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCQAsia
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCQEurope
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCQNorthAmerica
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCQOFC
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCQSouthAmerica
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.FWCWomen
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.GoldCup
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Italy
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.LaLiga
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Libertadores
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.LigueA
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Netherlands
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.PL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Portugal
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Roshan
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Tunisia
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.Turkey
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.UCL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.UECL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.UEL
import com.elTohamy.flushy.presentation.activities.tournament.rankingKeys.UNL
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.DarkBlue
import com.elTohamy.flushy.presentation.ui.theme.DarkGreen
import com.elTohamy.flushy.presentation.ui.theme.DarkRed
import com.elTohamy.flushy.presentation.ui.theme.LightBlue
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.presentation.ui.theme.Orange
import com.elTohamy.flushy.presentation.ui.theme.Yellow
import com.elTohamy.flushy.data.state.LeagueStandingsState
import com.elTohamy.flushy.data.state.TournamentLeagueInfoState
import com.elTohamy.flushy.presentation.activities.team.TeamActivity
import com.elTohamy.flushy.presentation.activities.tournament.viewModel.TournamentViewModel
import com.elTohamy.flushy.presentation.animations.loader.CircularLoader
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.Constants
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun TournamentStandingsScreen(navController: NavHostController, leagueId: Int, currentSeason: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchData(leagueId = leagueId, currentSeason = currentSeason)
    }
}

@Composable
fun FetchData(
    tournamentViewModel: TournamentViewModel = hiltViewModel(), leagueId: Int, currentSeason: Int
) {
    LaunchedEffect(key1 = null) {
        if (!tournamentViewModel.isInitializedStandingsTournament.value) {
            tournamentViewModel.getLeagueStandings(league = leagueId, season = currentSeason)
            tournamentViewModel.isInitializedStandingsTournament.value = true
        }
    }
    Column {
        when (val stateA = tournamentViewModel.leagueInfoState.collectAsState().value) {
            is TournamentLeagueInfoState.Empty -> {
                CircularLoader()
            }
            is TournamentLeagueInfoState.Loading -> {
                CircularLoader()
            }
            is TournamentLeagueInfoState.Error -> {
                Log.d(Constants.TAG, stateA.message)
            }
            is TournamentLeagueInfoState.Success -> {
                when (val stateB = tournamentViewModel.leagueStandingsState.collectAsState().value) {
                    is LeagueStandingsState.Empty -> {
                        CircularLoader()
                    }
                    is LeagueStandingsState.Loading -> {
                        CircularLoader()
                    }
                    is LeagueStandingsState.Error -> {
                        Log.d(Constants.TAG, stateB.message)
                    }
                    is LeagueStandingsState.Success -> {
                        StandingsPL(
                            rankList = stateB.list,
                            rankListOfList = stateB.listOfList,
                            leagueItem = stateA.data.body()!!.response?.get(0)!!
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StandingsPL(
    leagueItem: LeaguesByIdResponseItem, rankList: List<StandingsItemItem?>,
    rankListOfList: List<List<StandingsItemItem?>?>?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (leagueItem.league!!.type == "League") {
            LazyColumn {
                item { StandingHeader() }
                items(rankList.size) {
                    StandingRow(standingsItem = rankList[it]!!, season = leagueItem.seasons!![0]!!.year!!)
                }
                item { MarksExplanation(leagueItem = leagueItem) }
            }
        } else if (leagueItem.league.type == "Cup") {
            CupStandingExpandableGroup(
                standingsItemListOfList = rankListOfList,
                season = leagueItem.seasons!![0]!!.year!!,
                headerItem = { StandingHeader() }
            ) { MarksExplanation(leagueItem = leagueItem) }
        }
    }
}

@Composable
fun StandingRow(standingsItem: StandingsItemItem, season: Int) {
    val context = LocalContext.current
    BoxWithLayout {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .weight(10f)
                .padding(horizontal = 4.dp, vertical = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true, color = MaterialTheme.colorScheme.primary),
                    onClick = {
                        val intent = Intent(context, TeamActivity::class.java)
                        intent.putExtra("teamId", standingsItem.team!!.id)
                        intent.putExtra("currentSeason", season)
                        intent.putExtra("teamName", standingsItem.team.name)
                        context.startActivity(intent)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*if (leagueItem.league!!.type == "Cup") {
                if (
                    leagueItem.league.id == Constants.PREMIER_LEAGUE_ID || leagueItem.league.id == Constants.CHAMPIONSHIP ||
                    leagueItem.league.id == Constants.LA_LIGA || leagueItem.league.id == Constants.SERIE_A ||
                    leagueItem.league.id == Constants.BUNDESLIGA || leagueItem.league.id == Constants.LIGUE_1 ||
                    leagueItem.league.id == Constants.PRIMEIRA_LIGA || leagueItem.league.id == Constants.SUPER_LIG ||
                    leagueItem.league.id == Constants.EREDIVISIE || leagueItem.league.id == Constants.SERIE_A_BRA ||
                    leagueItem.league.id == Constants.LIGA_ARG || leagueItem.league.id == Constants.PRO_LEAGUE_SAU ||
                    leagueItem.league.id == Constants.PREMIER_LEAGUE_EGY || leagueItem.league.id == Constants.LIGUE_1_TUN ||
                    leagueItem.league.id == Constants.BOTOLA_PRO || leagueItem.league.id == Constants.SUPER_LIG
                    ) {
                }
            }*/
            when (standingsItem.description) {
                "Promotion - Champions League (Group Stage: )",
                "Promotion - Champions League (Play Offs: 1/8-finals)",
                "Promotion - Europa League (Play Offs: 1/8-finals)",
                "Promotion - Europa Conference League (Play Offs: 1/8-finals)",
                "CONMEBOL Libertadores", "AFC Champions League", "CAF Champions League",
                "Promotion - CAF Champions League (Play Offs: )",
                "Promotion - Copa Libertadores (Play Offs: 1/8-finals)",
                "Promotion - World Cup (Play Offs)", "Promotion - Euro (Play Offs)",
                "Promotion - UEFA Nations League (League A - Play Offs)",
                "Promotion - UEFA Nations League (League A: )",
                "Promotion - Africa Cup of Nations (Play Offs: )", "Promotion - Asian Cup (Play Offs: )",
                "Promotion - Copa Sudamericana (Play Offs: 1/8-finals)", "Promotion - World Championship",
                "Promotion - World Cup", "", "UEFA Champions League" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(DarkGreen)
                    ) {
                    }
                }
                "Promotion - Champions League (Qualification: )", "CONMEBOL Libertadores Qualifiers",
                "UEFA Champions League Qualifiers" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(LightGreen)
                    ) {
                    }
                }
                "Promotion - Europa League (Group Stage: )", "Promotion - Europa League (Play Offs: 1/16-finals)",
                "CONMEBOL Sudamericana", "CAF Confederation Cup", "Promotion - Copa Sudamericana (Play Offs: 1/16-finals)",
                "Ranking of third-placed teams", "Relegation - UEFA Nations League (League B: )",
                "Promotion - UEFA Nations League (League B: )", "Ranking of second-placed teams",
                "Promotion - World Cup (Fourth stage)", "Promotion - World Cup (Promotion)" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(Orange)
                    ) {
                    }
                }
                "Promotion - Europa League (Qualification: )", "CONMEBOL Sudamericana Group Stage",
                "UEFA Europa League Qualifiers" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(Yellow)
                    ) {
                    }
                }
                "Promotion - Europa Conference League (Group Stage: )",
                "Promotion - Europa Conference League (Play Offs: 1/16-finals)",
                "Relegation - UEFA Nations League (League C: )", "Promotion - UEFA Nations League (League C)",
                "UEFA Nations League (League C - Play Out: )", "UEFA Conference League Play-offs" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(DarkBlue)
                    ) {
                    }
                }
                "Promotion - Europa Conference League (Qualification: )", "Championship Round",
                "Promotion - Eredivisie (Conference League - Play Offs: )" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(LightBlue)
                    ) {
                    }
                }
                "Relegation Play-off" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(LightRed)
                    ) {
                    }
                }
                "Relegation - Championship", "Relegation - LaLiga2", "Relegation - Serie B",
                "Relegation - 2. Bundesliga", "Relegation - Ligue 2", "Relegation - Liga Portugal 2",
                "Relegation - 1. Lig", "Relegation - Eerste Divisie", "Relegation Round", "Relegation" -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(DarkRed)
                    ) {
                    }
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(Color.Transparent)
                    ) {
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 4.dp, top = 6.dp, bottom = 6.dp)
                    .weight(3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(30.dp)
                        .padding(start = 2.dp, end = 3.dp),
                    text = standingsItem.rank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                AsyncImage(
                    modifier = Modifier
                        .size(width = 25.dp, height = 25.dp)
                        .padding(horizontal = 3.dp),
                    model = standingsItem.team!!.logo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(120.dp),
                    text = standingsItem.team.name!!,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(3.dp)
                    .weight(7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.width(30.dp),
                    text = standingsItem.all!!.played.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = standingsItem.all.win.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = standingsItem.all.draw.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = standingsItem.all.lose.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(60.dp),
                    text =
                    "${standingsItem.all.goals!!.jsonMemberFor.toString()}-${standingsItem.all.goals.against.toString()}",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(40.dp),
                    text = standingsItem.goalsDiff.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(50.dp),
                    text = standingsItem.points.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun StandingHeader() {
    val dark = LocalTheme.current.isDark
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .padding(vertical = 3.dp, horizontal = 7.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(30.dp),
                    text = stringResource(id = R.string.rank),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .width(70.dp),
                    text = stringResource(id = R.string.team),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.width(30.dp),
                    text = stringResource(id = R.string.played),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = stringResource(id = R.string.won),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = stringResource(id = R.string.draw),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = stringResource(id = R.string.loss),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(60.dp),
                    text = stringResource(id = R.string.goals),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(40.dp),
                    text = stringResource(id = R.string.goalDiff),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(50.dp),
                    text = stringResource(id = R.string.points),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun CupStandingExpandableGroup(
    tournamentViewModel: TournamentViewModel = hiltViewModel(),
    standingsItemListOfList: List<List<StandingsItemItem?>?>?,
    season: Int,
    headerItem: @Composable () -> Unit,
    keys: @Composable () -> Unit
) {
    tournamentViewModel.standingsListTournament.value = standingsItemListOfList
    val groupedStandingsList =
        tournamentViewModel.standingsListTournament.value!!.groupBy { listOfList-> listOfList!!.groupBy { list-> list!!.group } }
    LazyColumn {
        item { headerItem() }
        groupedStandingsList.values.forEachIndexed { _, listOfListOfObjects ->
            listOfListOfObjects.forEachIndexed { _, listOfObjects ->
                item { Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)) }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor,
                            contentColor = MaterialTheme.customColorsPalette.blackToWhite
                        ),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(6f),
                                    text = listOfObjects?.get(0)!!.group!!,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                IconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .alpha(ContentAlpha.medium),
                                    onClick = {
                                    }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowUp,
                                        contentDescription = "Drop-Down Arrow"
                                    )
                                }
                            }
                        }
                    }
                }
                items(listOfObjects!!.size) {
                    StandingRow(standingsItem = listOfObjects[it]!!, season = season)
                }
            }
        }
        item { Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)) }
        item { keys() }
    }
}

@Composable
fun MarksExplanation(leagueItem: LeaguesByIdResponseItem) {
    when (leagueItem.league!!.id) {
        //Club
        //FCWC
        15 -> { FCWC() }
        //England
        39 -> { PL() }
        //Germany
        78 -> { Bundesliga() }
        //France
        61 -> { LigueA() }
        //Netherlands
        88 -> { Netherlands() }
        //Portugal
        94 -> { Portugal() }
        //Italy
        135 -> { Italy() }
        //Spain
        140 -> { LaLiga() }
        //Belgium
        144 -> { Belgium() }
        //Turkey
        203 -> { Turkey() }
        //Brazil
        71 -> { Brazil() }
        //Argentina
        128 -> { Argentina() }
        //Saudi Arabia
        307 -> { Roshan() }
        //Egypt
        233 -> { EgyptianPL() }
        //Tunisia
        202 -> { Tunisia() }
        //Morocco
        200 -> { Botola() }
        //Uefa
        2 -> { UCL() }
        3 -> { UEL() }
        848 -> { UECL() }
        //Africa
        12 -> { CAFCL() }
        20 -> { CAFCONF() }
        //Asia
        17 -> { AFCCL() }
        18 -> { AFCCup() }
        //South Am
        13 -> { Libertadores() }
        //North Am
        16 -> { CONCL() }
        //International
        //FWC + Q
        1 -> { FWC() }
        //Africa
        29 -> { FWCQAfrica() }
        //Asia
        30 -> { FWCQAsia() }
        //North Am
        31 -> { FWCQNorthAmerica() }
        //Europe
        32 -> { FWCQEurope() }
        //OFC
        33 -> { FWCQOFC() }
        //South America
        34 -> { FWCQSouthAmerica() }
        //Euro
        4 -> { Euro() }
        //UNL
        5 -> { UNL() }
        //AFCON
        6 -> { AFCON() }
        //Asian Cup
        7 -> { AsianCup() }
        //FWC W
        8 -> { FWCWomen() }
        //Copa America
        9 -> { CopaAmerica() }
        //CONMEBOL
        11 -> { CONMEBOL() }
        //Arab Cup
        860 -> { ArabCup() }
        //Gold Cup
        22 -> { GoldCup() }
    }
}