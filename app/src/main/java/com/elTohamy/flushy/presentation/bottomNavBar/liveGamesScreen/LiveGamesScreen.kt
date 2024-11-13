package com.elTohamy.flushy.presentation.bottomNavBar.liveGamesScreen

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.today.TodayGamesResponseItem
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.data.state.TodayMatchesState
import com.elTohamy.flushy.presentation.activities.match.MatchActivity
import com.elTohamy.flushy.presentation.activities.tournament.TournamentActivity
import com.elTohamy.flushy.presentation.animations.screens.LiveGamesShimmer
import com.elTohamy.flushy.presentation.bottomNavBar.liveGamesScreen.components.TodayLeaguesList
import com.elTohamy.flushy.presentation.components.InPlayMatchItem
import com.elTohamy.flushy.presentation.components.MatchFinishedItem
import com.elTohamy.flushy.presentation.components.NotPlayedMatchItem
import com.elTohamy.flushy.presentation.components.NotStartedMatchItem
import com.elTohamy.flushy.presentation.components.StoppedMatchItem
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.model.LeagueWithMatches
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.getTimeZone
import com.elTohamy.flushy.utils.getTournamentName
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveGamesScreen(navController: NavHostController, authUiClient: AuthUiClient) {
    val datePickerState = rememberDatePickerState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val selectedDate1 = Instant.ofEpochMilli(datePickerState.selectedDateMillis ?: 0)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

                var showDatePicker by remember {
                    mutableStateOf(false)
                }
                if (showDatePicker)
                    BottomSheetDatePicker(
                        state = datePickerState,
                        onDismissRequest = { showDatePicker = false }
                    )
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                        },
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    val date by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
                    val day =
                        if (datePickerState.selectedDateMillis != null) {
                            when (selectedDate1!!.dayOfWeek.name) {
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
                            when (date!!.dayOfWeek.name) {
                                "SATURDAY" -> stringResource(id = R.string.saturday)
                                "SUNDAY" -> stringResource(id = R.string.sunday)
                                "MONDAY" -> stringResource(id = R.string.monday)
                                "TUESDAY" -> stringResource(id = R.string.tuesday)
                                "WEDNESDAY" -> stringResource(id = R.string.wednesday)
                                "THURSDAY" -> stringResource(id = R.string.thursday)
                                "FRIDAY" -> stringResource(id = R.string.friday)
                                else -> "None"
                            }
                        }
                    Text(
                        text = if (datePickerState.selectedDateMillis != null)
                            "$selectedDate1: $day"
                        else
                            "$date: $day",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    )
                }
                IconButton(
                    onClick = {
                        showDatePicker = true
                    }
                ) {
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "Calendar")
                }
            }
        }
    ) { scaffoldPadding ->
        BoxWithLayout {
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(horizontal = 12.dp)
            ) {
                FetchTodayGamesData(datePickerState = datePickerState, authUiClient = authUiClient)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchTodayGamesData(
    authUiClient: AuthUiClient,
    mainViewModel: MainViewModel = hiltViewModel(),
    datePickerState: DatePickerState = rememberDatePickerState()
) {
    val date by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
    LaunchedEffect(key1 = date) {
        if (!mainViewModel.isInitializedMain.value) {
            mainViewModel.getTodayMatches(date.toString(), getTimeZone())
            mainViewModel.isInitializedMain.value = true
        }
    }

    var itemCount by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            // fetch something
            delay(1500)
            itemCount += 5
            isRefreshing = false
        }
    }

    var selectedDate by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
    LaunchedEffect(datePickerState.selectedDateMillis) {
        if (datePickerState.selectedDateMillis != null) {
            selectedDate = Instant.ofEpochMilli(datePickerState.selectedDateMillis ?: 0)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            withContext(Dispatchers.IO) {
                mainViewModel.getTodayMatches(
                    selectedDate.toString(), getTimeZone()
                )
            }
        }
    }
    val scope = rememberCoroutineScope()
    when (val state =
        mainViewModel.todayMatchesState.collectAsState().value
    ) {
        is TodayMatchesState.Empty -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                Text(text = "Error")
            }
        }
        is TodayMatchesState.Loading -> {
            repeat(3) {
                LiveGamesShimmer()
            }
        }
        is TodayMatchesState.Error -> {
            Text(text = "Error")
        }
        is TodayMatchesState.Success -> {
            TodayMatchesLazy(
                matchesList = state.data.body()!!.response!!,
                isRefreshing = isRefreshing,
                authUiClient = authUiClient,
                onRefresh = onRefresh
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayMatchesLazy(
    matchesList: List<TodayGamesResponseItem?>,
    isRefreshing: Boolean,
    authUiClient: AuthUiClient,
    onRefresh: () -> Unit
) {
    val context = LocalContext.current
    val flushyViewModel: MainViewModel = hiltViewModel()

    val pullToRefreshState = rememberPullToRefreshState()

    val scaleFraction = {
        if (isRefreshing) 1f
        else LinearOutSlowInEasing.transform(pullToRefreshState.distanceFraction).coerceIn(0f, 1f)
    }

    val db = FirebaseFirestore.getInstance()
    val listOfFavouriteLeagues = remember { mutableStateListOf(0) }
    db.collection("favourite").document(authUiClient.getSignedInUser()?.userId!!)
        .collection("leagues").get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val result = task.result
                val documents = result.documents
                documents.forEach {
                    val data = it.data
                    val leagueId = (data!!["id"] as Long).toInt()
                    listOfFavouriteLeagues.add(leagueId)
                }
            }
        }
    matchesList.sortedBy { it!!.league!!.name }
    LaunchedEffect(key1 = null) {
        val filteredMatches = matchesList.filter { item ->
            TodayLeaguesList.contains(item?.league?.id)
        }.sortedBy { it?.fixture?.status?.jsonMemberShort }

        flushyViewModel.mainList.value = filteredMatches
            .groupBy { it!!.league!!.id }
            .map { (leagueId, matches) ->
                LeagueWithMatches(leagueId!!, matches)
            }
    }
    if (flushyViewModel.mainList.value.isNotEmpty()) {
        Box(
            modifier = Modifier
                .pullToRefresh(
                    state = pullToRefreshState,
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh
                )
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                flushyViewModel.mainList.value.forEach { (league, items) ->
                    item {
                        TodayMatchesHeader(matchItem = items.firstOrNull()!!, league = league)
                    }
                    itemsIndexed(items,
                        itemContent = { _, item ->
                            if (item != null) {
                                TodayMatchesRow(
                                    teamHomeName = item.teams?.home?.name ?: stringResource(id = R.string.notDefined),
                                    teamHomeLogo = item.teams?.home?.logo ?: "",
                                    teamHomeR = item.goals?.home ?: 0,
                                    teamAwayName = item.teams?.away?.name ?: stringResource(id = R.string.notDefined),
                                    teamAwayLogo = item.teams?.away?.logo ?: "",
                                    teamAwayR = item.goals?.away ?: 0,
                                    teamHomePen =  item.score?.penalty?.home ?: 0,
                                    teamAwayPen = item.score?.penalty?.away ?: 0,
                                    time = (item.fixture?.date ?: stringResource(id = R.string.notDefined)).substring(11, 16),
                                    elapsed = item.fixture?.status?.elapsed ?: 0,
                                    matchItem = item
                                ) {
                                    val intent = Intent(context, MatchActivity::class.java)
                                    intent.putExtra("matchId", item.fixture?.id)
                                    intent.putExtra("homeId", item.teams?.home!!.id)
                                    intent.putExtra("awayId", item.teams.away?.id)
                                    intent.putExtra("leagueId", item.league?.id)
                                    context.startActivity(intent)
                                }
                            }
                        }
                    )
                }
            }
            Box(
                Modifier.align(Alignment.TopCenter).graphicsLayer {
                    scaleX = scaleFraction()
                    scaleY = scaleFraction()
                }
            ) {
                PullToRefreshDefaults.Indicator(state = pullToRefreshState, isRefreshing = isRefreshing)
            }
        }
    }
}

@Composable
fun TodayMatchesHeader(
    matchItem: TodayGamesResponseItem, league: Int, flushyViewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    if (matchItem.league!!.id == league) {
        Card(
            modifier = Modifier.padding(vertical = 5.dp),
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
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.padding(3.dp),
                        verticalAlignment = CenterVertically
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
                    val leagueToMove = flushyViewModel.mainList.value.find { it.leagueId == league }
                    IconButton(
                        modifier = Modifier
                            .padding(3.dp)
                            .size(width = 20.dp, height = 20.dp),
                        onClick = {
                            if (leagueToMove != null) {
                                val updatedList = flushyViewModel.mainList.value.toMutableList()
                                updatedList.remove(leagueToMove)
                                updatedList.add(0, leagueToMove) // Move to the beginning
                                flushyViewModel.mainList.value = updatedList
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (leagueToMove == null) Icons.Outlined.StarRate else Icons.Filled.StarRate,
                            contentDescription = "Rate Star"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TodayMatchesRow(
    teamHomeName: String, teamHomeLogo: String, teamHomeR: Int?,
    teamAwayName: String, teamAwayLogo: String, teamAwayR: Int?,
    teamHomePen: Int?, teamAwayPen: Int?, time: String?, elapsed: Int?,
    matchItem: TodayGamesResponseItem, onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 3.dp),
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
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(IntrinsicSize.Max)
                        .padding(vertical = 10.dp, horizontal = 2.dp)
                        .weight(4f),
                    verticalAlignment = CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                modifier = Modifier.weight(2f),
                                text = teamHomeName,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .size(25.dp),
                                model = teamHomeLogo,
                                contentDescription = "Team Home"
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(2f),
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
                        "CANC", "WO", "ABD", "AWD", "PST" -> {
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
                        .weight(4f),
                    verticalAlignment = CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .weight(3f)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .size(25.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDatePicker(
    state: DatePickerState,
    onDismissRequest: () -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick = { onDismissRequest() }) {
                Text(text = "Filter")
            }
        }
    ) {
        DatePicker(
            state = state,
            showModeToggle = false,
            title = null,
            headline = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodayMatchesLazyPreview() {
    //TodayMatchesLazy()
}

@Preview(showBackground = true)
@Composable
fun TodayMatchesFlowByLeaguePreview() {
    //TodayMatchesFlowByLeague()
}

@Preview(showBackground = true)
@Composable
fun TodayMatchesRowPreview() {
    //TodayMatchesRow()
}

@Preview(showBackground = true)
@Composable
fun TodayMatchesHeaderPreview() {
    //TodayMatchesHeader()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = false)
fun LiveGamesScreenPreview() {
    //LiveGamesScreen(rememberNavController())
}