package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.screens

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponseItem
import com.elTohamy.flushy.data.remote.dto.fixtures.lineups.LineupsResponseItem
import com.elTohamy.flushy.data.remote.dto.fixtures.players.PlayersFixtureStatsResponseItem
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.PlayerOnBenchComponent
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.PlayerOnPitchComponent
import com.elTohamy.flushy.presentation.activities.players.PlayerActivity
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun AwayTeam(
    lineupsResponseItemList: List<LineupsResponseItem?>,
    playersStatsList: List<PlayersFixtureStatsResponseItem?>,
    matchByIdResponseItem: MatchByIdResponseItem
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AwayLineup(
            lineupsResponseItem = lineupsResponseItemList,
            playersStatsList = playersStatsList,
            matchByIdResponseItem = matchByIdResponseItem
        )
        AwayBench(
            lineupsResponseItemList = lineupsResponseItemList,
            playersStatsList = playersStatsList,
            matchByIdResponseItem = matchByIdResponseItem
        )
    }
}

@Composable
fun AwayBench(
    lineupsResponseItemList: List<LineupsResponseItem?>,
    playersStatsList: List<PlayersFixtureStatsResponseItem?>,
    matchByIdResponseItem: MatchByIdResponseItem
) {
    val dark = LocalTheme.current.isDark
    val context = LocalContext.current
    val managerHomeName = lineupsResponseItemList[1]!!.coach!!.name
    val managerHomePhoto = lineupsResponseItemList[1]!!.coach!!.photo
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.bench),
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.manager),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier,
                        onClick = {},
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier,
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(width = 72.dp, height = 72.dp),
                                    model = managerHomePhoto,
                                    contentDescription = "",
                                    placeholder = painterResource(id = R.drawable.player_f)
                                )
                            }
                            Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text = managerHomeName ?: stringResource(id = R.string.notDefined),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(if (dark) Color.LightGray else Color.Gray)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = stringResource(id = R.string.subs),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.5f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
                lineupsResponseItemList[1]!!.substitutes!!
                    .forEach { subItem->
                        playersStatsList[1]!!.players!!
                            .forEach { playerItem->
                                if (
                                    playerItem!!.player!!.id == subItem!!.player!!.id
                                ) {
                                    val playerImage = playerItem.player!!.photo
                                    val playerName = playerItem.player.name ?: stringResource(id = R.string.noName)
                                    val playerNumber = subItem.player!!.number ?: 0
                                    playerItem.statistics!!.forEach { playerStatsItem->
                                        val goals by remember {
                                            mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                        }
                                        val assists by remember {
                                            mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                        }
                                        val yellowCards by remember {
                                            mutableIntStateOf(playerStatsItem!!.cards!!.yellow!!)
                                        }
                                        val redCards by remember {
                                            mutableIntStateOf(playerStatsItem!!.cards!!.red!!)
                                        }
                                        val rating by remember { mutableStateOf(playerStatsItem?.games?.rating ?: "0.0") }
                                        val minutes by remember {
                                            mutableIntStateOf(
                                                if (playerStatsItem?.games?.minutes != null) {
                                                    playerStatsItem.games.minutes
                                                } else 0
                                            )
                                        }
                                        val hasSubbed by remember {
                                            mutableStateOf((playerStatsItem?.games?.minutes ?: 0) > 0)
                                        }
                                        val time by remember {
                                            mutableIntStateOf(
                                                (matchByIdResponseItem.fixture?.status?.elapsed ?: 0) - minutes
                                            )
                                        }
                                        Row {
                                            PlayerOnBenchComponent(
                                                playerImage = playerImage!!,
                                                name = playerName,
                                                number = playerNumber,
                                                goals = goals,
                                                assists = assists,
                                                playerRating = rating,
                                                yellowCards = yellowCards,
                                                redCards = redCards,
                                                hasSubbed = hasSubbed,
                                                hasInvolved = minutes > 0,
                                                isCaptain = playerStatsItem!!.games!!.captain!!,
                                                time = time
                                            ) {
                                                val intent = Intent(context, PlayerActivity::class.java)
                                                intent.putExtra("playerId", subItem.player.id)
                                                intent.putExtra("currentSeason", matchByIdResponseItem.league!!.season)
                                                context.startActivity(intent)
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
fun AwayLineup(
    lineupsResponseItem: List<LineupsResponseItem?>,
    playersStatsList: List<PlayersFixtureStatsResponseItem?>,
    matchByIdResponseItem: MatchByIdResponseItem?
) {
    val context = LocalContext.current
    val pitchLightSection = Color(color = 0xFF02AC6C) //0xFF268807
    val pitchDarkSection = Color(0xFF02AC6C) //0xFF136D15
    val pitchDarkAll = Color.Black.copy(0.8f)
    val dark = LocalTheme.current.isDark
    //val painterCamera = painterResource(id = R.drawable.camera_w)
    //val painterVar = painterResource(id = R.drawable.var_w)
    val configuration = LocalConfiguration.current
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(
                width =
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 800.dp else 400.dp,
                height = 500.dp
            )
            .drawBehind {
                //Outside
                drawRect(
                    color = if (dark) pitchDarkAll
                    else pitchDarkSection.copy(0.7f)
                )
                //Main Rectangle Lines
                drawRect(
                    color = if (dark) Color.DarkGray else Color.White,
                    style = Stroke(width = 7f, cap = StrokeCap.Round),
                    size = Size(width = size.width, height = size.height / 1.1f),
                    topLeft = Offset(x = 0f, y = center.y / 11)
                )
                drawRect(
                    color = if (dark) Color.Gray else pitchLightSection
                )

                //Outside Pitch Container
                drawRect(
                    color = if (dark) Color.DarkGray else Color.White,
                    style = Stroke(width = 10f),
                    size = Size(
                        width = size.width,
                        height = size.height
                    ),
                    topLeft = Offset(x = 0f, y = 0f)
                )

                //Center Field
                drawLine(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    start = Offset(x = 0f, y = center.y),
                    end = Offset(x = size.width, y = center.y),
                    strokeWidth = 10f
                )

                drawCircle(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    radius = 10f,
                    center = Offset(size.width / 2, size.height / 2)
                )

                drawCircle(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    radius = 200f,
                    center = Offset(size.width / 2, size.height / 2),
                    style = Stroke(10f)
                )
                //Corner Home Left
                drawArc(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    startAngle = 0f,
                    sweepAngle = 90f,
                    useCenter = false,
                    size = Size(100f, 100f),
                    topLeft = Offset(-50f, -50f),
                    style = Stroke(3.dp.toPx())
                )

                drawArc(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    startAngle = 90f,
                    sweepAngle = 90f,
                    useCenter = false,
                    size = Size(100f, 100f),
                    topLeft = Offset(size.width - 50f, -50f),
                    style = Stroke(3.dp.toPx())
                )

                drawArc(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    startAngle = 180f,
                    sweepAngle = 90f,
                    useCenter = false,
                    size = Size(100f, 100f),
                    topLeft = Offset(
                        size.width - 50f, size.height - 50f
                    ),
                    style = Stroke(3.dp.toPx())
                )

                drawArc(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    startAngle = 270f,
                    sweepAngle = 90f,
                    useCenter = false,
                    size = Size(100f, 100f),
                    topLeft = Offset(-50f, size.height - 50f),
                    style = Stroke(3.dp.toPx())
                )

                drawRect(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    topLeft = Offset(
                        x = size.width.div(2) - 100f,
                        y = 0f
                    ),
                    size = Size(200f, 100f),
                    style = Stroke(10f)
                )

                drawRect(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    topLeft = Offset(
                        x = size.width.div(2) - 250f,
                        y = 0f
                    ),
                    size = Size(500f, 250f),
                    style = Stroke(10f)
                )

                drawRect(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    topLeft = Offset(
                        size.width.div(2) - 100f,
                        size.height - 100f
                    ), // Adjusted to ensure positive size
                    size = Size(200f, 100f),
                    style = Stroke(10f)
                )

                drawRect(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    topLeft = Offset(
                        size.width.div(2) - 250f,
                        size.height - 250f
                    ), // Adjusted to ensure positive size
                    size = Size(500f, 250f),
                    style = Stroke(10f)
                )
                drawCircle(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    radius = 10f,
                    center = Offset(size.width.div(2), 200f)
                )

                drawCircle(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    radius = 10f,
                    center = Offset(size.width.div(2), size.height - 200f)
                )

                drawArc(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    startAngle = 0f,
                    sweepAngle = 180f,
                    useCenter = false,
                    size = Size(200f, 100f),
                    topLeft = Offset(
                        size.width
                            .div(2) - 100f, 200f
                    ),
                    style = Stroke(3.dp.toPx())
                )

                drawArc(
                    color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    size = Size(200f, 100f),
                    topLeft = Offset(
                        x = size.width.div(2) - 100f,
                        y = size.height.minus(300f)
                    ),
                    style = Stroke(3.dp.toPx())
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                lineupsResponseItem[1]!!.startXI?.forEach { startingPlayersList ->
                    if ((startingPlayersList!!.player!!.grid as? String)!!.substring(0, 1) == "1") {
                        playersStatsList[1]!!.players!!.forEach { matchPlayerItem ->
                            if (matchPlayerItem!!.player!!.id == startingPlayersList.player!!.id) {
                                val image = matchPlayerItem.player!!.photo
                                val name = startingPlayersList.player.name
                                val number = startingPlayersList.player.number
                                matchPlayerItem.statistics!!.forEach { playerStatsItem ->
                                    val rating by remember { mutableStateOf(playerStatsItem!!.games!!.rating) }
                                    val goals by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                    }
                                    val assists by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                    }
                                    val yellowCards by remember {
                                        mutableIntStateOf((playerStatsItem?.cards!!.yellow!!))
                                    }
                                    val redCards by remember { mutableIntStateOf((playerStatsItem!!.cards!!.red!!)) }
                                    PlayerOnPitchComponent(
                                        playerImage = image!!,
                                        name = name!!,
                                        number = number!!,
                                        goals = goals,
                                        assists = assists,
                                        playerRating = rating!!,
                                        yellowCards = yellowCards,
                                        redCards = redCards,
                                        hasSubbed = false,
                                        hasInvolved = playerStatsItem!!.games!!.minutes!! > 0,
                                        isCaptain = playerStatsItem.games!!.captain!!,
                                        isGoalKeeper = true
                                    ) {
                                        val intent = Intent(context, PlayerActivity::class.java)
                                        intent.putExtra("playerId", matchPlayerItem.player.id)
                                        intent.putExtra("currentSeason", matchByIdResponseItem!!.league!!.season)
                                        context.startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                lineupsResponseItem[1]!!.startXI?.forEach { startingPlayersList ->
                    if ((startingPlayersList!!.player!!.grid as? String)!!.substring(0, 1) == "2") {
                        playersStatsList[1]!!.players!!.forEach { matchPlayerItem ->
                            if (matchPlayerItem!!.player!!.id == startingPlayersList.player!!.id) {
                                val image = matchPlayerItem.player!!.photo
                                val name = startingPlayersList.player.name
                                val number = startingPlayersList.player.number
                                matchPlayerItem.statistics!!.forEach { playerStatsItem ->
                                    val rating by remember { mutableStateOf(playerStatsItem!!.games!!.rating) }
                                    val goals by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                    }
                                    val assists by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                    }
                                    val yellowCards by remember {
                                        mutableIntStateOf((playerStatsItem?.cards!!.yellow!!))
                                    }
                                    val redCards by remember { mutableIntStateOf((playerStatsItem!!.cards!!.red!!)) }
                                    PlayerOnPitchComponent(
                                        playerImage = image!!,
                                        name = name!!,
                                        number = number!!,
                                        goals = goals,
                                        assists = assists,
                                        playerRating = rating!!,
                                        yellowCards = yellowCards,
                                        redCards = redCards,
                                        hasSubbed = false,
                                        hasInvolved = playerStatsItem!!.games!!.minutes!! > 0,
                                        isCaptain = playerStatsItem.games!!.captain!!,
                                        isGoalKeeper = false
                                    ) {
                                        val intent = Intent(context, PlayerActivity::class.java)
                                        intent.putExtra("playerId", matchPlayerItem.player.id)
                                        intent.putExtra("currentSeason", matchByIdResponseItem!!.league!!.season)
                                        context.startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement =
                if (
                    lineupsResponseItem[1]!!.formation == "4-4-2" || lineupsResponseItem[1]!!.formation == "4-3-3" ||
                    lineupsResponseItem[1]!!.formation == "3-5-2" || lineupsResponseItem[1]!!.formation == "5-3-2" ||
                    lineupsResponseItem[1]!!.formation == "3-4-3" || lineupsResponseItem[1]!!.formation == "4-4-1-1" ||
                    lineupsResponseItem[1]!!.formation == "4-3-2-1" || lineupsResponseItem[1]!!.formation == "4-5-1" ||
                    lineupsResponseItem[1]!!.formation == "5-4-1"
                ) Arrangement.SpaceBetween else Arrangement.Center
            ) {
                lineupsResponseItem[1]!!.startXI?.forEach { startingPlayersList ->
                    if ((startingPlayersList!!.player!!.grid as? String)!!.substring(0, 1) == "3") {
                        playersStatsList[1]!!.players!!.forEach { matchPlayerItem ->
                            if (matchPlayerItem!!.player!!.id == startingPlayersList.player!!.id) {
                                val image = matchPlayerItem.player!!.photo
                                val name = startingPlayersList.player.name
                                val number = startingPlayersList.player.number
                                matchPlayerItem.statistics!!.forEach { playerStatsItem ->
                                    val rating by remember { mutableStateOf(playerStatsItem!!.games!!.rating) }
                                    val goals by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                    }
                                    val assists by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                    }
                                    val yellowCards by remember {
                                        mutableIntStateOf((playerStatsItem?.cards!!.yellow!!))
                                    }
                                    val redCards by remember { mutableIntStateOf((playerStatsItem!!.cards!!.red!!)) }
                                    PlayerOnPitchComponent(
                                        playerImage = image!!,
                                        name = name!!,
                                        number = number!!,
                                        goals = goals,
                                        assists = assists,
                                        playerRating = rating!!,
                                        yellowCards = yellowCards,
                                        redCards = redCards,
                                        hasSubbed = false,
                                        hasInvolved = playerStatsItem!!.games!!.minutes!! > 0,
                                        isCaptain = playerStatsItem.games!!.captain!!,
                                        isGoalKeeper = false
                                    ) {
                                        val intent = Intent(context, PlayerActivity::class.java)
                                        intent.putExtra("playerId", matchPlayerItem.player.id)
                                        intent.putExtra("currentSeason", matchByIdResponseItem!!.league!!.season)
                                        context.startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement =
                if (
                    lineupsResponseItem[1]!!.formation == "4-1-4-1" || lineupsResponseItem[1]!!.formation == "4-3-3" ||
                    lineupsResponseItem[1]!!.formation == "4-2-4" || lineupsResponseItem[1]!!.formation == "3-4-3"
                ) Arrangement.SpaceBetween else Arrangement.Center
            ) {
                lineupsResponseItem[1]!!.startXI?.forEach { startingPlayersList ->
                    if ((startingPlayersList!!.player!!.grid as? String)!!.substring(0, 1) == "4") {
                        playersStatsList[1]!!.players!!.forEach { matchPlayerItem ->
                            if (matchPlayerItem!!.player!!.id == startingPlayersList.player!!.id) {
                                val image = matchPlayerItem.player!!.photo
                                val name = startingPlayersList.player.name
                                val number = startingPlayersList.player.number
                                matchPlayerItem.statistics!!.forEach { playerStatsItem ->
                                    val rating by remember { mutableStateOf(playerStatsItem!!.games!!.rating) }
                                    val goals by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                    }
                                    val assists by remember {
                                        mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                    }
                                    val yellowCards by remember {
                                        mutableIntStateOf((playerStatsItem?.cards!!.yellow!!))
                                    }
                                    val redCards by remember { mutableIntStateOf((playerStatsItem!!.cards!!.red!!)) }
                                    PlayerOnPitchComponent(
                                        playerImage = image!!,
                                        name = name!!,
                                        number = number!!,
                                        goals = goals,
                                        assists = assists,
                                        playerRating = rating!!,
                                        yellowCards = yellowCards,
                                        redCards = redCards,
                                        hasSubbed = false,
                                        hasInvolved = playerStatsItem!!.games!!.minutes!! > 0,
                                        isCaptain = playerStatsItem.games!!.captain!!,
                                        isGoalKeeper = false
                                    ) {
                                        val intent = Intent(context, PlayerActivity::class.java)
                                        intent.putExtra("playerId", matchPlayerItem.player.id)
                                        intent.putExtra("currentSeason", matchByIdResponseItem!!.league!!.season)
                                        context.startActivity(intent)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (lineupsResponseItem[1]!!.formation!!.length >= 7) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 1.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    lineupsResponseItem[1]!!.startXI?.forEach { startingPlayersList ->
                        if ((startingPlayersList!!.player!!.grid as? String)!!.substring(0, 1) == "5") {
                            playersStatsList[1]!!.players!!.forEach { matchPlayerItem ->
                                if (matchPlayerItem!!.player!!.id == startingPlayersList.player!!.id) {
                                    val image = matchPlayerItem.player!!.photo
                                    val name = startingPlayersList.player.name
                                    val number = startingPlayersList.player.number
                                    matchPlayerItem.statistics!!.forEach { playerStatsItem ->
                                        val rating by remember { mutableStateOf(playerStatsItem!!.games!!.rating) }
                                        val goals by remember {
                                            mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                        }
                                        val assists by remember {
                                            mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                        }
                                        val yellowCards by remember {
                                            mutableIntStateOf((playerStatsItem?.cards!!.yellow!!))
                                        }
                                        val redCards by remember { mutableIntStateOf((playerStatsItem!!.cards!!.red!!)) }
                                        PlayerOnPitchComponent(
                                            playerImage = image!!,
                                            name = name!!,
                                            number = number!!,
                                            goals = goals,
                                            assists = assists,
                                            playerRating = rating!!,
                                            yellowCards = yellowCards,
                                            redCards = redCards,
                                            hasSubbed = false,
                                            hasInvolved = playerStatsItem!!.games!!.minutes!! > 0,
                                            isCaptain = playerStatsItem.games!!.captain!!,
                                            isGoalKeeper = false
                                        ) {
                                            val intent = Intent(context, PlayerActivity::class.java)
                                            intent.putExtra("playerId", matchPlayerItem.player.id)
                                            intent.putExtra("currentSeason", matchByIdResponseItem!!.league!!.season)
                                            context.startActivity(intent)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (lineupsResponseItem[1]!!.formation!!.length >= 9) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 1.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    lineupsResponseItem[1]!!.startXI?.forEach { startingPlayersList ->
                        if ((startingPlayersList!!.player!!.grid as? String)!!.substring(0, 1) == "6") {
                            playersStatsList[1]!!.players!!.forEach { matchPlayerItem ->
                                if (matchPlayerItem!!.player!!.id == startingPlayersList.player!!.id) {
                                    val image = matchPlayerItem.player!!.photo
                                    val name = startingPlayersList.player.name
                                    val number = startingPlayersList.player.number
                                    matchPlayerItem.statistics!!.forEach { playerStatsItem ->
                                        val rating by remember { mutableStateOf(playerStatsItem!!.games!!.rating) }
                                        val goals by remember {
                                            mutableIntStateOf(playerStatsItem?.goals?.total ?: 0)
                                        }
                                        val assists by remember {
                                            mutableIntStateOf(playerStatsItem?.goals?.assists ?: 0)
                                        }
                                        val yellowCards by remember {
                                            mutableIntStateOf((playerStatsItem?.cards!!.yellow!!))
                                        }
                                        val redCards by remember { mutableIntStateOf((playerStatsItem!!.cards!!.red!!)) }
                                        PlayerOnPitchComponent(
                                            playerImage = image!!,
                                            name = name!!,
                                            number = number!!,
                                            goals = goals,
                                            assists = assists,
                                            playerRating = rating!!,
                                            yellowCards = yellowCards,
                                            redCards = redCards,
                                            hasSubbed = false,
                                            hasInvolved = playerStatsItem!!.games!!.minutes!! > 0,
                                            isCaptain = playerStatsItem.games!!.captain!!,
                                            isGoalKeeper = false
                                        ) {
                                            val intent = Intent(context, PlayerActivity::class.java)
                                            intent.putExtra("playerId", matchPlayerItem.player.id)
                                            intent.putExtra("currentSeason", matchByIdResponseItem!!.league!!.season)
                                            context.startActivity(intent)
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
}