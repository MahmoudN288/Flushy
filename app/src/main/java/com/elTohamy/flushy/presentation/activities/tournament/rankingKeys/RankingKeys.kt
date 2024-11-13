package com.elTohamy.flushy.presentation.activities.tournament.rankingKeys

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.ripple
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.DarkBlue
import com.elTohamy.flushy.presentation.ui.theme.DarkGreen
import com.elTohamy.flushy.presentation.ui.theme.DarkRed
import com.elTohamy.flushy.presentation.ui.theme.LightBlue
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.presentation.ui.theme.Orange
import com.elTohamy.flushy.presentation.ui.theme.Yellow

@Composable
fun FCWC() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun PL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Championship",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Bundesliga() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Bundesliga.2",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun LigueA() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Ligue 2",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Netherlands() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Yellow
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Yellow, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Eredivisie.2 | Playoff",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Eredivisie.2",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Portugal() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Yellow
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Yellow, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Liga Portugal.2 | Playoff",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Liga Portugal.2",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Italy() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Serie B",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun LaLiga() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Segunda Division",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Belgium() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To 2.Division | Playoff",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Turkey() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Champions League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Yellow
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Yellow, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To 1. Lig",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Brazil() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Libertadores Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = LightGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(LightGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Libertadores | Q. Phase Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Sudamericana Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Serie B",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Argentina() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Libertadores Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To Primera B Nacional",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Roshan() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To AFC Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To AFC Cup Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EgyptianPL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To CAF Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To CAF Confederation Cup Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Tunisia() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To CAF Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To CAF Confederation Cup Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Botola() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To CAF Champions League Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To CAF Confederation Cup Next Season",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun UCL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa League Playoff",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun UEL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage | Q. Phase",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Uefa Europa Conference League Playoff",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun UECL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CAFCL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CAFCONF() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun AFCCL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun AFCCup() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Libertadores() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CONCL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWC() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCQAfrica() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next World Cup",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To World Cup Playoffs",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCQAsia() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next World Cup",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Fourth Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCQNorthAmerica() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next World Cup",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To World Cup Playoffs",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCQEurope() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next World Cup",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To World Cup Playoffs",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCQOFC() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCQSouthAmerica() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next World Cup",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To World Cup Playoffs",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Euro() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage | Waiting",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun UNL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage | Promotion To League A",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Promotion To League B | Relegation To League B",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkBlue
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkBlue, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Promotion To League C | Relegation To League C",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkRed
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkRed, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "Relegation To League D",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun AFCON() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage | Waiting",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun AsianCup() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = Orange
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(Orange, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage | Waiting",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun FWCWomen() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CopaAmerica() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CONMEBOL() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ArabCup() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun GoldCup() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 4.dp)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Standings Keys",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(
                                bounded = true,
                                color = DarkGreen
                            ),
                            onClick = {}
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(modifier = Modifier
                        .padding(4.dp)
                        .size(width = 15.dp, height = 15.dp)
                        .aspectRatio(1f)
                        .background(DarkGreen, shape = CircleShape)
                    )
                    Text(
                        modifier = Modifier,
                        text = "To Next Stage",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}