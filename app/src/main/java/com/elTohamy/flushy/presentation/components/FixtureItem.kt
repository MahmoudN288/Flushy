package com.elTohamy.flushy.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun NotStartedMatchItem(
    shortStatusState: String, time: String,
    inHeader: Boolean
) {
    if (shortStatusState == "TBD") {
        Text(
            text = stringResource(id = R.string.tbd),
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (inHeader) 16.sp else 12.sp
            )
        )
    } else if (shortStatusState == "NS") {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = time,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (inHeader) 12.sp else 10.sp
                )
            )
        }
    }
}

@Composable
fun InPlayMatchItem(
    goalsHome: Int, goalsAway: Int,
    elapsedTime: Int, shortStatusState: String,
    penaltyScoreHome: Int, penaltyScoreAway: Int,
    inHeader: Boolean
) {
    val dark = LocalTheme.current.isDark
    Column(
        modifier = Modifier.padding(vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = "$elapsedTime'",
            style = TextStyle(
                color = Color.Red,
                fontWeight = FontWeight.Normal,
                fontSize = if (inHeader) 12.sp else 10.sp
            ),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = "$goalsHome",
                style = TextStyle(
                    color =
                    if (dark) {
                        if (goalsHome > goalsAway)
                            MaterialTheme.customColorsPalette.blackToWhite
                        else Color.LightGray
                    } else {
                        if (goalsHome > goalsAway)
                            MaterialTheme.customColorsPalette.blackToWhite
                        else Color.DarkGray
                    },
                    fontWeight =
                    if (goalsHome > goalsAway)
                        FontWeight.SemiBold
                    else FontWeight.Normal,
                    fontSize = if (inHeader) 14.sp else 12.sp
                ),
                maxLines = 1
            )
            Text(
                modifier = Modifier,
                text = " - ",
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (inHeader) 13.sp else 11.sp
                ),
                maxLines = 1
            )
            Text(
                modifier = Modifier,
                text = "$goalsAway",
                style = TextStyle(
                    color =
                    if (dark) {
                        if (goalsHome < goalsAway)
                            MaterialTheme.customColorsPalette.blackToWhite
                        else Color.LightGray
                    } else {
                        if (goalsHome < goalsAway)
                            MaterialTheme.customColorsPalette.blackToWhite
                        else Color.DarkGray
                    },
                    fontSize = if (inHeader) 14.sp else 12.sp
                ),
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(4.dp),
                    text =
                    if (inHeader) {
                        when(shortStatusState) {
                            "1H" -> stringResource(id = R.string.half_1)
                            "2H" -> stringResource(id = R.string.half_2)
                            "HT" -> stringResource(id = R.string.half_time)
                            "ET" -> stringResource(id = R.string.et)
                            "BT" -> stringResource(id = R.string.bt)
                            "P" -> stringResource(id = R.string.p)
                            else -> ""
                        }
                    } else {
                        when(shortStatusState) {
                            "1H" -> stringResource(id = R.string.small_half_1)
                            "2H" -> stringResource(id = R.string.small_half_2)
                            "HT" -> stringResource(id = R.string.small_half_time)
                            "ET" -> stringResource(id = R.string.small_et)
                            "BT" -> stringResource(id = R.string.small_bt)
                            "P" -> stringResource(id = R.string.small_p)
                            else -> ""
                        }
                    },
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = if (inHeader) 12.sp else 10.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            if (shortStatusState == "P") {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "(",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "$penaltyScoreHome",
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (penaltyScoreHome > penaltyScoreAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (penaltyScoreHome > penaltyScoreAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontWeight =
                            if (penaltyScoreHome > penaltyScoreAway)
                                FontWeight.SemiBold
                            else FontWeight.Normal,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = " - ",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "$penaltyScoreAway",
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (penaltyScoreHome < penaltyScoreAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (penaltyScoreHome < penaltyScoreAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontWeight =
                            if (penaltyScoreAway > penaltyScoreHome)
                                FontWeight.SemiBold
                            else FontWeight.Normal,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = ")",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun StoppedMatchItem(
    goalsHome: Int, goalsAway: Int,
    shortStatusState: String, inHeader: Boolean
) {
    val dark = LocalTheme.current.isDark
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = goalsHome.toString(),
            style = TextStyle(
                color =
                if (dark) {
                       if (goalsHome > goalsAway)
                           MaterialTheme.customColorsPalette.blackToWhite
                       else Color.LightGray
                } else {
                       if (goalsHome > goalsAway)
                           MaterialTheme.customColorsPalette.blackToWhite
                       else Color.DarkGray
                },
                fontWeight =
                if (goalsHome > goalsAway)
                    FontWeight.SemiBold
                else FontWeight.Normal,
                fontSize = if (inHeader) 13.sp else 11.sp
            )
        )
        Card(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Text(
                modifier = Modifier.padding(3.dp),
                text =
                if (inHeader) {
                    when (shortStatusState) {
                        "SUSP" -> stringResource(id = R.string.sus)
                        "INT" -> stringResource(id = R.string.inter)
                        else -> ""
                    }
                } else {
                    when (shortStatusState) {
                        "SUSP" -> stringResource(id = R.string.small_sus)
                        "INT" -> stringResource(id = R.string.small_inter)
                        else -> ""
                    }
                },
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = if (inHeader) 12.sp else 10.sp
                )
            )
        }
        Text(
            text = goalsAway.toString(),
            style = TextStyle(
                color =
                if (dark) {
                    if (goalsHome < goalsAway)
                        MaterialTheme.customColorsPalette.blackToWhite
                    else Color.LightGray
                } else {
                    if (goalsHome < goalsAway)
                        MaterialTheme.customColorsPalette.blackToWhite
                    else Color.DarkGray
                },
                fontWeight =
                if (goalsAway > goalsHome)
                     FontWeight.SemiBold
                else FontWeight.Normal,
                fontSize = if (inHeader) 13.sp else 11.sp
            )
        )
    }
}

@Composable
fun MatchFinishedItem(
    goalsHome: Int, goalsAway: Int,
    shortStatusState: String,
    penaltyScoreHome: Int, penaltyScoreAway: Int,
    inHeader: Boolean
) {
    val dark = LocalTheme.current.isDark
    when (shortStatusState) {
        "FT" -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = goalsHome.toString(),
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (goalsHome > goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (goalsHome > goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontWeight =
                            if (goalsHome > goalsAway)
                                FontWeight.SemiBold
                            else FontWeight.Normal,
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = " - ",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = goalsAway.toString(),
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (goalsHome < goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (goalsHome < goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                }
                Card(
                    modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = stringResource(
                            id = if (inHeader) R.string.ft else R.string.small_ft
                        ),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        maxLines = 1
                    )
                }
            }
        }
        "AET" -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = goalsHome.toString(),
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (goalsHome > goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (goalsHome > goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontWeight =
                            if (goalsHome > goalsAway)
                                FontWeight.SemiBold
                            else FontWeight.Normal,
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = " - ",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = goalsAway.toString(),
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (goalsHome < goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (goalsHome < goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                }
                Card(
                    modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = stringResource(
                            id = if (inHeader) R.string.aet else R.string.small_aet
                        ),
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (inHeader) 12.sp else 10.sp
                        ),
                        maxLines = 1
                    )
                }
            }
        }
        "PEN" -> {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier,
                        text = goalsHome.toString(),
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (goalsHome > goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (goalsHome > goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontWeight =
                            if (goalsHome > goalsAway)
                                FontWeight.SemiBold
                            else FontWeight.Normal,
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = " - ",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier,
                        text = goalsAway.toString(),
                        style = TextStyle(
                            color =
                            if (dark) {
                                if (goalsHome < goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.LightGray
                            } else {
                                if (goalsHome < goalsAway)
                                    MaterialTheme.customColorsPalette.blackToWhite
                                else Color.DarkGray
                            },
                            fontSize = if (inHeader) 13.sp else 11.sp
                        ),
                        maxLines = 1
                    )
                }
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Card(
                        modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = stringResource(
                                id = if (inHeader) R.string.pen else R.string.small_pen
                            ),
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = if (inHeader) 12.sp else 10.sp
                            ),
                            maxLines = 1
                        )
                    }
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "(",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = if (inHeader) 12.sp else 10.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "$penaltyScoreHome",
                            style = TextStyle(
                                color =
                                if (dark) {
                                    if (penaltyScoreHome > penaltyScoreAway)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else Color.LightGray
                                } else {
                                    if (penaltyScoreHome > penaltyScoreAway)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else Color.DarkGray
                                },
                                fontWeight =
                                if (penaltyScoreHome > penaltyScoreAway)
                                    FontWeight.SemiBold
                                else FontWeight.Normal,
                                fontSize = if (inHeader) 12.sp else 10.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = " - ",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = if (inHeader) 12.sp else 10.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "$penaltyScoreAway",
                            style = TextStyle(
                                color =
                                if (dark) {
                                    if (penaltyScoreHome < penaltyScoreAway)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else Color.LightGray
                                } else {
                                    if (penaltyScoreHome < penaltyScoreAway)
                                        MaterialTheme.customColorsPalette.blackToWhite
                                    else Color.DarkGray
                                },
                                fontWeight =
                                if (penaltyScoreAway > penaltyScoreHome)
                                    FontWeight.SemiBold
                                else FontWeight.Normal,
                                fontSize = if (inHeader) 12.sp else 10.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = ")",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = if (inHeader) 12.sp else 10.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NotPlayedMatchItem(
    shortStatusState: String, inHeader: Boolean
) {
    when (shortStatusState) {
        "WO" -> {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(
                        id = if (inHeader) R.string.wo else R.string.small_wo
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = if (inHeader) 12.sp else 10.sp
                    ),
                    maxLines = 1
                )
            }
        }
        "AWD" -> {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(
                        id = if (inHeader) R.string.awd else R.string.small_awd
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = if (inHeader) 12.sp else 10.sp
                    ),
                    maxLines = 1
                )
            }
        }
        "CANC" -> {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(
                        id = if (inHeader) R.string.canc else R.string.small_canc
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = if (inHeader) 12.sp else 10.sp
                    ),
                    maxLines = 1
                )
            }
        }
        "ABD" -> {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(
                        id = if (inHeader) R.string.abd else R.string.small_abd
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = if (inHeader) 12.sp else 10.sp
                    ),
                    maxLines = 1
                )
            }
        }
        "PST" -> {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(
                        id = if (inHeader) R.string.pst else R.string.small_pst
                    ),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = if (inHeader) 12.sp else 10.sp
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotPlayedMatchItemPreview() {
    NotPlayedMatchItem("WO", true)
}

@Preview(showBackground = true)
@Composable
fun MatchFinishedItemPreview() {
    MatchFinishedItem(
        2, 2, "FT",
        5, 4, false
    )
}

@Preview(showBackground = true)
@Composable
fun StoppedMatchItemPreview() {
    StoppedMatchItem(
        3, 0, "SUSP", false
    )
}

@Preview(showBackground = true)
@Composable
fun InPlayMatchItemItemPreview() {
    InPlayMatchItem(
        2, 1, 80,
        "P", 5,
        4, false
    )
}

@Preview(showBackground = true)
@Composable
fun NotStartedMatchItemPreview() {
    NotStartedMatchItem(
        "NS", "10:00", false
    )
}