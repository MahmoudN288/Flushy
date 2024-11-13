package com.elTohamy.flushy.presentation.activities.team.tabRow.screens

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.FixturesResponseItem
import com.elTohamy.flushy.data.state.TeamMatchesState
import com.elTohamy.flushy.presentation.activities.match.MatchActivity
import com.elTohamy.flushy.presentation.activities.team.viewModel.TeamViewModel
import com.elTohamy.flushy.presentation.activities.tournament.TournamentActivity
import com.elTohamy.flushy.presentation.components.InPlayMatchItem
import com.elTohamy.flushy.presentation.components.MatchFinishedItem
import com.elTohamy.flushy.presentation.components.NotPlayedMatchItem
import com.elTohamy.flushy.presentation.components.NotStartedMatchItem
import com.elTohamy.flushy.presentation.components.StoppedMatchItem
import com.elTohamy.flushy.presentation.bottomNavBar.liveGamesScreen.components.TodayLeaguesList
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.getTournamentName
import com.elTohamy.flushy.utils.stringToLocalDate
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeamGamesScreen(team: Int, season: Int) {
    FetchTeamMatchesList(team = team, season = season)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FetchTeamMatchesList(
    teamViewModel: TeamViewModel = hiltViewModel(),
    team: Int, season: Int
) {
    LaunchedEffect(key1 = teamViewModel.isTeamMatchesInitialized.value) {
        if (!teamViewModel.isTeamMatchesInitialized.value) {
            teamViewModel.getTeamMatches(team, season)
            teamViewModel.isTeamMatchesInitialized.value = true
        }
    }
    when (val state = teamViewModel.teamMatchesState.collectAsState().value) {
        is TeamMatchesState.Empty -> {}
        is TeamMatchesState.Loading -> {}
        is TeamMatchesState.Error -> {}
        is TeamMatchesState.Success -> {
            TeamMatchesList(matchesList = state.data.body()!!.response!!)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TeamMatchesList(
    matchesList: List<FixturesResponseItem?>,
    teamViewModel: TeamViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val date by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
    LaunchedEffect(key1 = null) {
        scope.launch { scope.launch { teamViewModel.mainList.value = matchesList.filter { item->
            matchesList.sortedBy { item!!.fixture!!.status!!.jsonMemberShort }
            item?.league?.id == TodayLeaguesList[0] || item?.league?.id == TodayLeaguesList[1] ||
                    item?.league?.id == TodayLeaguesList[2] || item?.league?.id == TodayLeaguesList[3] ||
                    item?.league?.id == TodayLeaguesList[4] || item?.league?.id == TodayLeaguesList[5] ||
                    item?.league?.id == TodayLeaguesList[6] || item?.league?.id == TodayLeaguesList[7] ||
                    item?.league?.id == TodayLeaguesList[8] || item?.league?.id == TodayLeaguesList[9] ||
                    item?.league?.id == TodayLeaguesList[10] || item?.league?.id == TodayLeaguesList[11] ||
                    item?.league?.id == TodayLeaguesList[12] || item?.league?.id == TodayLeaguesList[13] ||
                    item?.league?.id == TodayLeaguesList[14] || item?.league?.id == TodayLeaguesList[15] ||
                    item?.league?.id == TodayLeaguesList[16] || item?.league?.id == TodayLeaguesList[17] ||
                    item?.league?.id == TodayLeaguesList[18] || item?.league?.id == TodayLeaguesList[19] ||
                    item?.league?.id == TodayLeaguesList[20] || item?.league?.id == TodayLeaguesList[21] ||
                    item?.league?.id == TodayLeaguesList[22] || item?.league?.id == TodayLeaguesList[23] ||
                    item?.league?.id == TodayLeaguesList[24] || item?.league?.id == TodayLeaguesList[25] ||
                    item?.league?.id == TodayLeaguesList[26] || item?.league?.id == TodayLeaguesList[27] ||
                    item?.league?.id == TodayLeaguesList[28] || item?.league?.id == TodayLeaguesList[29] ||
                    item?.league?.id == TodayLeaguesList[30] || item?.league?.id == TodayLeaguesList[31] ||
                    item?.league?.id == TodayLeaguesList[32] || item?.league?.id == TodayLeaguesList[33] ||
                    item?.league?.id == TodayLeaguesList[34] || item?.league?.id == TodayLeaguesList[35] ||
                    item?.league?.id == TodayLeaguesList[36] || item?.league?.id == TodayLeaguesList[37] ||
                    item?.league?.id == TodayLeaguesList[38] || item?.league?.id == TodayLeaguesList[39] ||
                    item?.league?.id == TodayLeaguesList[40] || item?.league?.id == TodayLeaguesList[41] ||
                    item?.league?.id == TodayLeaguesList[42] || item?.league?.id == TodayLeaguesList[43] ||
                    item?.league?.id == TodayLeaguesList[44] || item?.league?.id == TodayLeaguesList[45] ||
                    item?.league?.id == TodayLeaguesList[46] || item?.league?.id == TodayLeaguesList[47] ||
                    item?.league?.id == TodayLeaguesList[48] || item?.league?.id == TodayLeaguesList[49] ||
                    item?.league?.id == TodayLeaguesList[50] || item?.league?.id == TodayLeaguesList[51] ||
                    item?.league?.id == TodayLeaguesList[52] || item?.league?.id == TodayLeaguesList[53] ||
                    item?.league?.id == TodayLeaguesList[54] || item?.league?.id == TodayLeaguesList[55] ||
                    item?.league?.id == TodayLeaguesList[56] || item?.league?.id == TodayLeaguesList[57] ||
                    item?.league?.id == TodayLeaguesList[58] || item?.league?.id == TodayLeaguesList[59] ||
                    item?.league?.id == TodayLeaguesList[60] || item?.league?.id == TodayLeaguesList[61] ||
                    item?.league?.id == TodayLeaguesList[62] || item?.league?.id == TodayLeaguesList[63] ||
                    item?.league?.id == TodayLeaguesList[64] || item?.league?.id == TodayLeaguesList[65] ||
                    item?.league?.id == TodayLeaguesList[66] || item?.league?.id == TodayLeaguesList[67] ||
                    item?.league?.id == TodayLeaguesList[68] || item?.league?.id == TodayLeaguesList[69] ||
                    item?.league?.id == TodayLeaguesList[70] || item?.league?.id == TodayLeaguesList[71] ||
                    item?.league?.id == TodayLeaguesList[72] || item?.league?.id == TodayLeaguesList[73] ||
                    item?.league?.id == TodayLeaguesList[74] || item?.league?.id == TodayLeaguesList[75] ||
                    item?.league?.id == TodayLeaguesList[76] || item?.league?.id == TodayLeaguesList[77] ||
                    item?.league?.id == TodayLeaguesList[78] || item?.league?.id == TodayLeaguesList[79] ||
                    item?.league?.id == TodayLeaguesList[80] || item?.league?.id == TodayLeaguesList[81] ||
                    item?.league?.id == TodayLeaguesList[82]
        } } }
    }
    val myList = teamViewModel.mainList.value.groupBy { item -> item!!.fixture!!.status!!.jsonMemberLong }
    val collapseState = remember(myList) { myList.map { false }.toMutableStateList() }
    if (myList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
        ) {
            myList.values.forEachIndexed { index, items ->
                val collapsed = collapseState[index]
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        ),
                        onClick = { collapseState[index] = !collapsed }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier,
                                text = items[0]!!.fixture!!.status!!.jsonMemberLong!!,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                            val rotationState by animateFloatAsState(
                                targetValue = if (collapseState[index]) 180f else 0f,
                                label = ""
                            )
                            IconButton(
                                modifier = Modifier.rotate(rotationState)
                                    .animateContentSize(
                                        animationSpec = tween(1000)
                                    ),
                                onClick = { collapseState[index] = !collapsed }
                            ) {
                                Icon(
                                    modifier = Modifier.size(15.dp),
                                    imageVector = Icons.Default.ArrowDropUp,
                                    contentDescription = "Drop Up"
                                )
                            }
                        }
                    }
                }
                val list = items.groupBy { it!!.league!!.id }
                if (!collapsed) {
                    list.forEach { (league, fixtures) ->
                        item {
                            TeamMatchHeader(matchItem = items[0]!!, league = league!!)
                        }
                        itemsIndexed(fixtures,
                            itemContent = { _, item ->
                                TeamMatchItem(
                                    teamHomeName = item!!.teams!!.home!!.name!!,
                                    teamHomeLogo = item.teams!!.home!!.logo!!,
                                    teamHomeR = item.goals!!.home ?: 0,
                                    teamAwayName = item.teams.away!!.name!!,
                                    teamAwayLogo = item.teams.away.logo!!,
                                    teamAwayR = item.goals.away,
                                    teamHomePen =  item.score!!.penalty!!.home ?: 0,
                                    teamAwayPen = item.score.penalty!!.away ?: 0,
                                    elapsed = item.fixture!!.status!!.elapsed ?: 0,
                                    matchItem = item,
                                    onClick = {
                                        val intent = Intent(context, MatchActivity::class.java)
                                        intent.putExtra("matchId", item.fixture.id)
                                        intent.putExtra("homeId", item.teams.home!!.id)
                                        intent.putExtra("awayId", item.teams.away.id)
                                        intent.putExtra("leagueId", item.league!!.id)
                                        context.startActivity(intent)
                                    },
                                    time = if (item.fixture.date!!.substring(0, 10) == date.toString())
                                        item.fixture.date.substring(11, 16)
                                    else item.fixture.date.substring(0, 10),
                                    day = if (item.fixture.date.substring(0, 10) == date.toString()) {
                                        stringResource(id = R.string.today)
                                    }
                                    else when (stringToLocalDate(item.fixture.date.substring(0, 10)).dayOfWeek.name) {
                                        "SATURDAY" -> stringResource(id = R.string.saturday)
                                        "SUNDAY" -> stringResource(id = R.string.sunday)
                                        "MONDAY" -> stringResource(id = R.string.monday)
                                        "TUESDAY" -> stringResource(id = R.string.tuesday)
                                        "WEDNESDAY" -> stringResource(id = R.string.wednesday)
                                        "THURSDAY" -> stringResource(id = R.string.thursday)
                                        "FRIDAY" -> stringResource(id = R.string.friday)
                                        else -> "None"
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TeamMatchHeader(
    matchItem: FixturesResponseItem, league: Int
) {
    val context = LocalContext.current
    if (matchItem.league!!.id == league) {
        Card(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults
                .cardElevation(defaultElevation = 8.dp),
            colors = CardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer,
                contentColor = MaterialTheme.customColorsPalette.blackToWhite,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            onClick = {
                val intent = Intent(context, TournamentActivity::class.java)
                intent.putExtra("leagueId", league)
                intent.putExtra("currentSeason", matchItem.league.season)
                intent.putExtra("leagueCountry", matchItem.league.country)
                intent.putExtra("leagueName", matchItem.league.name)
                context.startActivity(intent)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(width = 25.dp, height = 25.dp),
                    model = matchItem.league.logo,
                    contentDescription = "League Logo"
                )
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = getTournamentName(
                        id = matchItem.league.id!!,
                        jsonName = matchItem.league.name!!
                    ),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun TeamMatchItem(
    teamHomeName: String, teamHomeLogo: String, teamHomeR: Int?,
    teamAwayName: String, teamAwayLogo: String, teamAwayR: Int?,
    teamHomePen: Int?, teamAwayPen: Int?, time: String?, day: String,
    elapsed: Int?, matchItem: FixturesResponseItem,
    onClick: ()-> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
        elevation = CardDefaults
            .cardElevation(defaultElevation = 8.dp),
        colors = CardColors(
            containerColor = MaterialTheme.customColorsPalette.matchCard,
            contentColor = MaterialTheme.customColorsPalette.blackToWhite,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        onClick = { onClick() }
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .weight(10f)
                    .padding(vertical = 5.dp, horizontal = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(IntrinsicSize.Max)
                        .padding(vertical = 10.dp, horizontal = 2.dp)
                        .weight(3.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                modifier = Modifier.weight(2f),
                                text = teamHomeName,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .size(width = 30.dp, height = 30.dp),
                                model = teamHomeLogo,
                                contentDescription = "Team Home"
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    when (val shortStatusState = matchItem.fixture!!.status!!.jsonMemberShort) {
                        "NS", "TBD" -> {
                            NotStartedMatchItem(
                                shortStatusState = shortStatusState,
                                time = time!!,
                                inHeader = false
                            )
                        }
                        "1H", "HT", "2H", "ET", "BT", "P" -> {
                            InPlayMatchItem(
                                goalsHome = teamHomeR!!,
                                goalsAway = teamAwayR!!,
                                elapsedTime = elapsed!!,
                                shortStatusState = shortStatusState,
                                penaltyScoreHome = teamHomePen!!,
                                penaltyScoreAway = teamAwayPen!!,
                                inHeader = false
                            )
                        }
                        "FT", "AET", "PEN" -> {
                            MatchFinishedItem(
                                goalsHome = teamHomeR!!,
                                goalsAway = teamAwayR!!,
                                shortStatusState = shortStatusState,
                                penaltyScoreHome = teamHomePen!!,
                                penaltyScoreAway = teamAwayPen!!,
                                inHeader = false
                            )
                        }
                        "SUSP", "INT" -> {
                            StoppedMatchItem(
                                goalsHome = teamHomeR!!,
                                goalsAway = teamAwayR!!,
                                shortStatusState = shortStatusState,
                                inHeader = false
                            )
                        }
                        "CANC", "WO", "ABD", "AWD" -> {
                            NotPlayedMatchItem(
                                shortStatusState = shortStatusState,
                                inHeader = false
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(3.5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .size(width = 25.dp, height = 25.dp),
                                model = teamAwayLogo,
                                contentDescription = "Team Away"
                            )
                            Text(
                                modifier = Modifier.weight(2f),
                                text = teamAwayName,
                                textAlign = TextAlign.Start,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}