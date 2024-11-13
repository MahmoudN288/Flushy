package com.elTohamy.flushy.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette

@Composable
fun FavouriteTournamentCard(
    isSelected: Boolean = false
) {
    Card(
        modifier = Modifier.padding(8.dp)
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(0.4f)
            else MaterialTheme.customColorsPalette.mainCardContainer
        ),
        elevation = CardDefaults.cardElevation(1.5.dp),
        onClick = {}
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(R.drawable.la_liga),
                    contentDescription = "Tournament Image"
                )
                Text(
                    modifier = Modifier.width(80.dp),
                    text = "La Liga",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FavouritePlayerCard(
    playerName: String = "",
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .wrapContentHeight()
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.padding(3.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(0.4f)
                    else MaterialTheme.customColorsPalette.mainCardContainer
                ),
                elevation = CardDefaults.cardElevation(1.5.dp),
                onClick = {}
            ) {
                Image(
                    modifier = Modifier.size(80.dp)
                        .background(Color.Transparent),
                    painter = painterResource(R.drawable.person_min),
                    contentDescription = "Tournament Image"
                )
            }
            if (isSelected) {
                Image(
                    modifier = Modifier.align(Alignment.TopStart)
                        .padding(start = 10.dp, top = 5.dp)
                        .size(15.dp),
                    painter = painterResource(R.drawable.ic_circle_checked),
                    contentDescription = "Favourite Icon"
                )
            }
        }
        Text(
            modifier = Modifier.width(80.dp),
            text = playerName,
            fontSize = 11.sp,
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavouritePlayerCardPreview() {
    FavouritePlayerCard(
        playerName = "Messi",
        isSelected = false
    ) {}
}

@Preview(showBackground = true)
@Composable
fun FavouriteTournamentCardPreview() {
    FavouriteTournamentCard()
}