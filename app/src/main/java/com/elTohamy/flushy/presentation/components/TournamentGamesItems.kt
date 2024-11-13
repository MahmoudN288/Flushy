package com.elTohamy.flushy.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette

@Composable
fun TournamentNotStartedMatchItem(
    shortStatusState: String, date: String, day: String,
    time: String, today: Boolean
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (shortStatusState == "NS") {
            if (today) {
                Text(
                    modifier = Modifier,
                    text = time,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    ),
                    maxLines = 1
                )
            } else {
                Column(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 3.dp),
                        text = date,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = day,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = time,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp
                        ),
                        maxLines = 1
                    )
                }
            }
        } else if (shortStatusState == "TBD") {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.tbd),
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun TournamentInPlayMatchItem(
    elapsedTime: Int, shortStatusState: String
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = "$elapsedTime'",
            style = TextStyle(
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            ),
            maxLines = 1
        )
        Card(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp),
                text =
                when(shortStatusState) {
                    "1H" -> stringResource(id = R.string.half_1)
                    "2H" -> stringResource(id = R.string.half_2)
                    "HT" -> stringResource(id = R.string.half_time)
                    "ET" -> stringResource(id = R.string.et)
                    "BT" -> stringResource(id = R.string.bt)
                    "P" -> stringResource(id = R.string.p)
                    else -> ""
                },
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun TournamentStoppedMatchItem(
    shortStatusState: String
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp),
                text =
                when (shortStatusState) {
                    "SUSP" -> stringResource(id = R.string.sus)
                    "INT" -> stringResource(id = R.string.inter)
                    else -> ""
                },
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun TournamentMatchFinishedItem(
    shortStatusState: String
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                modifier = Modifier.padding
                    (horizontal = 5.dp, vertical = 3.dp),
                text =
                when (shortStatusState) {
                    "FT" -> {
                        stringResource(id = R.string.ft)
                    }

                    "AET" -> {
                        stringResource(id = R.string.aet)
                    }

                    "PEN" -> {
                        stringResource(id = R.string.pen)
                    }

                    else -> {
                        stringResource(id = R.string.ft)
                    }
                },
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun TournamentNotPlayedMatchItem(
    shortStatusState: String
) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 3.dp),
                text =
                when (shortStatusState) {
                    "WO" -> { stringResource(id = R.string.wo) }
                    "AWD" -> { stringResource(id = R.string.awd) }
                    "CANC" -> { stringResource(id = R.string.canc) }
                    "ABD" -> { stringResource(id = R.string.abd) }
                    "PST" -> { stringResource(id = R.string.pst) }
                    else -> { stringResource(id = R.string.notDefined) }
                },
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TournamentNotPlayedMatchItemPreview() {
    TournamentNotPlayedMatchItem("WO")
}

@Preview(showBackground = true)
@Composable
fun TournamentMatchFinishedItemPreview() {
    TournamentMatchFinishedItem(
        "FT"
    )
}

@Preview(showBackground = true)
@Composable
fun TournamentStoppedMatchItemPreview() {
    TournamentStoppedMatchItem(
        "SUSP"
    )
}

@Preview(showBackground = true)
@Composable
fun TournamentInPlayMatchItemItemPreview() {
    TournamentInPlayMatchItem(
        80, "P"
    )
}

@Preview(showBackground = true)
@Composable
fun TournamentNotStartedMatchItemPreview() {
    TournamentNotStartedMatchItem(

        "NS", "2024-04-30", "Tuesday",
        "09:00", false
    )
}