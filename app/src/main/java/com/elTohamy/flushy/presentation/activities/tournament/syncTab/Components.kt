package com.elTohamy.flushy.presentation.activities.tournament.syncTab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ripple
import coil.compose.AsyncImage
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout

@Composable
fun TodayGamesCard(game: TodayGamesItem) {
    BoxWithLayout {
        Row(
            modifier = Modifier.fillMaxWidth()
                .weight(10f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = game.time,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.weight(9f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .shadow(elevation = 1.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(width = 25.dp, height = 25.dp),
                            model = game.teamHLogo,
                            contentDescription = "Team A Logo"
                        )
                        Text(
                            modifier = Modifier.width(150.dp),
                            text = game.teamHName,
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Text(
                        modifier = Modifier.width(35.dp),
                        text = game.teamHResult.toString(),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .shadow(elevation = 1.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(width = 25.dp, height = 25.dp),
                            model = game.teamALogo,
                            contentDescription = "Team Away Logo"
                        )
                        Text(
                            modifier = Modifier.width(150.dp),
                            text = game.teamAName,
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Text(
                        modifier = Modifier.width(35.dp),
                        text = game.teamAResult.toString(),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodayGamesCardPreview() {
    TodayGamesCard(
        game = TodayGamesItem("Liverpool", "",
            3, "82", 1,
            "", "Man City"
        )
    )
}

@Composable
fun StandingsItemCard(item: TopTeamsPoints) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(8.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    ),
                    textAlign = TextAlign.Center
                )
                AsyncImage(
                    modifier = Modifier
                        .size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier
                        .width(150.dp)
                        .padding(8.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.wins.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.draws.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.losses.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.points.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StandingsItemCardPreview() {
    StandingsItemCard(
        TopTeamsPoints(
            1, "", " Liverpool",
            27, 19, 6, 2, 63
        )
    )
}

@Composable
fun TopTeamsCleanCard(item: TopTeamsClean) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(8.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier.size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier
                        .width(150.dp)
                        .padding(8.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.cleanSheets.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopTeamsCleanCardPreview() {
    TopTeamsCleanCard(
        TopTeamsClean(
            1, "", "Arsenal",
            27, 10
        )
    )
}

@Composable
fun TopTeamsYellowCardsCard(item: TopTeamsYellowCards) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.yellowCards.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopTeamsYellowCardsCardPreview() {
    TopTeamsYellowCardsCard(
        TopTeamsYellowCards(
            1, "", "Chelsea",
            27, 81
        )
    )
}

@Composable
fun TopTeamsRedCardsCard(item: TopTeamsRedCards) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(start = 2.dp, end = 3.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.redCards.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopTeamsRedCardsCardPreview() {
    TopTeamsRedCardsCard(
        TopTeamsRedCards(
            1, "", "Liverpool",
            27, 5
        )
    )
}

@Composable
fun TopScoredTeamsCard(item: TopTeamsMostScored) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.goalsScored.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopScoredTeamsCardsCardPreview() {
    TopScoredTeamsCard(
        TopTeamsMostScored(
            1, "", "Liverpool",
            27, 64
        )
    )
}

@Composable
fun LessTeamsConcededCardsCard(item: TopTeamsLessConceded) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.goalsConceded.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LessTeamsConcededCardsCardPreview() {
    LessTeamsConcededCardsCard(
        TopTeamsLessConceded(
            1, "", "Arsenal",
            27, 24
        )
    )
}

@Composable
fun MostPenaltiesTeamsCard(item: TopTeamsPenalties) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.teamRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.teamLogo,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.teamName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.missed.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.scored.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.total.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MostPenaltiesTeamsCardPreview() {
    MostPenaltiesTeamsCard(
        TopTeamsPenalties(
            1, "", "Arsenal",
            27, 0, 8, 8
        )
    )
}

@Composable
fun TopScorersCard(item: TopScorersItem) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.playerRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.playerImage,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.playerName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.nonPenalty.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.goals.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopScorersCardPreview() {
    TopScorersCard(
        TopScorersItem(
            1, "", "Erling Haaland",
            27, 15, 18
        )
    )
}

@Composable
fun TopAssistsCard(item: TopAssistItem) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true),
                    onClick = {}
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .width(35.dp)
                        .padding(8.dp),
                    text = item.playerRank.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                AsyncImage(
                    modifier = Modifier
                        .wrapContentHeight()
                        .size(width = 25.dp, height = 25.dp),
                    model = item.playerImage,
                    contentDescription = "Logo"
                )
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.playerName,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(5f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.apps.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    modifier = Modifier.width(35.dp),
                    text = item.assists.toString(),
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopAssistsCardPreview() {
    TopAssistsCard(
        TopAssistItem(
            1, "", "Ollie Watkins",
            27, 10
        )
    )
}