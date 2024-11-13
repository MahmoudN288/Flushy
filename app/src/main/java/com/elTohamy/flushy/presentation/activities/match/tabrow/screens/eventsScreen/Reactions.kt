package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.eventsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elTohamy.flushy.R

@Composable
fun Reactions(loveCount: Int, partyCount: Int, coolCount: Int, sadCount: Int, angryCount: Int) {
    ConstraintLayout {
        val (count, love, party, cool, sad, angry) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(count) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            text = loveCount.toString()
        )
        if (
            loveCount > 0 && partyCount == 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
        } else if (
            loveCount > 0 && partyCount == 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
        } else if (
            loveCount > 0 && partyCount == 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount > 0 && partyCount == 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount > 0 && partyCount == 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount == 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount > 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount == 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount > 0 && sadCount == 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount == 0 && partyCount == 0 &&
            coolCount == 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        } else if (
            loveCount > 0 && partyCount > 0 &&
            coolCount > 0 && sadCount > 0 &&
            angryCount > 0
        ) {
            Image(
                modifier = Modifier
                    .constrainAs(love) {
                        start.linkTo(count.end, margin = 3.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.love),
                contentDescription = "Love"
            )
            Image(
                modifier = Modifier
                    .constrainAs(party) {
                        start.linkTo(love.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.party),
                contentDescription = "Party"
            )
            Image(
                modifier = Modifier
                    .constrainAs(cool) {
                        start.linkTo(party.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.cool),
                contentDescription = "Cool"
            )
            Image(
                modifier = Modifier
                    .constrainAs(sad) {
                        start.linkTo(cool.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.sad),
                contentDescription = "Sad"
            )
            Image(
                modifier = Modifier
                    .constrainAs(angry) {
                        start.linkTo(sad.end, margin = (-3).dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(15.dp),
                painter = painterResource(id = R.drawable.angry),
                contentDescription = "Angry"
            )
        }
    }
}

/*
DropdownMenu(
            expanded = expanded,
            offset = DpOffset(xDp, -cardHeightDp + yDp),
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .weight(1f)
                                .size(25.dp),
                            painter = when (eventsItem.type) {
                                "Card" -> {
                                    when (eventsItem.detail) {
                                        "Yellow Card" -> {
                                            painterResource(id = R.drawable.y_card)
                                        }

                                        "Red Card" -> {
                                            painterResource(id = R.drawable.r_card)
                                        }

                                        else -> {
                                            painterResource(id = R.drawable.y_card)
                                        }
                                    }
                                }

                                "subst" -> painterResource(id = R.drawable.sub)
                                "Goal" -> painterResource(
                                    id = when (eventsItem.detail) {
                                        "Normal Goal" -> {
                                            R.drawable.goal_lineups
                                        }

                                        "Penalty" -> {
                                            if (dark) R.drawable.scored_pen_dark
                                            else R.drawable.scored_pen_light
                                        }

                                        "Missed Penalty" -> {
                                            R.drawable.missed_pen
                                        }

                                        else -> {
                                            R.drawable.goal_lineups
                                        }
                                    }
                                )

                                "Var" -> painterResource(id = R.drawable.`var`)
                                else -> painterResource(id = R.drawable.ic_launcher_foreground)
                            },
                            contentDescription = "Event Icon"
                        )
                        Text(
                            modifier = Modifier,
                            text = eventsItem.player.name!!,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            ),
                            maxLines = 1
                        )
                    }
                }
            )

            DropdownMenuItem(
                onClick = {
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id.toString())
                        .collection(
                            (
                                ((eventsItem.player.id)!! *
                                 (eventsItem.team.id!!)) /
                                eventsItem.time.elapsed!!
                            ).toString()
                        )
                        .document(authUiClient.getSignedInUser()?.userId!!)
                        .get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                val data = result.data
                                if (result.exists()) {
                                    val react = data!!["react"] as String
                                    if (react == "love") {
                                        reactsViewModel.deleteReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!
                                        )
                                    } else {
                                        reactsViewModel.updateReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!,
                                            "love"
                                        )
                                    }
                                } else {
                                    reactsViewModel.addReact(
                                        currentIndex,
                                        fixtureByIdResponseItem.fixture.id.toString(),
                                        (
                                           ((eventsItem.player.id) *
                                            (eventsItem.team.id)) /
                                           eventsItem.time.elapsed
                                        ).toString(),
                                        authUiClient.getSignedInUser()?.userId!!,
                                        "love"
                                    )
                                }
                            } else {
                                Log.d("TAG", task.exception.toString())
                            }
                        }
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id!!.toString())
                        .collection(customId)
                        .document(authUiClient.getSignedInUser()?.userId!!).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                if (result.exists()) {
                                    val data = result.data
                                    val eventId = (data!!["index"] as Long).toInt()
                                    if (currentIndex == eventId) {
                                        isCurrentEvent.value = true
                                    }
                                }
                            }
                        }
                    isUpdated.value = true
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "I Love It",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.love),
                            contentDescription = ""
                        )
                    }
                }
            )

            DropdownMenuItem(
                onClick = {
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id.toString())
                        .collection(
                            (
                               ((eventsItem.player.id)!! *
                                (eventsItem.team.id!!)) /
                               eventsItem.time.elapsed!!
                            ).toString()
                        )
                        .document(authUiClient.getSignedInUser()?.userId!!)
                        .get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                val data = result.data
                                if (result.exists()) {
                                    val react = data!!["react"] as String
                                    if (react == "party") {
                                        reactsViewModel.deleteReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!
                                        )
                                    } else {
                                        reactsViewModel.updateReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!,
                                            "party"
                                        )
                                    }
                                } else {
                                    reactsViewModel.addReact(
                                        currentIndex,
                                        fixtureByIdResponseItem.fixture.id.toString(),
                                        (
                                           ((eventsItem.player.id) *
                                            (eventsItem.team.id)) /
                                           eventsItem.time.elapsed
                                        ).toString(),
                                        authUiClient.getSignedInUser()?.userId!!,
                                        "party"
                                    )
                                }
                            } else {
                                Log.d("TAG", task.exception.toString())
                            }
                        }
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id!!.toString())
                        .collection(customId)
                        .document(authUiClient.getSignedInUser()?.userId!!).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                if (result.exists()) {
                                    val data = result.data
                                    val eventId = (data!!["index"] as Long).toInt()
                                    if (currentIndex == eventId) {
                                        isCurrentEvent.value = true
                                    }
                                }
                            }
                        }
                    isUpdated.value = true
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Let's Party",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.party),
                            contentDescription = ""
                        )
                    }
                }
            )

            DropdownMenuItem(
                onClick = {
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id.toString())
                        .collection(
                            (
                               ((eventsItem.player.id)!! *
                                (eventsItem.team.id!!)) /
                               eventsItem.time.elapsed!!
                            ).toString()
                        )
                        .document(authUiClient.getSignedInUser()?.userId!!)
                        .get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                val data = result.data
                                if (result.exists()) {
                                    val react = data!!["react"] as String
                                    if (react == "cool") {
                                        reactsViewModel.deleteReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!
                                        )
                                    } else {
                                        reactsViewModel.updateReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!,
                                            "cool"
                                        )
                                    }
                                } else {
                                    reactsViewModel.addReact(
                                        currentIndex,
                                        fixtureByIdResponseItem.fixture.id.toString(),
                                        (
                                           ((eventsItem.player.id) *
                                            (eventsItem.team.id)) /
                                           eventsItem.time.elapsed
                                        ).toString(),
                                        authUiClient.getSignedInUser()?.userId!!,
                                        "cool"
                                    )
                                }
                            } else {
                                Log.d("TAG", task.exception.toString())
                            }
                        }
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id!!.toString())
                        .collection(customId)
                        .document(authUiClient.getSignedInUser()?.userId!!).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                if (result.exists()) {
                                    val data = result.data
                                    val eventId = (data!!["index"] as Long).toInt()
                                    if (currentIndex == eventId) {
                                        isCurrentEvent.value = true
                                    }
                                }
                            }
                        }
                    isUpdated.value = true
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "It's Cool",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.cool),
                            contentDescription = ""
                        )
                    }
                }
            )

            DropdownMenuItem(
                onClick = {
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id.toString())
                        .collection(
                            (
                               ((eventsItem.player.id)!! *
                                (eventsItem.team.id!!)) /
                               eventsItem.time.elapsed!!
                            ).toString()
                        )
                        .document(authUiClient.getSignedInUser()?.userId!!)
                        .get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                val data = result.data
                                if (result.exists()) {
                                    val react = data!!["react"] as String
                                    if (react == "sad") {
                                        reactsViewModel.deleteReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!
                                        )
                                    } else {
                                        reactsViewModel.updateReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!,
                                            "sad"
                                        )
                                    }
                                } else {
                                    reactsViewModel.addReact(
                                        currentIndex,
                                        fixtureByIdResponseItem.fixture.id.toString(),
                                        (
                                           ((eventsItem.player.id) *
                                            (eventsItem.team.id)) /
                                           eventsItem.time.elapsed
                                        ).toString(),
                                        authUiClient.getSignedInUser()?.userId!!,
                                        "sad"
                                    )
                                }
                            } else {
                                Log.d("TAG", task.exception.toString())
                            }
                        }
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id!!.toString())
                        .collection(customId)
                        .document(authUiClient.getSignedInUser()?.userId!!).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                if (result.exists()) {
                                    val data = result.data
                                    val eventId = (data!!["index"] as Long).toInt()
                                    if (currentIndex == eventId) {
                                        isCurrentEvent.value = true
                                    }
                                }
                            }
                        }
                    isUpdated.value = true
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "I'm So Sad",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.sad),
                            contentDescription = ""
                        )
                    }
                }
            )

            DropdownMenuItem(
                onClick = {
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id.toString())
                        .collection(
                            (
                               ((eventsItem.player.id)!! *
                                (eventsItem.team.id!!)) /
                               eventsItem.time.elapsed!!
                            ).toString()
                        )
                        .document(authUiClient.getSignedInUser()?.userId!!)
                        .get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                val data = result.data
                                if (result.exists()) {
                                    val react = data!!["react"] as String
                                    if (react == "angry") {
                                        reactsViewModel.deleteReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!
                                        )
                                    } else {
                                        reactsViewModel.updateReact(
                                            fixtureByIdResponseItem.fixture.id.toString(),
                                            (
                                               ((eventsItem.player.id) *
                                                (eventsItem.team.id)) /
                                               eventsItem.time.elapsed
                                            ).toString(),
                                            authUiClient.getSignedInUser()?.userId!!,
                                            "angry"
                                        )
                                    }
                                } else {
                                    reactsViewModel.addReact(
                                        currentIndex,
                                        fixtureByIdResponseItem.fixture.id.toString(),
                                        (
                                           ((eventsItem.player.id) *
                                            (eventsItem.team.id)) /
                                           eventsItem.time.elapsed
                                        ).toString(),
                                        authUiClient.getSignedInUser()?.userId!!,
                                        "angry"
                                    )
                                }
                            } else {
                                Log.d("TAG", task.exception.toString())
                            }
                        }
                    firestore.collection("reactions")
                        .document(fixtureByIdResponseItem.fixture.id!!.toString())
                        .collection(customId)
                        .document(authUiClient.getSignedInUser()?.userId!!).get()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                if (result.exists()) {
                                    val data = result.data
                                    val eventId = (data!!["index"] as Long).toInt()
                                    if (currentIndex == eventId) {
                                        isCurrentEvent.value = true
                                    }
                                }
                            }
                        }
                    isUpdated.value = true
                    expanded = false
                },
                interactionSource = remember { MutableInteractionSource() },
                text = {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "I'm Angry",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.angry),
                            contentDescription = ""
                        )
                    }
                }
            )
        }
* */

/*
val isUpdated = remember { mutableStateOf(false) }
    if (isUpdated.value) {
        GetReacts(
            eventsItem = eventsItem,
            fixtureByIdResponseItem = fixtureByIdResponseItem,
        )
    }
* */

/*
@Composable
fun GetReacts(
    reactsViewModel: ReactsViewModel = hiltViewModel(),
    eventsItem: EventsItem,
    fixtureByIdResponseItem: MatchByIdResponseItem
) {
    //All
    reactsViewModel.getReacts(
        fixtureId = fixtureByIdResponseItem.fixture!!.id.toString(),
        customId =
        (((eventsItem.player!!.id)!! *
                (eventsItem.team!!.id!!)) /
                eventsItem.time!!.elapsed!!).toString()
    )
    //Love
    reactsViewModel.getLoveReacts(
        fixtureId = fixtureByIdResponseItem.fixture.id.toString(),
        customId =
        (((eventsItem.player.id)!! *
                (eventsItem.team.id!!)) /
                eventsItem.time.elapsed!!).toString(),
        react = "love"
    )
    //Party
    reactsViewModel.getPartyReacts(
        fixtureId = fixtureByIdResponseItem.fixture.id.toString(),
        customId =
        (((eventsItem.player.id) *
                (eventsItem.team.id)) /
                eventsItem.time.elapsed).toString(),
        react = "party"
    )
    //Cool
    reactsViewModel.getCoolReacts(
        fixtureId = fixtureByIdResponseItem.fixture.id.toString(),
        customId =
        (((eventsItem.player.id) *
                (eventsItem.team.id)) /
                eventsItem.time.elapsed).toString(),
        react = "cool"
    )
    //Sad
    reactsViewModel.getSadReacts(
        fixtureId = fixtureByIdResponseItem.fixture.id.toString(),
        customId =
        (((eventsItem.player.id) *
                (eventsItem.team.id)) /
                eventsItem.time.elapsed).toString(),
        react = "sad"
    )
    //Angry
    reactsViewModel.getAngryReacts(
        fixtureId = fixtureByIdResponseItem.fixture.id.toString(),
        customId =
        (((eventsItem.player.id) *
                (eventsItem.team.id)) /
                eventsItem.time.elapsed).toString(),
        react = "angry"
    )
    //
    val loveCount = remember { mutableIntStateOf(0) }
    val partyCount = remember { mutableIntStateOf(0) }
    val coolCount = remember { mutableIntStateOf(0) }
    val sadCount = remember { mutableIntStateOf(0) }
    val angryCount = remember { mutableIntStateOf(0) }
    reactsViewModel.loveReactsResponse.apply {
        when (this) {
            is EmojiResponse.Loading -> {}
            is EmojiResponse.ReactFailure -> {}
            is EmojiResponse.ReactSuccess -> {
                loveCount.intValue = this.data.size
            }
        }
    }
    reactsViewModel.partyReactsResponse.apply {
        when (this) {
            is EmojiResponse.Loading -> {}
            is EmojiResponse.ReactFailure -> {}
            is EmojiResponse.ReactSuccess -> {
                partyCount.intValue = this.data.size
            }
        }
    }
    reactsViewModel.coolReactsResponse.apply {
        when (this) {
            is EmojiResponse.Loading -> {}
            is EmojiResponse.ReactFailure -> {}
            is EmojiResponse.ReactSuccess -> {
                coolCount.intValue = this.data.size
            }
        }
    }
    reactsViewModel.sadReactsResponse.apply {
        when (this) {
            is EmojiResponse.Loading -> {}
            is EmojiResponse.ReactFailure -> {}
            is EmojiResponse.ReactSuccess -> {
                sadCount.intValue = this.data.size
            }
        }
    }
    reactsViewModel.angryReactsResponse.apply {
        when (this) {
            is EmojiResponse.Loading -> {}
            is EmojiResponse.ReactFailure -> {}
            is EmojiResponse.ReactSuccess -> {
                angryCount.intValue = this.data.size
            }
        }
    }
}
* */

/*
@Composable
fun Pulsating(pulseFraction: Float = 1.2f, content: @Composable () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = pulseFraction,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(modifier = Modifier.scale(scale)) {
        content()
    }
}
* */

/*
@HiltViewModel
class ReactsViewModel @Inject constructor(private val emojisUseCases: EmojisUseCases): ViewModel() {
    var reactsResponse by mutableStateOf<ReactResponse>(EmojiResponse.Loading)
        private set
    var loveReactsResponse by mutableStateOf<LoveReactResponse>(EmojiResponse.Loading)
        private set
    var partyReactsResponse by mutableStateOf<PartyReactResponse>(EmojiResponse.Loading)
        private set
    var coolReactsResponse by mutableStateOf<CoolReactResponse>(EmojiResponse.Loading)
        private set
    var sadReactsResponse by mutableStateOf<SadReactResponse>(EmojiResponse.Loading)
        private set
    var angryReactsResponse by mutableStateOf<AngryReactResponse>(EmojiResponse.Loading)
        private set
    var addReactResponse by mutableStateOf<AddReactResponse>(EmojiResponse.ReactSuccess(false))
        private set
    var updateReactResponse by mutableStateOf<UpdateReactResponse>(EmojiResponse.ReactSuccess(false))
        private set
    var deleteReactResponse by mutableStateOf<DeleteReactResponse>(EmojiResponse.ReactSuccess(false))
        private set

    fun getReacts(fixtureId: String, customId: String) = viewModelScope.launch {
        emojisUseCases.getReact(fixtureId, customId).collect { response ->
            reactsResponse = response
        }
    }

    fun getLoveReacts(fixtureId: String, customId: String, react: String) = viewModelScope.launch {
        emojisUseCases.getLoveReacts(fixtureId, customId, react).collect { response ->
            loveReactsResponse = response
        }
    }

    fun getPartyReacts(fixtureId: String, customId: String, react: String) = viewModelScope.launch {
        emojisUseCases.getPartyReact(fixtureId, customId, react).collect { response ->
            partyReactsResponse = response
        }
    }

    fun getCoolReacts(fixtureId: String, customId: String, react: String) = viewModelScope.launch {
        emojisUseCases.getCoolReact(fixtureId, customId, react).collect { response ->
            coolReactsResponse = response
        }
    }

    fun getSadReacts(fixtureId: String, customId: String, react: String) = viewModelScope.launch {
        emojisUseCases.getSadReact(fixtureId, customId, react).collect { response ->
            sadReactsResponse = response
        }
    }

    fun getAngryReacts(fixtureId: String, customId: String, react: String) = viewModelScope.launch {
        emojisUseCases.getAngryReact(fixtureId, customId, react).collect { response ->
            angryReactsResponse = response
        }
    }

    fun addReact(index: Int, fixtureId: String, customId: String, userId: String, react: String) =
        viewModelScope.launch {
            addReactResponse = EmojiResponse.Loading
            addReactResponse = emojisUseCases.addReact(index, fixtureId, customId, userId, react)
        }

    fun updateReact(fixtureId: String, customId: String, userId: String, react: String) =
        viewModelScope.launch {
            updateReactResponse = EmojiResponse.Loading
            updateReactResponse = emojisUseCases.updateReact(fixtureId, customId, userId, react)
        }

    fun deleteReact(fixtureId: String, customId: String, userId: String) = viewModelScope.launch {
        deleteReactResponse = EmojiResponse.Loading
        deleteReactResponse = emojisUseCases.deleteReact(fixtureId, customId, userId)
    }
}
* */