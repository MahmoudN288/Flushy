package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.presentation.ui.theme.Orange

@Composable
fun PlayerOnBenchComponent(
    playerImage: String, name: String, number: Int,
    goals: Int, assists: Int, playerRating: String,
    yellowCards: Int, redCards: Int, hasSubbed: Boolean,
    hasInvolved: Boolean, isCaptain: Boolean, time: Int,
    onClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(4.dp)
            ) {
                ConstraintLayout(
                    modifier = Modifier
                ) {
                    val (
                        image, goalIcon, goalsN, assistIcon,
                        assistsN, yellowCardIcon, redCardIcon
                    ) = createRefs()
                    Card(
                        modifier = Modifier
                            .clip(CircleShape)
                            .constrainAs(image) {
                                top.linkTo(parent.top, margin = 2.dp)
                                start.linkTo(parent.start, margin = 10.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(width = 50.dp, height = 50.dp),
                            model = playerImage,
                            contentDescription = "image"
                        )
                    }
                    //Goals
                    if (goals > 0) {
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    width = 0.1.dp, color = MaterialTheme.colorScheme.primary,
                                    shape = CircleShape
                                )
                                .constrainAs(goalIcon) {
                                    end.linkTo(image.end, margin = 3.4.dp)
                                    top.linkTo(image.top)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(width = 14.dp, height = 14.dp),
                                painter = painterResource(id = R.drawable.goal_lineups),
                                contentDescription = "Goal Icon"
                            )
                        }
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .constrainAs(goalsN) {
                                    start.linkTo(goalIcon.end, margin = 1.dp)
                                    top.linkTo(goalIcon.top)
                                    bottom.linkTo(goalIcon.bottom)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(2.dp)
                        )  {
                            Text(
                                modifier = Modifier.padding(0.3.dp),
                                text = "x$goals",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 6.sp
                                )
                            )
                        }
                    }
                    //Assists
                    if (assists > 0) {
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    width = 0.1.dp, color = MaterialTheme.colorScheme.primary,
                                    shape = CircleShape
                                )
                                .constrainAs(assistIcon) {
                                    end.linkTo(image.end, margin = 3.4.dp)
                                    bottom.linkTo(image.bottom)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(width = 14.dp, height = 14.dp),
                                painter = painterResource(id = R.drawable.assist),
                                contentDescription = "Goal Icon"
                            )
                        }
                        Card(
                            modifier = Modifier
                                .constrainAs(assistsN) {
                                    start.linkTo(assistIcon.end, margin = 1.dp)
                                    top.linkTo(assistIcon.top)
                                    bottom.linkTo(assistIcon.bottom)
                                },
                            shape = CircleShape,
                            elevation = CardDefaults.cardElevation(2.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        )  {
                            Text(
                                modifier = Modifier.padding(0.3.dp),
                                text = "x$assists",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 6.sp
                                )
                            )
                        }
                    }
                    //Yellow Card
                    if (yellowCards > 0) {
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .constrainAs(yellowCardIcon) {
                                    start.linkTo(image.start)
                                    top.linkTo(image.top)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(1.8.dp)
                                    .size(width = 11.dp, height = 11.dp),
                                painter = painterResource(id = R.drawable.y_card),
                                contentDescription = "Yellow Card"
                            )
                        }
                    }
                    //Red Card
                    if (redCards > 0) {
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .constrainAs(redCardIcon) {
                                    start.linkTo(image.start)
                                    bottom.linkTo(image.bottom)
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(1.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(1.8.dp)
                                    .size(width = 11.dp, height = 11.dp),
                                painter = painterResource(id = R.drawable.r_card),
                                contentDescription = "Red Card"
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isCaptain) {
                        Text(
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(Color.White)
                                .size(width = 14.dp, height = 14.dp),
                            text = "C",
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        modifier = Modifier
                            .width(150.dp),
                        text = "$number. $name",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        ),
                        maxLines = 2
                    )
                }
                //Sub
                if (hasSubbed) {
                    Row(
                        modifier = Modifier
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                            ,
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(0.5.dp)
                                    .size(width = 13.dp, height = 13.dp),
                                painter = painterResource(id = R.drawable.sub_in),
                                contentDescription = "Sub"
                            )
                        }
                        Text(
                            modifier = Modifier,
                            text = "  Subbed In $time'",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 11.sp
                            ),
                            maxLines = 1
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                //Rating
                if (hasInvolved) {
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor =
                            if (playerRating.toDouble() in 0.0..3.9)
                                Color.Red
                            else if (playerRating.toDouble() in 4.0..5.9)
                                LightRed
                            else if (playerRating.toDouble() in 6.0..6.9)
                                Orange
                            else if (playerRating.toDouble() in 7.0..7.9)
                                LightGreen
                            else if (playerRating.toDouble() in 8.0..10.0)
                                MaterialTheme.colorScheme.primary
                            else Color.White
                        )
                    ) {
                        Text(
                            modifier = Modifier.padding(
                                start = 5.dp, end = 5.dp, top = 1.dp, bottom = 1.dp
                            ),
                            text = playerRating,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            ),
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPrev() {
    PlayerOnBenchComponent(
        playerImage = "",
        name = "Endrick",
        number = 9,
        goals = 1,
        assists = 1,
        playerRating = "8.6",
        yellowCards = 1,
        redCards = 1,
        hasSubbed = true,
        hasInvolved = true,
        isCaptain = true,
        time = 58,
        onClick = {}
    )
}