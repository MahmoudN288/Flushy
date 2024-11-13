package com.elTohamy.flushy.presentation.activities.tournament.screens

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Icon
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.FixturesResponseItem
import com.elTohamy.flushy.data.state.MatchesByLeagueState
import com.elTohamy.flushy.presentation.activities.match.MatchActivity
import com.elTohamy.flushy.presentation.activities.tournament.viewModel.TournamentViewModel
import com.elTohamy.flushy.presentation.components.TournamentInPlayMatchItem
import com.elTohamy.flushy.presentation.components.TournamentMatchFinishedItem
import com.elTohamy.flushy.presentation.components.TournamentNotPlayedMatchItem
import com.elTohamy.flushy.presentation.components.TournamentNotStartedMatchItem
import com.elTohamy.flushy.presentation.components.TournamentStoppedMatchItem
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.getTimeZone
import com.elTohamy.flushy.utils.stringToLocalDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TournamentGamesScreen(navController: NavHostController, leagueId: Int, currentSeason: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchMatchesForTournament(leagueId = leagueId, currentSeason = currentSeason)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FetchMatchesForTournament(
    tournamentViewModel: TournamentViewModel = hiltViewModel(),
    leagueId: Int, currentSeason: Int
) {
    LaunchedEffect(key1 = Unit) {
        if (!tournamentViewModel.isTournamentMatchesInitialized.value) {
            tournamentViewModel.getMatchesByLeague(leagueId, currentSeason, getTimeZone())
            tournamentViewModel.isTournamentMatchesInitialized.value = true
        }
    }
    Column {
        when (
            val state = tournamentViewModel.matchesByLeagueState.collectAsState().value
        ) {
            is MatchesByLeagueState.Empty -> {}
            is MatchesByLeagueState.Loading -> {}
            is MatchesByLeagueState.Error -> {}
            is MatchesByLeagueState.Success -> {
                FixtureLazy(list = state.data.body()!!.response!!)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FixtureLazy(list: List< FixturesResponseItem?>) {
    val context = LocalContext.current

    /*
    * val firstList = list.sortedBy { it!!.fixture!!.status!!.jsonMemberLong == "Not Started" }
    val byStatusList = firstList.groupBy {
        it!!.fixture!!.status!!.jsonMemberLong
    }
    val scrollState = rememberLazyListState()
    val collapseStateSaver: Saver<MutableList<Boolean>, *> = listSaver(
        save = { it.toList() },
        restore = { it.toMutableStateList() }
    )
    val date by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
    val collapseState = rememberSaveable(
        saver = collapseStateSaver,
        inputs = arrayOf(byStatusList)
    ) {
        byStatusList.map { false }.toMutableStateList()
    }*/

    val liveGames = list.filter { it?.fixture?.status?.jsonMemberShort in listOf("1H", "2H", "HT", "ET", "BT", "P") }
    val otherGames = list - liveGames.toSet()

    val sortedOtherGames = otherGames.sortedWith(
        compareBy(
            { it!!.fixture!!.status!!.jsonMemberLong != "Not Started" }, // Live games first
            { it?.league!!.round } // Then by round
        )
    )

    val firstList = list.sortedBy { it!!.fixture!!.status!!.jsonMemberLong == "Not Started" }
    val byStatusList = firstList.groupBy {
        it!!.fixture!!.status!!.jsonMemberLong
    }
    val scrollState = rememberLazyListState()
    val collapseStateSaver: Saver<MutableList<Boolean>, *> = listSaver(
        save = { it.toList() },
        restore = { it.toMutableStateList() }
    )
    val date by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
    val collapseState = rememberSaveable(
        saver = collapseStateSaver,
        inputs = arrayOf(sortedOtherGames)
    ) {
        sortedOtherGames.map { false }.toMutableStateList()
    }

    LazyColumn(state = scrollState) {
        if (liveGames.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Live Games ${liveGames.size}",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
            items(liveGames) { game ->
                LiveMatchesItems(
                    teamHomeName = game!!.teams!!.home!!.name ?: stringResource(id = R.string.noName),
                    teamHomeLogo = game.teams!!.home!!.logo!!,
                    teamHomeR = game.goals!!.home ?: 0,
                    teamAwayName = game.teams.away!!.name ?: stringResource(id = R.string.noName),
                    teamAwayLogo = game.teams.away.logo!!,
                    teamAwayR = game.goals.away ?: 0,
                    teamHomePen =  game.score!!.penalty!!.home ?: 0,
                    teamAwayPen = game.score.penalty!!.away ?: 0,
                    elapsed = game.fixture!!.status!!.elapsed ?: 0,
                    matchItem = game
                ) {
                    val intent = Intent(context, MatchActivity::class.java)
                    intent.putExtra("matchId", game.fixture.id)
                    intent.putExtra("homeId", game.teams.home!!.id)
                    intent.putExtra("awayId", game.teams.away.id)
                    intent.putExtra("leagueId", game.league!!.id)
                    context.startActivity(intent)
                }
            }
        }
        sortedOtherGames.groupBy { it!!.fixture!!.status!!.jsonMemberLong }.values.forEachIndexed { index, fixturesResponseItems ->
            val collapsed = collapseState[index]
            item(key = "header_$index") {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RectangleShape,
                    elevation = CardDefaults.cardElevation(1.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.customColorsPalette.mainCardContainer.copy(0.2f)
                    ),
                    onClick = { collapseState[index] = !collapsed }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val fixtureItem = fixturesResponseItems.getOrNull(index)
                        Text(
                            text =
                            when (fixtureItem?.fixture?.status?.jsonMemberShort) {
                                "TBD" -> stringResource(id = R.string.tournamentGamesMatchTBD)
                                "NS" -> stringResource(id = R.string.tournamentGamesMatchNotStarted)
                                "1H", "2H", "HT", "ET", "BT", "P" -> stringResource(id = R.string.tournamentGamesMatchLive)
                                "PST" -> stringResource(id = R.string.tournamentGamesMatchPostponed)
                                "AWD" -> stringResource(id = R.string.awd)
                                "WO" -> stringResource(id = R.string.wo)
                                "CANC" -> stringResource(id = R.string.canc)
                                "ABD" -> stringResource(id = R.string.abd)
                                "FT" -> stringResource(id = R.string.ft)
                                else -> fixtureItem?.fixture?.status?.jsonMemberShort ?: ""
                            }
                        )
                        val rotationState by animateFloatAsState(
                            targetValue = if (collapseState[index]) 180f else 0f,
                            label = ""
                        )
                        IconButton(
                            modifier = Modifier
                                .rotate(rotationState)
                                .animateContentSize(
                                    animationSpec = tween(1000)
                                ),
                            onClick = { collapseState[index] = !collapsed }
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Default.ArrowDropUp,
                                contentDescription = "Collapse Icon",
                                tint = MaterialTheme.customColorsPalette.blackToWhite
                            )
                        }
                    }
                }
            }
            if (!collapsed) {
                val myList = fixturesResponseItems.sortedBy { it!!.fixture!!.status!!.jsonMemberLong == "Not Started" }
                val byRoundList = myList.groupBy { it!!.league!!.round }
                byRoundList.forEach { (round, list)->
                    item {
                        RoundMatchesHeader(round!!)
                    }
                    itemsIndexed(
                        list,
                        itemContent = { index, item ->
                            RoundMatchesItems(
                                teamHomeName = item!!.teams!!.home!!.name ?: stringResource(id = R.string.noName),
                                teamHomeLogo = item.teams!!.home!!.logo!!,
                                teamHomeR = item.goals!!.home ?: 0,
                                teamAwayName = item.teams.away!!.name ?: stringResource(id = R.string.noName),
                                teamAwayLogo = item.teams.away.logo!!,
                                teamAwayR = item.goals.away ?: 0,
                                teamHomePen =  item.score!!.penalty!!.home ?: 0,
                                teamAwayPen = item.score.penalty!!.away ?: 0,
                                elapsed = item.fixture!!.status!!.elapsed ?: 0,
                                matchItem = item,
                                date = if (item.fixture.date != null) item.fixture.date.substring(0, 10)
                                else stringResource(id = R.string.notDefined),
                                time = if (item.fixture.date != null) item.fixture.date.substring(11, 16)
                                else stringResource(id = R.string.notDefined),
                                day =
                                if (item.fixture.date != null) {
                                    if (item.fixture.date.substring(0, 10) == date.toString()) {
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
                                } else {
                                    stringResource(id = R.string.notDefined)
                                },
                                isLast = (index == list.size - 1),
                                isToday =
                                if (item.fixture.date != null) (item.fixture.date.substring(0, 10) == date.toString())
                                else false,
                                onClick = {
                                    val intent = Intent(context, MatchActivity::class.java)
                                    intent.putExtra("matchId", item.fixture.id)
                                    intent.putExtra("homeId", item.teams.home!!.id)
                                    intent.putExtra("awayId", item.teams.away.id)
                                    intent.putExtra("leagueId", item.league!!.id)
                                    context.startActivity(intent)
                                },
                            )
                        }
                    )
                    item {
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(15.dp)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                )
            }
        }
        /*byStatusList.values.forEachIndexed { index, fixturesList->
            val collapsed = collapseState[index]
            item(key = "header_$index") {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
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
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val fixtureItem = fixturesList.getOrNull(index)
                        Text(
                            text =
                            when (fixtureItem?.fixture?.status?.jsonMemberShort) {
                                "TBD" -> stringResource(id = R.string.tournamentGamesMatchTBD)
                                "NS" -> stringResource(id = R.string.tournamentGamesMatchNotStarted)
                                "1H", "2H", "HT", "ET", "BT", "P" -> stringResource(id = R.string.tournamentGamesMatchLive)
                                "PST" -> stringResource(id = R.string.tournamentGamesMatchPostponed)
                                "AWD" -> stringResource(id = R.string.awd)
                                "WO" -> stringResource(id = R.string.wo)
                                "CANC" -> stringResource(id = R.string.canc)
                                "ABD" -> stringResource(id = R.string.abd)
                                "FT" -> stringResource(id = R.string.ft)
                                else -> fixtureItem?.fixture?.status?.jsonMemberShort ?: ""
                            }
                        )
                        val rotationState by animateFloatAsState(
                            targetValue = if (collapseState[index]) 180f else 0f,
                            label = ""
                        )
                        IconButton(
                            modifier = Modifier
                                .rotate(rotationState)
                                .animateContentSize(
                                    animationSpec = tween(1000)
                                ),
                            onClick = { collapseState[index] = !collapsed }
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                imageVector = Icons.Default.ArrowDropUp,
                                contentDescription = "Collapse Icon",
                                tint = MaterialTheme.customColorsPalette.blackToWhite
                            )
                        }
                    }
                }
            }
            if (!collapsed) {
                val myList = fixturesList.sortedBy { it!!.fixture!!.status!!.jsonMemberLong == "Not Started" }
                val byRoundList = myList.groupBy { it!!.league!!.round }
                byRoundList.forEach { (round, list)->
                    item {
                        RoundMatchesHeader(round!!)
                    }
                    itemsIndexed(
                        list,
                        itemContent = { index, item ->
                            RoundMatchesItems(
                                teamHomeName = item!!.teams!!.home!!.name ?: stringResource(id = R.string.noName),
                                teamHomeLogo = item.teams!!.home!!.logo!!,
                                teamHomeR = item.goals!!.home ?: 0,
                                teamAwayName = item.teams.away!!.name ?: stringResource(id = R.string.noName),
                                teamAwayLogo = item.teams.away.logo!!,
                                teamAwayR = item.goals.away ?: 0,
                                teamHomePen =  item.score!!.penalty!!.home ?: 0,
                                teamAwayPen = item.score.penalty!!.away ?: 0,
                                elapsed = item.fixture!!.status!!.elapsed ?: 0,
                                matchItem = item,
                                date = if (item.fixture.date != null) item.fixture.date.substring(0, 10)
                                else stringResource(id = R.string.notDefined),
                                time = if (item.fixture.date != null) item.fixture.date.substring(11, 16)
                                else stringResource(id = R.string.notDefined),
                                day =
                                if (item.fixture.date != null) {
                                    if (item.fixture.date.substring(0, 10) == date.toString()) {
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
                                } else {
                                    stringResource(id = R.string.notDefined)
                                },
                                isLast = (index == list.size - 1),
                                isToday =
                                if (item.fixture.date != null) (item.fixture.date.substring(0, 10) == date.toString())
                                else false,
                                onClick = {
                                    val intent = Intent(context, MatchActivity::class.java)
                                    intent.putExtra("matchId", item.fixture.id)
                                    intent.putExtra("homeId", item.teams.home!!.id)
                                    intent.putExtra("awayId", item.teams.away.id)
                                    intent.putExtra("leagueId", item.league!!.id)
                                    context.startActivity(intent)
                                },
                            )
                        }
                    )
                    item {
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                        )
                    }
                }
            }
        }*/
        item {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            )
        }
    }
}

@Composable
fun RoundMatchesHeader(round: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(IntrinsicSize.Max)
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.matchCardHeader,
            contentColor = MaterialTheme.customColorsPalette.blackToWhite
        ),
        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = round,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            )
        }
    }
}

@Composable
fun RoundMatchesItems(
    teamHomeName: String, teamHomeLogo: String, teamHomeR: Int?,
    teamAwayName: String, teamAwayLogo: String, teamAwayR: Int?,
    teamHomePen: Int?, teamAwayPen: Int?, date: String?, time: String?,
    day: String, elapsed: Int?, matchItem: FixturesResponseItem,
    isLast: Boolean, isToday: Boolean, onClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 12.dp),
        shape = if (isLast) RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
        else RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight()
                    .height(IntrinsicSize.Max)
                    .width(IntrinsicSize.Max),
                shape = if (isLast) RoundedCornerShape(bottomStart = 15.dp)
                else RectangleShape,
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when (val shortStatusState = matchItem.fixture!!.status!!.jsonMemberShort) {
                        "TBD", "NS" -> {
                            TournamentNotStartedMatchItem(
                                shortStatusState = shortStatusState,
                                date = date!!,
                                day = day,
                                time = time!!,
                                today = isToday
                            )
                        }
                        "1H", "HT", "2H", "ET", "BT", "P" -> {
                            TournamentInPlayMatchItem(
                                elapsedTime = elapsed!!,
                                shortStatusState = shortStatusState
                            )
                        }
                        "FT", "AET", "PEN" -> {
                            TournamentMatchFinishedItem(shortStatusState = shortStatusState)
                        }
                        "SUSP", "INT" -> {
                            TournamentStoppedMatchItem(shortStatusState = shortStatusState)
                        }
                        "CANC", "WO", "ABD", "AWD", "PST" -> {
                            TournamentNotPlayedMatchItem(shortStatusState = shortStatusState)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(3.5f)
                    .width(IntrinsicSize.Max)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(2f)
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(IntrinsicSize.Max)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(25.dp),
                                    model = teamHomeLogo,
                                    contentDescription = "Team Home"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = teamHomeName,
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                            when (matchItem.fixture!!.status!!.jsonMemberShort) {
                                "FT", "AET", "SUSP", "INT" -> {
                                    Text(
                                        modifier = Modifier,
                                        text = teamHomeR.toString(),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight =
                                            if (matchItem.teams!!.home!!.winner != null) {
                                                if (matchItem.teams.home!!.winner == true) FontWeight.SemiBold
                                                else FontWeight.Normal
                                            } else {
                                                FontWeight.Normal
                                            },
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                                "PEN" -> {
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = teamHomeR.toString(),
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamHomeR!! > teamAwayR!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 13.sp
                                            )
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = "(${stringResource(id = R.string.pen)}: $teamHomePen)",
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamHomePen!! > teamAwayPen!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 12.sp
                                            )
                                        )
                                    }
                                }
                                "1H", "2H", "ET", "BT" -> {
                                    Text(
                                        modifier = Modifier,
                                        text = teamHomeR.toString(),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight =
                                            if (teamHomeR!! > teamAwayR!!) FontWeight.SemiBold
                                            else FontWeight.Normal,
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                                "P" -> {
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = teamHomeR.toString(),
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamHomeR!! > teamAwayR!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 13.sp
                                            )
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = "(${stringResource(id = R.string.p)}: $teamHomePen)",
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamHomePen!! > teamAwayPen!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 12.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(IntrinsicSize.Max)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(25.dp),
                                    model = teamAwayLogo,
                                    contentDescription = "Team Away"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = teamAwayName,
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                            when (matchItem.fixture!!.status!!.jsonMemberShort) {
                                "FT", "AET", "SUSP", "INT" -> {
                                    Text(
                                        modifier = Modifier,
                                        text = teamAwayR.toString(),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight =
                                            if (matchItem.teams!!.away!!.winner != null) {
                                                if (matchItem.teams.away!!.winner == true) FontWeight.SemiBold
                                                else FontWeight.Normal
                                            } else {
                                                FontWeight.Normal
                                            },
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                                "PEN" -> {
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = teamAwayR.toString(),
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamAwayR!! > teamHomeR!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 13.sp
                                            )
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = "(${stringResource(id = R.string.pen)}: $teamAwayPen)",
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamAwayPen!! > teamHomePen!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 12.sp
                                            )
                                        )
                                    }
                                }
                                "1H", "2H", "ET", "BT" -> {
                                    Text(
                                        modifier = Modifier,
                                        text = teamAwayR.toString(),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight =
                                            if (teamAwayR!! > teamHomeR!!) FontWeight.SemiBold
                                            else FontWeight.Normal,
                                            fontSize = 13.sp
                                        )
                                    )
                                }
                                "P" -> {
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier,
                                            text = teamAwayR.toString(),
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamAwayR!! > teamHomeR!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 13.sp
                                            )
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = "(${stringResource(id = R.string.p)}: $teamAwayPen)",
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                fontWeight =
                                                if (teamAwayPen!! > teamHomePen!!) FontWeight.SemiBold
                                                else FontWeight.Normal,
                                                fontSize = 12.sp
                                            )
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
}

@Composable
fun LiveMatchesItems(
    teamHomeName: String, teamHomeLogo: String, teamHomeR: Int?,
    teamAwayName: String, teamAwayLogo: String, teamAwayR: Int?,
    teamHomePen: Int?, teamAwayPen: Int?, elapsed: Int?,
    matchItem: FixturesResponseItem,
    onClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight()
                    .height(IntrinsicSize.Max)
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = "$elapsed'",
                    style = TextStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    )
                )
                Text(
                    text = matchItem.fixture?.status?.jsonMemberLong.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }

            Column(
                modifier = Modifier
                    .weight(3.5f)
                    .width(IntrinsicSize.Max)
                    .fillMaxHeight()
                    .height(IntrinsicSize.Max)
                    .padding(5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier.weight(0.5f)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .width(IntrinsicSize.Max),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        Modifier.fillMaxSize()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(25.dp),
                                model = teamHomeLogo,
                                contentDescription = "Team Home"
                            )
                            Text(
                                text = teamHomeName,
                                style = TextStyle(
                                    color = if (teamHomeR!! > teamAwayR!!)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                    fontWeight = if (teamHomeR > teamAwayR) FontWeight.SemiBold
                                        else FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = matchItem.goals?.home.toString(),
                                style = TextStyle(
                                    color = if (teamHomeR!! > teamAwayR!!)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                    fontWeight = if (teamHomeR > teamAwayR) FontWeight.SemiBold
                                    else FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            if (matchItem.fixture?.status?.jsonMemberShort == "PEN") {
                                Text(
                                    text = teamHomePen.toString(),
                                    style = TextStyle(
                                        color = if (teamHomePen!! > teamAwayPen!!) MaterialTheme.customColorsPalette.blackToWhite
                                        else MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                        fontWeight = if (teamHomePen > teamAwayPen) FontWeight.SemiBold
                                        else FontWeight.Normal,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                    }
                }
                Card(
                    modifier = Modifier.weight(0.5f)
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                        .width(IntrinsicSize.Max),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        Modifier.fillMaxSize()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier.size(25.dp),
                                model = teamAwayLogo,
                                contentDescription = "Team Home"
                            )
                            Text(
                                text = teamAwayName,
                                style = TextStyle(
                                    color = if (teamAwayR!! > teamHomeR!!)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                        else MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                    fontWeight = if (teamAwayR > teamHomeR) FontWeight.SemiBold
                                        else FontWeight.Normal,
                                    fontSize = 14.sp
                                )
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = teamAwayR.toString(),
                                style = TextStyle(
                                    color = if (teamAwayR!! > teamHomeR!!)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                    fontWeight = if (teamAwayR > teamHomeR) FontWeight.SemiBold
                                    else FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            if (matchItem.fixture?.status?.jsonMemberShort == "PEN") {
                                Text(
                                    text = teamHomePen.toString(),
                                    style = TextStyle(
                                        color = if (teamHomePen!! < teamAwayPen!!) MaterialTheme.customColorsPalette.blackToWhite
                                        else MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                                        fontWeight = if (teamHomePen < teamAwayPen) FontWeight.SemiBold
                                        else FontWeight.Normal,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}