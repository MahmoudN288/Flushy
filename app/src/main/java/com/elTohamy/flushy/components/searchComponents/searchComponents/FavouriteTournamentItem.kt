package com.elTohamy.flushy.components.searchComponents.searchComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.wear.compose.material3.Text
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.Dimens

@Composable
fun FavouriteTournamentItem(
    name: String,
    image: Painter,
    isShown: Boolean = false,
    borderColor: Color =
        if (isShown) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.primary.copy(0.2f),
    borderWidth: Dp =
        if (isShown) 2.dp else 2.dp,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)
            .shadow(elevation = Dimens.dp3, shape = RoundedCornerShape(Dimens.dp16))
            .background(color = Color.White, shape = RoundedCornerShape(Dimens.dp16))
            .clickable {
                onClick()
            }
    ) {
        Card(
            modifier = Modifier
                .border(width = borderWidth,
                    color = borderColor,
                    shape = RoundedCornerShape(Dimens.dp16)
                )
        ) {
            Column(
                modifier = Modifier
                    .size(width = 120.dp, height = 120.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(Dimens.dp16))
                    .background(color = MaterialTheme.customColorsPalette.matchCard),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = image,
                    contentDescription = "Tournament Description"
                )
                Text(
                    text = name,
                    fontFamily = FontFamily.Cursive,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.matchCardTextLive,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun FavouriteTournamentItemPreview() {
}