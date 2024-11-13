package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.ui.theme.LightGreen
import com.elTohamy.flushy.presentation.ui.theme.LightRed
import com.elTohamy.flushy.presentation.ui.theme.Orange
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun PlayerOnPitchComponent(
    playerImage: String, name: String, number: Int,
    goals: Int, assists: Int, playerRating: String,
    yellowCards: Int, redCards: Int, hasSubbed: Boolean,
    hasInvolved: Boolean, isCaptain: Boolean, isGoalKeeper: Boolean,
    onClick: () -> Unit
) {
    /*Box(
        modifier = Modifier
            .background(Color.Black.copy(0.5f))
            .width(IntrinsicSize.Max)
            .wrapContentHeight(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .clip(CircleShape),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Image(
                    modifier = Modifier.size(width = 45.dp, height = 45.dp),
                    painter = painterResource(id = playerImage),
                    contentDescription = ""
                )
            }
            Row(
                modifier = Modifier.padding(top = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isCaptain) {
                    Text(
                        modifier = Modifier.padding(end = 2.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .width(7.dp)
                            .height(7.dp)
                            .background(Color.White),
                        text = "C",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 6.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    modifier = Modifier.width(38.dp),
                    text = "$number. $name",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal,
                        fontSize = 6.sp
                    ),
                    maxLines = 2
                )
            }
        }
        if (goals > 0) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(start = 5.dp, end = 5.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier,
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(width = 10.dp, height = 10.dp),
                        painter = painterResource(id = R.drawable.goal_lineups),
                        contentDescription = "Goal"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .width(7.dp)
                        .height(7.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    text = "x$goals",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 5.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
        if (assists > 0) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(start = 5.dp, end = 5.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier,
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(width = 10.dp, height = 10.dp),
                        painter = painterResource(id = R.drawable.assist),
                        contentDescription = "Assist"
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .width(7.dp)
                        .height(7.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    text = "x$assists",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 5.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
        if (yellowCards > 0) {
            Card(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 12.dp, top = 10.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 10.dp, height = 10.dp)
                        .padding(2.dp),
                    painter = painterResource(id = R.drawable.y_card),
                    contentDescription = "Yellow Card"
                )
            }
        }
        if (redCards > 0) {
            Card(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 20.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 10.dp, height = 10.dp)
                        .padding(2.dp),
                    painter = painterResource(id = R.drawable.r_card),
                    contentDescription = "Red Card"
                )
            }
        }
        if (hasSubbed) {
            Card(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 7.dp, bottom = 9.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 10.dp, height = 10.dp)
                        .padding(2.dp),
                    painter = painterResource(id = R.drawable.sub_out),
                    contentDescription = "Sub"
                )
            }
        }
        if (hasInvolved) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 3.dp, bottom = 9.dp)
                    .width(14.dp)
                    .height(7.dp)
                    .clip(CircleShape)
                    .background(Orange),
                text = playerRating,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 6.sp
                ),
                textAlign = TextAlign.Center
            )
        }
    }*/
    val dark = LocalTheme.current.isDark
    val configuration = LocalConfiguration.current
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Ltr
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            val (
                image, nameNum, cap, goalIcon, goalsN, assistIcon,
                assistsN, yellowCardIcon, redCardIcon, rating, subIcon, keeper
            ) = createRefs()
            Card(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 2.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    },
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                onClick = { onClick() }
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(width = 55.dp, height = 55.dp),
                    model = playerImage,
                    contentDescription = "image"
                )
            }
            if (isCaptain) {
                Text(
                    modifier = Modifier
                        .constrainAs(cap) {
                            end.linkTo(nameNum.start, margin = 3.dp)
                            top.linkTo(nameNum.top)
                            bottom.linkTo(nameNum.bottom)
                        }
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White)
                        .size(width = 9.dp, height = 9.dp),
                    text = "C",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 9.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Text(
                modifier = Modifier
                    .constrainAs(nameNum) {
                        top.linkTo(image.bottom, margin = 2.dp)
                        bottom.linkTo(parent.bottom, margin = 1.dp)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
                    .width(
                        if (
                            configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                        ) 80.dp else 70.dp
                    ),
                text = "$number. $name",
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp
                ),
                textAlign = TextAlign.Start,
                maxLines = 2
            )
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
                        modifier = Modifier.size(width = 12.dp, height = 12.dp),
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
                        modifier = Modifier.size(width = 13.dp, height = 13.dp),
                        painter = painterResource(id = R.drawable.assist),
                        contentDescription = "Goal Icon"
                    )
                }
                Card(
                    modifier = Modifier
                        .clip(CircleShape)
                        .constrainAs(assistsN) {
                            start.linkTo(assistIcon.end, margin = 1.dp)
                            top.linkTo(assistIcon.top)
                            bottom.linkTo(assistIcon.bottom)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
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
                            .padding(1.5.dp)
                            .size(width = 10.dp, height = 10.dp),
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
                            .padding(1.5.dp)
                            .size(width = 10.dp, height = 10.dp),
                        painter = painterResource(id = R.drawable.r_card),
                        contentDescription = "Red Card"
                    )
                }
            }
            //Rating
            if (hasInvolved) {
                Card(
                    modifier = Modifier
                        .constrainAs(rating) {
                            end.linkTo(image.end, margin = (-7).dp)
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom)
                        }
                        .clip(RoundedCornerShape(3.dp)),
                    elevation = CardDefaults.cardElevation(4.dp),
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
                        modifier = Modifier.width(20.dp),
                        text = playerRating,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 8.sp
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
            //Sub
            if (hasSubbed) {
                Card(
                    modifier = Modifier
                        .clip(CircleShape)
                        .constrainAs(subIcon) {
                            start.linkTo(image.start, margin = (-6).dp)
                            top.linkTo(image.top)
                            bottom.linkTo(image.bottom)
                        }
                    ,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .padding(0.5.dp)
                            .size(width = 10.dp, height = 10.dp),
                        painter = painterResource(id = R.drawable.sub_out),
                        contentDescription = "Sub"
                    )
                }
            }
            //Goal Keeper
            if (isGoalKeeper) {
                Card(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(
                            width = 0.1.dp, color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .constrainAs(keeper) {
                            start.linkTo(yellowCardIcon.end)
                            end.linkTo(goalIcon.start)
                            top.linkTo(image.top)
                            bottom.linkTo(goalIcon.top)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Image(
                        modifier = Modifier.size(width = 12.dp, height = 12.dp),
                        painter = painterResource(
                            id =
                            if (dark) R.drawable.gloves_b
                            else R.drawable.gloves_w
                        ),
                        contentDescription = "Keeper Icon"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    PlayerOnPitchComponent(
        playerImage = "",
        name = "Alvaro Morata",
        number = 7,
        goals = 3,
        assists = 2,
        playerRating = "8.5",
        yellowCards = 1,
        redCards = 1,
        hasSubbed = true,
        hasInvolved = true,
        isCaptain = true,
        isGoalKeeper = true
    ) {}
}