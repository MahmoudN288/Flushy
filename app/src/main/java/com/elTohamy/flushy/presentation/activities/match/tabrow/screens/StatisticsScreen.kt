package com.elTohamy.flushy.presentation.activities.match.tabrow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.TeamStatisticsItem
import com.elTohamy.flushy.presentation.animations.possession.PossessionAnimation
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.data.state.MatchInfoState
import com.elTohamy.flushy.presentation.activities.match.viewModel.MatchViewModel
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun MatchTeamsStatisticsScreens() {
    FetchMatchStats()
}

@Composable
fun FetchMatchStats(matchViewModel: MatchViewModel = hiltViewModel()) {
    Column {
        when (val state = matchViewModel.matchInfoState.collectAsState().value) {
            is MatchInfoState.Empty -> {}
            is MatchInfoState.Loading -> {}
            is MatchInfoState.Error -> {}
            is MatchInfoState.Success -> {
                TeamsStatsVerticalColumn(
                    statisticsItemList = state.data.body()!!.response?.get(0)?.statistics!!
                )
            }
        }
    }
}

@Composable
fun TeamsStatsVerticalColumn(statisticsItemList: List<TeamStatisticsItem?>) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = stringResource(id = R.string.statistics),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
        }
        GeneralStatistics(statisticsItemList = statisticsItemList)
        ShootingStatistics(statisticsItemList = statisticsItemList)
        DisciplineStatistics(statisticsItemList = statisticsItemList)
    }
}

@Composable
fun GeneralStatistics(statisticsItemList: List<TeamStatisticsItem?>) {
    val dark = LocalTheme.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.gStatistics),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(width = 20.dp, height = 20.dp),
                    painter = if (dark.isDark)
                        painterResource(id = R.drawable.statistics_white)
                    else painterResource(id = R.drawable.statistics_black),
                    contentDescription = "Shoot Icon"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        text = stringResource(id = R.string.possession),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    statisticsItemList[0]!!.statistics!!.forEach {
                        if (it!!.type == "Ball Possession") {
                            if (it.value != null) {
                                val homePossession = it.value.toString()
                                PossessionAnimation(receivedValue = homePossession)
                            } else {
                                Text(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(10.dp),
                                    text = stringResource(id = R.string.notDefined),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp
                                    ),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var expectedHomeText: String? = "0"
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "expected_goals") {
                                expectedHomeText = (it.value ?: 0).toString()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = expectedHomeText!!,
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.exG),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var expectedAwayText: String? = "0"
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "expected_goals") {
                                expectedAwayText = it.value.toString()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = expectedAwayText!!,
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var shotsOnGoalHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Shots on Goal") {
                                shotsOnGoalHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = shotsOnGoalHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.shotsOn),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var shotsOnGoalAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Shots on Goal") {
                                shotsOnGoalAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = shotsOnGoalAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var totalPassesHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Total passes") {
                                totalPassesHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = totalPassesHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.totalPasses),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var totalPassesAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Total passes") {
                                totalPassesAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = totalPassesAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var accuratePassesHomeText: Int? = 0
                        var percentagePassesHomeText: String? = "0%"
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Passes accurate") {
                                accuratePassesHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                            if (it.type == "Passes %") {
                                percentagePassesHomeText = ((it.value ?: "0%").toString())
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = "${accuratePassesHomeText!!} (${percentagePassesHomeText!!})",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.accPasses),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var accuratePassesAwayText: Int? = 0
                        var percentagePassesAwayText: String? = "0%"
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Passes accurate") {
                                accuratePassesAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                            if (it.type == "Passes %") {
                                percentagePassesAwayText = ((it.value ?: "0%").toString())
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = "${accuratePassesAwayText!!} (${percentagePassesAwayText!!})",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var foulsHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Fouls") {
                                foulsHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = foulsHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.foulsCom),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var foulsAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Fouls") {
                                foulsAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = foulsAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var offsidesHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Offsides") {
                                offsidesHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = offsidesHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.offsides),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var offsidesAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Offsides") {
                                offsidesAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = offsidesAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var cornersHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Corner Kicks") {
                                cornersHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = cornersHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.cornerKicks),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var cornersAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Corner Kicks") {
                                cornersAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = cornersAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var goalkeeperSavesHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Goalkeeper Saves") {
                                goalkeeperSavesHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = goalkeeperSavesHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.keeperSaves),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var goalkeeperSavesAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Goalkeeper Saves") {
                                goalkeeperSavesAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = goalkeeperSavesAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShootingStatistics(statisticsItemList: List<TeamStatisticsItem?>) {
    val linearGradient: List< Color> = listOf(
        MaterialTheme.colorScheme.primary.copy(0.5f),
        LightRed.copy(0.5f)
    )
    val barsLinearGradient: List<Color> = listOf(
        Color.White,
        Color.White
    )
    val configuration = LocalConfiguration.current

    //val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val dark = LocalTheme.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.shots),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(width = 25.dp, height = 25.dp),
                    painter = if (dark.isDark)
                        painterResource(id = R.drawable.shot_white)
                    else painterResource(id = R.drawable.shot_black),
                    contentDescription = "Shoot Icon"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var totalShotsHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Total Shots") {
                                totalShotsHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = totalShotsHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.totalShots),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var totalShotsAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Total Shots") {
                                totalShotsAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = totalShotsAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .drawBehind {
                        //Vertical Rectangle Left
                        drawRect(
                            brush = Brush.linearGradient(linearGradient),
                            topLeft = Offset(x = center.x / 5, y = 0f),
                            size = Size(width = size.width / 1.25f, height = size.height)
                        )
                        //Horizontal Bar
                        drawRect(
                            brush = Brush.linearGradient(barsLinearGradient),
                            topLeft = Offset(x = center.x / 2.2f, y = center.y / 1.2f),
                            size = Size(width = size.width / 1.8f, height = size.height / 9)
                        )
                        //Vertical Bar Left
                        drawRect(
                            brush = Brush.linearGradient(barsLinearGradient),
                            topLeft = Offset(x = center.x / 2.2f, y = center.y),
                            size = Size(width = size.width / 43, height = size.height / 2)
                        )
                        //Vertical Bar Right
                        drawRect(
                            brush = Brush.linearGradient(barsLinearGradient),
                            topLeft = Offset(x = center.x / 0.658f, y = center.y),
                            size = Size(width = size.width / 43, height = size.height / 2)
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 3.dp, vertical = 8.dp)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.Top
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .weight(4f)
                                .padding(bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var shotsOffGoalHomeText: Int? = 0
                            statisticsItemList[0]!!.statistics!!.forEach {
                                if (it!!.type == "Shots off Goal") {
                                    shotsOffGoalHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = shotsOffGoalHomeText.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f),
                                text = stringResource(id = R.string.offTarget),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            var shotsOffGoalAwayText: Int? = 0
                            statisticsItemList[1]!!.statistics!!.forEach {
                                if (it!!.type == "Shots off Goal") {
                                    shotsOffGoalAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = shotsOffGoalAwayText.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.width(screenWidth / 2)
                ) {
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .weight(4f)
                                .padding(bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            var shotsOnGoalHomeText: Int? = 0
                            statisticsItemList[0]!!.statistics!!.forEach {
                                if (it!!.type == "Shots on Goal") {
                                    shotsOnGoalHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = shotsOnGoalHomeText.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f),
                                text = stringResource(id = R.string.onTarget),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                            var shotsOnGoalAwayText: Int? = 0
                            statisticsItemList[1]!!.statistics!!.forEach {
                                if (it!!.type == "Shots on Goal") {
                                    shotsOnGoalAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                text = shotsOnGoalAwayText.toString(),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                ),
                                maxLines = 1,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var blockedShotsHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Blocked Shots") {
                                blockedShotsHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = blockedShotsHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.blockedShots),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var blockedShotsAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Blocked Shots") {
                                blockedShotsAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = blockedShotsAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var shotsInsideBoxHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Shots insidebox") {
                                shotsInsideBoxHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = shotsInsideBoxHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.shotsInside),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var shotsInsideBoxAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Shots insidebox") {
                                shotsInsideBoxAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = shotsInsideBoxAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var shotsOutsideBoxHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Shots outsidebox") {
                                shotsOutsideBoxHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = shotsOutsideBoxHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.shotsOutside),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var shotsOutsideBoxAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Shots outsidebox") {
                                shotsOutsideBoxAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = shotsOutsideBoxAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DisciplineStatistics(statisticsItemList: List<TeamStatisticsItem?>) {
    val dark = LocalTheme.current.isDark
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.matchDiscipline),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(width = 25.dp, height = 25.dp),
                    painter = if(dark) painterResource(id = R.drawable.card_white)
                    else painterResource(id = R.drawable.card_black),
                    contentDescription = "Shoot Icon"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var yellowCardsHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Yellow Cards") {
                                yellowCardsHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = yellowCardsHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.matchDiscYellow),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var yellowCardsAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Yellow Cards") {
                                yellowCardsAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = yellowCardsAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 3.dp, vertical = 4.dp)
            ) {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(4f)
                            .padding(bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var redCardsHomeText: Int? = 0
                        statisticsItemList[0]!!.statistics!!.forEach {
                            if (it!!.type == "Red Cards") {
                                redCardsHomeText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = redCardsHomeText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            text = stringResource(id = R.string.matchDiscRed),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                        var redCardsAwayText: Int? = 0
                        statisticsItemList[1]!!.statistics!!.forEach {
                            if (it!!.type == "Red Cards") {
                                redCardsAwayText = ((it.value ?: 0.0) as Double?)?.toInt()
                            }
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = redCardsAwayText.toString(),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            ),
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}