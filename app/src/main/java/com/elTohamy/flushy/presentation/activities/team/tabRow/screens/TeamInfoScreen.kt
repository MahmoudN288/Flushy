package com.elTohamy.flushy.presentation.activities.team.tabRow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.elTohamy.flushy.presentation.activities.team.tabRow.components.FetchStadiumInfo
import com.elTohamy.flushy.presentation.activities.team.viewModel.TeamViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun TeamInfoScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 10.dp),
            text = stringResource(id = R.string.teamMainInfo),
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            ),
            textAlign = TextAlign.Start
        )
        FetchTeamInfo()
    }
}

@Composable
fun FetchTeamInfo(teamViewModel: TeamViewModel = hiltViewModel()) {
    when (val state = teamViewModel.teamInfoByIdState.collectAsState().value) {
        is TeamInfoState.Empty -> {}
        is TeamInfoState.Loading -> {}
        is TeamInfoState.Error -> {}
        is TeamInfoState.Success -> {
            TeamMainInfo(teamsInfoResponse = state.data.body()!!.response!![0]!!)
        }
    }
}

@Composable
fun TeamMainInfo(
    teamsInfoResponse: TeamsInfoResponse?,
    teamViewModel: TeamViewModel = hiltViewModel()
) {
    val dark = LocalTheme.current.isDark
    if (teamViewModel.isDialogShown) {
        FetchStadiumInfo(
            onDismiss = { teamViewModel.onDismiss() },
            onConfirm = {}
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(4f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.padding(5.dp),
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(width = 100.dp, height = 100.dp),
                            model = teamsInfoResponse!!.team!!.logo,
                            contentDescription = "Player Image"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxHeight()
                            .padding(horizontal = 5.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = teamsInfoResponse!!.team!!.name!!,
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = teamsInfoResponse.team!!.country!!,
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.8f),
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            ),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.teamFounded),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = (teamsInfoResponse!!.team!!.founded ?: 0).toString(),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.teamClassification),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.6f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text =
                        if (teamsInfoResponse!!.team!!.national == true) stringResource(id = R.string.teamIsNational)
                        else stringResource(id = R.string.teamIsClub),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        if (teamsInfoResponse!!.venue!!.id != null) {
                            teamViewModel.setId(teamsInfoResponse.venue!!.id!!)
                            teamViewModel.onDialogOpened()
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.teamStadium),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .weight(2f)
                            .height(IntrinsicSize.Max)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = (teamsInfoResponse!!.venue!!.name ?: stringResource(id = R.string.notDefined)),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 11.sp
                                )
                            )
                            Card(
                                modifier = Modifier,
                                shape = RoundedCornerShape(15.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(100.dp),
                                    model = teamsInfoResponse.venue!!.image,
                                    contentDescription = "Main Stadium",
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Text(
                                text = (teamsInfoResponse.venue!!.city ?: stringResource(id = R.string.notDefined)),
                                style = TextStyle(
                                    color = if (dark) Color.LightGray else Color.Gray,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp
                                )
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text =
                                if (
                                    (teamsInfoResponse!!.venue!!.surface ?:
                                    stringResource(id = R.string.notDefined)) == "Grass"
                                ) stringResource(id = R.string.teamStadiumGrass) else (teamsInfoResponse.venue!!.surface ?:
                                stringResource(id = R.string.notDefined)),
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 11.sp
                                )
                            )
                            Card(
                                modifier = Modifier,
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Image(
                                    modifier = Modifier.size(width = 75.dp, height = 100.dp),
                                    painter = painterResource(id = R.drawable.grass),
                                    contentDescription = "Grass Pitch"
                                )
                            }
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                    ),
                    onClick = {
                        if (teamsInfoResponse!!.venue!!.id != null) {
                            teamViewModel.setId(teamsInfoResponse.venue!!.id!!)
                            teamViewModel.onDialogOpened()
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.teamStadiumInfo),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp
                            )
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            BoxWithLayout {
                                Row(
                                    modifier = Modifier
                                        .weight(2f)
                                        .height(IntrinsicSize.Max),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.teamStadiumCapacity),
                                            style = TextStyle(
                                                color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.7f),
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 11.sp
                                            )
                                        )
                                        Icon(
                                            modifier = Modifier.size(15.dp),
                                            imageVector = Icons.Default.People,
                                            contentDescription = "Capacity"
                                        )
                                    }
                                    Text(
                                        text = (
                                                (teamsInfoResponse!!.venue!!.capacity ?:
                                                stringResource(id = R.string.notDefined)).toString()
                                                ),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }
                            BoxWithLayout {
                                Row(
                                    modifier = Modifier.weight(2f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.teamStadiumAddress),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.7f),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 11.sp
                                        )
                                    )
                                    Text(
                                        text = teamsInfoResponse!!.venue!!.address ?: stringResource(
                                            id = R.string.notDefined
                                        ),
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 11.sp
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