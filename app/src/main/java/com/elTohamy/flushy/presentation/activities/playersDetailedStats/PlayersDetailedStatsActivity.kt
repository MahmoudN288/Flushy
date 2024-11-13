package com.elTohamy.flushy.presentation.activities.playersDetailedStats

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import androidx.core.text.layoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Icon
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
import com.elTohamy.flushy.presentation.activities.tournament.viewModel.TournamentViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.FlushyTheme
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.presentation.ui.theme.Orange
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.presentation.uiMode.UserSettings
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.DarkTheme
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.getTournamentName
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class PlayersDetailedActivity : AppCompatActivity() {

    @Inject
    lateinit var userSettings: UserSettings

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val extras = intent.extras
            val type = extras!!.getString("top")
            val leagueName =
                when (extras.getString("league")) {
                    "topScorer" -> stringResource(id = R.string.tournamentPlayersTopScorers)
                    "topAssist" -> stringResource(id = R.string.tournamentPlayersTopAssists)
                    "topY" -> stringResource(id = R.string.tournamentPlayersTopYellow)
                    "topR" -> stringResource(id = R.string.tournamentPlayersTopRed)
                    else -> { extras.getString("league") }
                }
            val leagueLogo = extras.getString("leagueLogo")
            val leagueId = extras.getInt("leagueId")
            val flag = extras.getString("flag")
            val currentSeason = extras.getInt("season")

            val local = if (getLocale()!!.layoutDirection == LayoutDirection.Rtl.ordinal)
                LayoutDirection.Rtl
            else LayoutDirection.Ltr
            val theme = userSettings.themeStream.collectAsState()
            val useDarkColors = when (theme.value) {
                AppTheme.MODE_AUTO -> DarkTheme(isSystemInDarkTheme())
                AppTheme.MODE_DAY -> DarkTheme(false)
                AppTheme.MODE_NIGHT -> DarkTheme(true)
            }
            CompositionLocalProvider(
                LocalTheme provides useDarkColors, LocalLayoutDirection provides local
            ) {
                FlushyTheme(darkTheme = LocalTheme.current.isDark) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            topBar = {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RectangleShape,
                                    elevation = CardDefaults.cardElevation(8.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        IconButton(
                                            onClick = {
                                                finish()
                                            }
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(20.dp),
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Arrow Back",
                                                tint = MaterialTheme.customColorsPalette.blackToWhite
                                            )
                                        }
                                        AsyncImage(
                                            modifier = Modifier.size(25.dp),
                                            model = leagueLogo,
                                            contentDescription = "Logo"
                                        )
                                        Row(
                                            modifier = Modifier,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = getTournamentName(id = leagueId,
                                                    jsonName = leagueName!!
                                                ),
                                                style = TextStyle(
                                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                                    fontWeight = FontWeight.Normal,
                                                    fontSize = 14.sp
                                                ),
                                                maxLines = 1
                                            )
                                            Text(
                                                text = " - $type",
                                                style = TextStyle(
                                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                                    fontWeight = FontWeight.Normal,
                                                    fontSize = 14.sp
                                                ),
                                                maxLines = 1
                                            )
                                        }
                                        AsyncImage(
                                            modifier = Modifier.size(25.dp),
                                            model = flag,
                                            contentDescription = "Flag"
                                        )
                                    }
                                }
                            }
                        ) { paddingValues ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(paddingValues)
                            ) {
                                FetchPlayersStatsData(
                                    type = type!!,
                                    leagueId = leagueId,
                                    currentSeason = currentSeason
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@ReadOnlyComposable
fun getLocale(): Locale? {
    val configuration = LocalConfiguration.current
    return ConfigurationCompat.getLocales(configuration).get(0)
}

@Composable
fun FetchPlayersStatsData(
    tournamentViewModel: TournamentViewModel = hiltViewModel(),
    type: String, leagueId: Int, currentSeason: Int
) {
    when (type) {
        "topScorer" -> {
            LaunchedEffect(key1 = tournamentViewModel) {
                tournamentViewModel.getTopScorersByLeague(
                    league = leagueId, season = currentSeason
                )
            }
            when (val state = tournamentViewModel.topScorersState.collectAsState().value) {
                is TopScorersState.Empty -> {}
                is TopScorersState.Loading -> {}
                is TopScorersState.Error -> {}
                is TopScorersState.Success -> {
                    TopScorersItems(topScorerItems = state.data.body()!!.response!!)
                }
            }
        }
        "topAssist" -> {
            LaunchedEffect(key1 = tournamentViewModel) {
                tournamentViewModel.getTopAssistsByLeague(
                    league = leagueId, season = currentSeason
                )
            }
            when (val state = tournamentViewModel.topAssistsState.collectAsState().value) {
                is TopAssistsState.Empty -> {}
                is TopAssistsState.Loading -> {}
                is TopAssistsState.Error -> {}
                is TopAssistsState.Success -> {
                    TopAssistsItems(topAssistItems = state.data.body()!!.response!!)
                }
            }
        }
        "topY" -> {
            LaunchedEffect(key1 = tournamentViewModel) {
                tournamentViewModel.getTopYellowCardsByLeague(
                    league = leagueId, season = currentSeason
                )
            }
            when (val state = tournamentViewModel.topYellowCardsState.collectAsState().value) {
                is TopYellowCardsState.Empty -> {}
                is TopYellowCardsState.Loading -> {}
                is TopYellowCardsState.Error -> {}
                is TopYellowCardsState.Success -> {
                    TopYellowItems(topBookedYellowItems = state.data.body()!!.response!!)
                }
            }
        }
        "topR" -> {
            LaunchedEffect(key1 = tournamentViewModel) {
                tournamentViewModel.getTopRedCardsByLeague(
                    league = leagueId, season = currentSeason
                )
            }
            when (val state = tournamentViewModel.topRedCardsState.collectAsState().value) {
                is TopRedCardsState.Empty -> {}
                is TopRedCardsState.Loading -> {}
                is TopRedCardsState.Error -> {}
                is TopRedCardsState.Success -> {
                    TopRedItems(topBookedRedItems = state.data.body()!!.response!!)
                }
            }
        }
    }
}

@Composable
fun TopScorersItems(topScorerItems: List<TopScorersResponseItem?>) {
    LazyColumn {
        itemsIndexed(
            topScorerItems,
            itemContent = {index, scorerItem->
                PlayerTopScorerItem(id = index + 1, topScorer = scorerItem)
            }
        )
    }
}

@Composable
fun TopAssistsItems(topAssistItems: List<TopAssistsResponseItem?>) {
    LazyColumn {
        itemsIndexed(
            topAssistItems,
            itemContent = {index, assistItem->
                PlayerTopAssistsItem(id = index + 1, topAssists = assistItem)
            }
        )
    }
}

@Composable
fun TopYellowItems(topBookedYellowItems: List<TopYellowResponseItem?>) {
    LazyColumn {
        itemsIndexed(
            topBookedYellowItems,
            itemContent = {index, bookedYItem->
                PlayerTopYellowItem(id = index + 1, topBookedYellow = bookedYItem)
            }
        )
    }
}

@Composable
fun TopRedItems(topBookedRedItems: List<TopRedResponseItem?>) {
    LazyColumn {
        itemsIndexed(
            topBookedRedItems,
            itemContent = {index, bookedRItem->
                PlayerTopRedItem(id = index + 1, topBookedRed = bookedRItem)
            }
        )
    }
}

@Composable
fun PlayerTopScorerItem(id: Int, topScorer: TopScorersResponseItem?) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("playerId", topScorer!!.player!!.id)
            intent.putExtra("currentSeason", topScorer.statistics!![0]!!.league!!.season)
            context.startActivity(intent)
        }
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(10f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = id.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            Card(
                                modifier = Modifier.weight(2f),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(30.dp),
                                    model = topScorer!!.player!!.photo,
                                    contentDescription = "Player"
                                )
                            }
                            Text(
                                modifier = Modifier.weight(5f),
                                text = topScorer!!.player!!.name!!,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .weight(2f)
                                    .size(30.dp),
                                model = topScorer.statistics?.get(0)!!.team!!.logo,
                                contentDescription = "Club"
                            )
                        }
                    }
                }
                val rating =
                    if (topScorer!!.statistics?.get(0)!!.games!!.rating!!.toDouble() == 10.0) {
                        topScorer.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 4)
                    } else {
                        topScorer.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 3)
                    }
                Row(
                    modifier = Modifier.width(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(2f).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = topScorer.statistics[0]!!.goals!!.total.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            val penalties = topScorer.statistics[0]!!.penalty!!.scored.toString()
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "(Pen: $penalties)",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            Card(
                                modifier = Modifier.weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                    if (rating.toDouble() in 0.0..3.9)
                                        Color.Red
                                    else if (rating.toDouble() in 4.0..5.9)
                                        LightRed
                                    else if (rating.toDouble() in 6.0..6.9)
                                        Orange
                                    else if (rating.toDouble() in 7.0..7.9)
                                        LightGreen
                                    else if (rating.toDouble() in 8.0..10.0)
                                        Color.Green
                                    else Color.White
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = if (rating.toDouble() == 10.0) rating.substring(0, 3) else rating.substring(0, 2),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
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

@Composable
fun PlayerTopAssistsItem(id: Int, topAssists: TopAssistsResponseItem?) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("playerId", topAssists!!.player!!.id)
            intent.putExtra("currentSeason", topAssists.statistics!![0]!!.league!!.season)
            context.startActivity(intent)
        }
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(10f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = id.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            Card(
                                modifier = Modifier.weight(2f),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(30.dp),
                                    model = topAssists!!.player!!.photo,
                                    contentDescription = "Player"
                                )
                            }
                            Text(
                                modifier = Modifier.weight(5f),
                                text = topAssists!!.player!!.name!!,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .weight(2f)
                                    .size(30.dp),
                                model = topAssists.statistics?.get(0)!!.team!!.logo,
                                contentDescription = "Club"
                            )
                        }
                    }
                }
                val rating =
                    if (topAssists!!.statistics?.get(0)!!.games!!.rating!!.toDouble() == 10.0) {
                        topAssists.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 4)
                    } else {
                        topAssists.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 3)
                    }
                Row(
                    modifier = Modifier.width(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(2f).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = topAssists.statistics[0]!!.goals!!.total.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            val penalties = topAssists.statistics[0]!!.penalty!!.scored.toString()
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "(Pen: $penalties)",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            Card(
                                modifier = Modifier.weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                    if (rating.toDouble() in 0.0..3.9)
                                        Color.Red
                                    else if (rating.toDouble() in 4.0..5.9)
                                        LightRed
                                    else if (rating.toDouble() in 6.0..6.9)
                                        Orange
                                    else if (rating.toDouble() in 7.0..7.9)
                                        LightGreen
                                    else if (rating.toDouble() in 8.0..10.0)
                                        Color.Green
                                    else Color.White
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = if (rating.toDouble() == 10.0) rating.substring(0, 3) else rating.substring(0, 2),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
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

@Composable
fun PlayerTopYellowItem(id: Int, topBookedYellow: TopYellowResponseItem?) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("playerId", topBookedYellow!!.player!!.id)
            intent.putExtra("currentSeason", topBookedYellow.statistics!![0]!!.league!!.season)
            context.startActivity(intent)
        }
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(10f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = id.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            Card(
                                modifier = Modifier.weight(2f),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(30.dp),
                                    model = topBookedYellow!!.player!!.photo,
                                    contentDescription = "Player"
                                )
                            }
                            Text(
                                modifier = Modifier.weight(5f),
                                text = topBookedYellow!!.player!!.name!!,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .weight(2f)
                                    .size(30.dp),
                                model = topBookedYellow.statistics?.get(0)!!.team!!.logo,
                                contentDescription = "Club"
                            )
                        }
                    }
                }
                val rating =
                    if (topBookedYellow!!.statistics?.get(0)!!.games!!.rating!!.toDouble() == 10.0) {
                        topBookedYellow.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 4)
                    } else {
                        topBookedYellow.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 3)
                    }
                Row(
                    modifier = Modifier.width(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(2f).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = topBookedYellow.statistics[0]!!.goals!!.total.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            val penalties = topBookedYellow.statistics[0]!!.penalty!!.scored.toString()
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "(Pen: $penalties)",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            Card(
                                modifier = Modifier.weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                    if (rating.toDouble() in 0.0..3.9)
                                        Color.Red
                                    else if (rating.toDouble() in 4.0..5.9)
                                        LightRed
                                    else if (rating.toDouble() in 6.0..6.9)
                                        Orange
                                    else if (rating.toDouble() in 7.0..7.9)
                                        LightGreen
                                    else if (rating.toDouble() in 8.0..10.0)
                                        Color.Green
                                    else Color.White
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = rating,
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
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

@Composable
fun PlayerTopRedItem(id: Int, topBookedRed: TopRedResponseItem?) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("playerId", topBookedRed!!.player!!.id)
            intent.putExtra("currentSeason", topBookedRed.statistics!![0]!!.league!!.season)
            context.startActivity(intent)
        }
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(10f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = id.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            Card(
                                modifier = Modifier.weight(2f),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(30.dp),
                                    model = topBookedRed!!.player!!.photo,
                                    contentDescription = "Player"
                                )
                            }
                            Text(
                                modifier = Modifier.weight(5f),
                                text = topBookedRed!!.player!!.name!!,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .weight(2f)
                                    .size(30.dp),
                                model = topBookedRed.statistics?.get(0)!!.team!!.logo,
                                contentDescription = "Club"
                            )
                        }
                    }
                }
                val rating =
                    if (topBookedRed!!.statistics?.get(0)!!.games!!.rating!!.toDouble() == 10.0) {
                        topBookedRed.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 4)
                    } else {
                        topBookedRed.statistics?.get(0)!!
                            .games!!.rating!!.substring(0, 3)
                    }
                Row(
                    modifier = Modifier.width(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier.weight(2f).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = topBookedRed.statistics[0]!!.goals!!.total.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            val penalties = topBookedRed.statistics[0]!!.penalty!!.scored.toString()
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "(Pen: $penalties)",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )
                            Card(
                                modifier = Modifier.weight(1f)
                                    .padding(horizontal = 5.dp)
                                    .clip(RoundedCornerShape(3.dp)),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                    if (rating.toDouble() in 0.0..3.9)
                                        Color.Red
                                    else if (rating.toDouble() in 4.0..5.9)
                                        LightRed
                                    else if (rating.toDouble() in 6.0..6.9)
                                        Orange
                                    else if (rating.toDouble() in 7.0..7.9)
                                        LightGreen
                                    else if (rating.toDouble() in 8.0..10.0)
                                        Color.Green
                                    else Color.White
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 8.dp),
                                    text = rating,
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
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