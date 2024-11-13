package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.detailsScreen

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material3.ripple
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponseItem
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.data.state.MatchInfoState
import com.elTohamy.flushy.presentation.activities.match.viewModel.MatchViewModel
import com.elTohamy.flushy.presentation.activities.tournament.TournamentActivity
import com.elTohamy.flushy.utils.getTournamentName
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreens() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FetchMatchDetails()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FetchMatchDetails(matchViewModel: MatchViewModel = hiltViewModel()) {
    Column {
        when (val state = matchViewModel.matchInfoState.collectAsState().value) {
            is MatchInfoState.Empty -> {}
            is MatchInfoState.Loading -> {}
            is MatchInfoState.Error -> {}
            is MatchInfoState.Success -> {
                InfoScrollableContent(
                    matchByIdResponseItem = state.data.body()!!
                        .response?.get(0)!!
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoScrollableContent(
    matchByIdResponseItem: MatchByIdResponseItem
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
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
                text = stringResource(id = R.string.matchDet),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
        }
        InfoSection(
            matchByIdResponseItem = matchByIdResponseItem
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoSection(
    matchByIdResponseItem: MatchByIdResponseItem,
    matchViewModel: MatchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    if (matchViewModel.isDialogShown) {
        FetchStadiumInfo(
            onDismiss = { matchViewModel.onDismiss() },
            onConfirm = {}
        )
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.matchDetailsCardContainer
        ),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp, pressedElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Header Info
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = { }
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.matchInfo),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            //Tournament Row
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            val intent = Intent(context, TournamentActivity::class.java)
                            intent.putExtra("leagueId", matchByIdResponseItem.league!!.id)
                            intent.putExtra("currentSeason", matchByIdResponseItem.league.season)
                            intent.putExtra("leagueCountry", matchByIdResponseItem.league.country)
                            intent.putExtra("leagueName", matchByIdResponseItem.league.name)
                            context.startActivity(intent)
                        }
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = stringResource(id = R.string.matchTournament),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(1f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    )
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = getTournamentName(
                                id = matchByIdResponseItem.league!!.id!!,
                                jsonName = matchByIdResponseItem.league.name!!
                            ),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        )
                        Spacer(modifier = Modifier
                            .width(8.dp)
                            .fillMaxHeight())
                        AsyncImage(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            model = matchByIdResponseItem.league.logo,
                            contentDescription = "Tournament Image"
                        )
                    }
                }
            }
            //Round
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = stringResource(id = R.string.matchRound),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(1f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        modifier = Modifier.width(200.dp),
                        text = matchByIdResponseItem.league!!.round!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
            //Stadium
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(
                            bounded = true,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            if (matchByIdResponseItem.fixture!!.venue!!.id != null) {
                                matchViewModel.setId(matchByIdResponseItem.fixture.venue!!.id!!)
                                matchViewModel.onDialogOpened()
                            }
                        }
                    )
                    .padding(top = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = stringResource(id = R.string.matchVenue),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(1f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        modifier = Modifier.width(200.dp),
                        text = matchByIdResponseItem.fixture!!.venue!!.name!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
            //City
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = stringResource(id = R.string.matchLocation),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(1f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        modifier = Modifier.width(200.dp),
                        text = "(${matchByIdResponseItem.fixture!!.venue!!.city!!} - ${matchByIdResponseItem.league!!.country})",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
            //Date and Time
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                /*val readPattern = "yyyy-MM-dd HH:mm:ss.S"
                val readDateTimeFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern(readPattern).withZone(ZoneOffset.UTC)
                val utcLocalDateTime: LocalDateTime =
                    LocalDateTime.parse(matchByIdResponseItem.fixture!!.date, readDateTimeFormatter)
                val localZonedDateTime: ZonedDateTime = utcLocalDateTime.atOffset(ZoneOffset.UTC)
                    .atZoneSameInstant(ZoneId.systemDefault())
                val writePattern = "yyyy-MM-dd HH:mm:ssXXX"
                val writeDateTimeFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern(writePattern)*/
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(IntrinsicSize.Max),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = stringResource(id = R.string.matchDateAndTime),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite.copy(1f),
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    )
                    Row(
                        modifier = Modifier.width(200.dp)
                    ) {
                        val date by rememberSaveable { mutableStateOf<LocalDate?>(LocalDate.now()) }
                        Text(
                            modifier = Modifier.width(200.dp),
                            text = if (matchByIdResponseItem.fixture!!.date!!.substring(0, 10) == date.toString())
                                matchByIdResponseItem.fixture.date!!.substring(11, 16)
                            else matchByIdResponseItem.fixture.date!!.substring(0, 10),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        )
                    }
                }
            }
        }
    }
}