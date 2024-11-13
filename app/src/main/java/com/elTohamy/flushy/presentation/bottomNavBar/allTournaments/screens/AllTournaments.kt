package com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material3.Icon
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.leagues.LeaguesResponseItem
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.presentation.activities.tournament.TournamentActivity
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.viewModel.LeaguesViewModel
import com.elTohamy.flushy.presentation.bottomNavBar.liveGamesScreen.components.TodayLeaguesList
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.search.SearchBar
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.getTournamentName
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


@Composable
fun AllTournaments(leagues: List<LeaguesResponseItem?>, mainViewModel: MainViewModel = hiltViewModel()) {
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = mainViewModel.lazyListStateAll.value)
    val scope = rememberCoroutineScope()
    mainViewModel.updateLazyListStateAll(remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value)
    Scaffold(
        topBar = {},
        floatingActionButton = {
            if (remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value != 0) {
                FloatingActionButton(
                    shape = FloatingActionButtonDefaults.smallShape,
                    containerColor = MaterialTheme.colorScheme.primary,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TournamentLazy(leagues = leagues, lazyListState)
        }
    }
}

@Composable
fun TournamentLazy(
    leagues: List<LeaguesResponseItem?>, lazyListState: LazyListState,
    leaguesViewModel: LeaguesViewModel = hiltViewModel()
) {
    val dark = LocalTheme.current.isDark
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val flushyAuthUiClient by lazy {
        AuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    LaunchedEffect(key1 = null) {
        scope.launch {
            leagues.filter { item ->
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
            }
        }
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
                    .systemBarsPadding()
                    .padding(8.dp),
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
                    .padding(10.dp),
                text = stringResource(id = R.string.allTournaments),
                style = TextStyle(
                    color = if (dark) Color.LightGray else Color.Gray,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                ),
                maxLines = 1,
                textAlign = TextAlign.Start
            )
        }
        itemsIndexed(
            leagues.filter {
                it!!.league!!.name!!.contains(searchedText, ignoreCase = true)
            }
        ) { index, item ->
            val isFavourite: SnapshotStateList<Boolean> = remember(leagues) {
                leagues.map { false }.toMutableStateList()
            }
            val db = FirebaseFirestore.getInstance()
            db.collection("favourite").document(flushyAuthUiClient.getSignedInUser()?.userId!!)
                .collection("leagues").document(item!!.league!!.id.toString()).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        isFavourite[index] = document.exists()
                    }
                }
            val favourite = isFavourite[index]
            TournamentItem(
                leaguesResponseItem = item,
                isFavourite = isFavourite[index]
            ) {
                scope.launch {
                    db.collection("favourite").document(flushyAuthUiClient.getSignedInUser()?.userId!!)
                        .collection("leagues").document(item.league!!.id.toString())
                        .get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (document.exists()) {
                                    leaguesViewModel.removeFavouriteLeague(
                                        id = item.league.id!!, userId = flushyAuthUiClient.getSignedInUser()?.userId!!
                                    )
                                    isFavourite[index] = !favourite
                                } else {
                                    leaguesViewModel.addFavouriteLeague(
                                        id = item.league.id!!, name = item.league.name!!,
                                        logo = item.league.logo!!, season = item.seasons!![item.seasons.size - 1]!!.year!!,
                                        country = item.country!!.name!!, userId = flushyAuthUiClient.getSignedInUser()?.userId!!
                                    )
                                    isFavourite[index] = !favourite
                                }
                            }
                        }
                }
            }
        }
    }
}

@Composable
fun TournamentItem(
    leaguesResponseItem: LeaguesResponseItem,
    isFavourite: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val currentSeasonList = leaguesResponseItem.seasons!!.filter {
        it!!.current!!
    }
    val currentSeason = currentSeasonList[0]!!.year
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, TournamentActivity::class.java)
            intent.putExtra("leagueId", leaguesResponseItem.league!!.id)
            intent.putExtra("currentSeason", currentSeason)
            intent.putExtra("leagueCountry", leaguesResponseItem.country!!.name)
            intent.putExtra("leagueName", leaguesResponseItem.league.name)
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.size(width = 50.dp, height = 50.dp),
                    model = leaguesResponseItem.league!!.logo,
                    contentDescription = "Tournament Logo"
                )
                Text(
                    modifier = Modifier,
                    text = getTournamentName(
                        id = leaguesResponseItem.league.id!!,
                        jsonName = leaguesResponseItem.league.name!!
                    ),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    maxLines = 1
                )
            }
            IconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    modifier = Modifier.size(width = 25.dp, height = 25.dp),
                    imageVector = if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favourite Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}