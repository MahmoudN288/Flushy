package com.elTohamy.flushy.presentation.activities.tournament.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ripple
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout

@Composable
fun TournamentOverviewScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TournamentInfoHeader()
        Spacer(modifier = Modifier.height(3.dp))
        TournamentAllTimeStats()
        //TournamentLatestNews()
    }
}

@Composable
fun TournamentAllTimeStats() {
    Card(
        shape = RoundedCornerShape(15.dp),
        colors = CardColors(
            contentColor = MaterialTheme.customColorsPalette.blackToWhite,
            containerColor = MaterialTheme.customColorsPalette.standingsRowsColor,
            disabledContentColor = MaterialTheme.customColorsPalette.blackToWhite,
            disabledContainerColor = MaterialTheme.customColorsPalette.standingsRowsColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = MaterialTheme.customColorsPalette.appBarPL
                ),
                onClick = {}
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(10f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(width = 25.dp, height = 25.dp),
                                imageVector = Icons.Default.Groups,
                                contentDescription = "Teams",
                                tint = MaterialTheme.customColorsPalette.logoTint
                            )
                        }
                        Row(
                            modifier = Modifier
                                .weight(9f)
                                .padding(start = 3.dp, end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(width = 60.dp, height = 60.dp),
                                    painter = painterResource(id = R.drawable.goal),
                                    contentDescription = "Most Titles"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Manchester United",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Most Decorated (20)",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(width = 60.dp, height = 60.dp),
                                    painter = painterResource(id = R.drawable.goal),
                                    contentDescription = "Most Recent"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Manchester City",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Most Recent (9)",
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
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column {
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(10f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(width = 25.dp, height = 25.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = "Players",
                                tint = MaterialTheme.customColorsPalette.logoTint
                            )
                        }
                        Row(
                            modifier = Modifier
                                .weight(9f)
                                .padding(start = 3.dp, end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(width = 60.dp, height = 60.dp),
                                    painter = painterResource(id = R.drawable.y_card),
                                    contentDescription = "Top Scorer"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Alan Shearer",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Top Scorer (260)",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(width = 60.dp, height = 60.dp),
                                    painter = painterResource(id = R.drawable.y_card),
                                    contentDescription = "Top Assist"
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Ryan Giggs",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = "Top Assist (162)",
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
        }
    }
}

@Composable
fun TournamentLatestNews() {
    Column(
        modifier = Modifier.padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(3.dp),
            text = "Premier League News",
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold
            )
        )
        LazyRow {
            items(8) {
                TournamentLatestNewsComponent()
            }
        }
    }
}

@Composable
fun TournamentLatestNewsComponent() {
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
        ) {
            val (saveCardIcon, cardImage, cardTitleContainer) = createRefs()
            IconButton(
                modifier = Modifier
                    .padding(4.dp)
                    .shadow(
                        ambientColor = Color.Red,
                        spotColor = MaterialTheme.colorScheme.primary,
                        elevation = 18.dp
                    ),
                onClick = {  }
            ) {
                Icon(
                    modifier = Modifier
                        .constrainAs(saveCardIcon) {
                            top.linkTo(cardImage.top, margin = 5.dp)
                            start.linkTo(cardImage.start, margin = 5.dp)
                        },
                    imageVector = Icons.Outlined.PushPin,
                    tint = Color.Black,
                    contentDescription = "Pin"
                )
            }
            Image(
                modifier = Modifier
                    .constrainAs(cardImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                painter = painterResource(id = R.drawable.r_card),
                contentDescription = "News Image"
            )
            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(cardTitleContainer) {
                        start.linkTo(cardImage.start, margin = 0.dp)
                        end.linkTo(cardImage.end, margin = 0.dp)
                        bottom.linkTo(cardImage.bottom)
                    }
                    .shadow(elevation = 25.dp)
                    .background(MaterialTheme.customColorsPalette.titleCardColor)
            ) {
                val (sourceName, title) = createRefs()
                Text(
                    modifier = Modifier
                        .constrainAs(sourceName) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                        },
                    text = "ESPN",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp, end = 10.dp)
                        .constrainAs(title) {
                            top.linkTo(sourceName.bottom, margin = 2.dp)
                            start.linkTo(sourceName.start)
                        },
                    text = "Ryan Giggs can now breath",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun TournamentInfoHeader() {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.lottie
        )
    )
    val dark = isSystemInDarkTheme()
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(3.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            ConstraintLayout {
                val (tournamentLogo, collection) = createRefs()
                Image(
                    modifier = Modifier
                        .size(width = 55.dp, height = 55.dp)
                        .constrainAs(tournamentLogo) {
                            start.linkTo(parent.start, margin = 5.dp)
                            top.linkTo(parent.top, margin = 3.dp)
                        },
                    painter = if(dark) painterResource(id = R.drawable.r_card)
                              else painterResource(id = R.drawable.ic_circle_checked),
                    contentDescription = "Tournament Logo"
                )
                Column(
                    modifier = Modifier
                        .constrainAs(collection) {
                            start.linkTo(tournamentLogo.end, margin = 3.dp)
                            top.linkTo(tournamentLogo.top, margin = 3.dp)
                            bottom.linkTo(tournamentLogo.bottom, margin = 3.dp)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ConstraintLayout {
                        val (
                            tournamentName, countryFlag, countryName
                        ) = createRefs()
                        Text(
                            modifier = Modifier
                                .constrainAs(tournamentName) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                },
                            text = "Premier League",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Image(
                            modifier = Modifier
                                .size(width = 30.dp, height = 30.dp)
                                .constrainAs(countryFlag) {
                                    top.linkTo(tournamentName.bottom)
                                    bottom.linkTo(parent.bottom)
                                },
                            painter = painterResource(id = R.drawable.ic_circle_checked),
                            contentDescription = "Country Flag"
                        )
                        Text(
                            modifier = Modifier
                                .constrainAs(countryName) {
                                    top.linkTo(countryFlag.top)
                                    bottom.linkTo(countryFlag.bottom)
                                    start.linkTo(countryFlag.end, margin = 2.dp)
                                },
                            text = "UK",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        //LottieAnimation(composition = preloaderLottieComposition)
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(end = 8.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.NotificationsActive,
                    contentDescription = "Tournament Follow Icon",
                    tint = MaterialTheme.customColorsPalette.logoTint
                )
            }
            Text(
                text = "Follow",
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TournamentInfoHeaderPreview() {
    TournamentInfoHeader()
}

@Preview(showBackground = true)
@Composable
fun TournamentLatestNewsComponentPreview() {
    TournamentLatestNewsComponent()
}

@Preview(showBackground = true)
@Composable
fun TournamentLatestNewsPreview() {
    TournamentLatestNews()
}

@Preview(showBackground = true)
@Composable
fun TournamentAllTimeStatsPreview() {
    TournamentAllTimeStats()
}

@Preview(showBackground = true)
@Composable
fun TournamentOverviewScreenPreview() {
    TournamentOverviewScreen(rememberNavController())
}