package com.elTohamy.flushy.presentation.activities.team.tabRow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.teams.TeamsInfoResponse
import com.elTohamy.flushy.data.state.TeamInfoState
import com.elTohamy.flushy.data.state.TeamSeasonsState
import com.elTohamy.flushy.presentation.activities.team.viewModel.TeamViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.Runner_Up
import com.elTohamy.flushy.presentation.ui.theme.Winner
import com.elTohamy.flushy.utils.BoxWithLayout

@Composable
fun TeamSeasonsScreen(team: Int) {
    FetchTeamSeasons(team = team)
}

@Composable
fun FetchTeamSeasons(teamViewModel: TeamViewModel = hiltViewModel(), team: Int) {
    LaunchedEffect(key1 = teamViewModel.isTeamSeasonsInitialized.value) {
        if (!teamViewModel.isTeamSeasonsInitialized.value) {
            teamViewModel.getTeamSeasons(team)
            teamViewModel.isTeamSeasonsInitialized.value = true
        }
    }
    when (val teamInfoState = teamViewModel.teamInfoByIdState.collectAsState().value) {
        is TeamInfoState.Empty -> {}
        is TeamInfoState.Loading -> {}
        is TeamInfoState.Error -> {}
        is TeamInfoState.Success -> {
            when (val teamSeasonsState = teamViewModel.teamSeasonsState.collectAsState().value) {
                is TeamSeasonsState.Empty -> {}
                is TeamSeasonsState.Loading -> {}
                is TeamSeasonsState.Error -> {}
                is TeamSeasonsState.Success -> {
                    TeamSeasonsList(
                        teamsInfoResponse = teamInfoState.data.body()!!.response!![0]!!,
                        teamSeasons = teamSeasonsState.data.body()!!.response!!
                    )
                }
            }
        }
    }
}

@Composable
fun TeamSeasonsList(
    teamsInfoResponse: TeamsInfoResponse?,
    teamSeasons: List<Int?>
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 10.dp),
                text = stringResource(id = R.string.teamSeasonsHeader),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                ),
                textAlign = TextAlign.Start
            )
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 10.dp),
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.teamSeasonsCardHeader),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
        itemsIndexed(
            teamSeasons.reversed(),
            itemContent = { index, item ->
                TeamSeasonsItem(
                    teamsInfoResponse = teamsInfoResponse,
                    teamSeason = item,
                    id = index + 1,
                    isLast = (index == 0),
                    isFirst = (index == (teamSeasons.size - 1))
                )
            }
        )
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Transparent))
        }
    }
}

@Composable
fun TeamSeasonsItem(
    teamsInfoResponse: TeamsInfoResponse?,
    teamSeason: Int?,
    id: Int,
    isLast: Boolean,
    isFirst: Boolean
) {
    Card(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        shape =
            if (isFirst) RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            else RectangleShape,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        )
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 5.dp, bottom = 5.dp, start = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(2.dp),
                    text = id.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                Row(
                    modifier = Modifier
                        .weight(2.5f)
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(25.dp),
                        model = teamsInfoResponse!!.team!!.logo,
                        contentDescription = "Team Logo"
                    )
                    Text(
                        text = teamsInfoResponse.team!!.name!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        maxLines = 1
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    text = (teamSeason ?: 0).toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                )
                Box(
                    modifier = Modifier
                        .weight(0.1f)
                        .fillMaxHeight()
                        .background(if (isLast) Winner else Runner_Up)
                )
            }
        }
    }
}