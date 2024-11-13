package com.elTohamy.flushy.presentation.favouriteOnboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun FavouritePlayersPager(
    players: List<String>,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val itemsPerPage = 24 // Number of items to display per page
    val pages = (players.size + itemsPerPage - 1) / itemsPerPage // Calculate total pages
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages }
    )
    val selectedPlayers = remember { mutableStateOf(setOf<String>()) }

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(bottom = 16.dp)
        ) {

            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White)
                    .align(Alignment.TopEnd)
                    .clickable {
                    },
                text = "Skip",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 36.dp),
            ) { page ->
                val startIndex = page * itemsPerPage
                val endIndex = minOf(startIndex + itemsPerPage, players.size)
                val currentPagePlayers = players.subList(startIndex, endIndex)

                FavouritePlayersGrid(currentPagePlayers, selectedPlayers)
            }

            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        // Handle previous button click
                        if (pagerState.currentPage > 0) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    enabled = pagerState.currentPage > 0 // Disable if on first page
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Previous"
                    )
                }

                Row(
                    modifier = Modifier
                        .height(10.dp)
                ) {
                    repeat(pages) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.LightGray
                            else Color.LightGray.copy(0.5f)
                        Box(
                            modifier = Modifier
                                .padding(start = 2.dp, end = 2.dp, bottom = 5.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }

                IconButton(
                    onClick = {
                        // Handle next button click
                        if (pagerState.currentPage < pages - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    enabled = pagerState.currentPage < pages - 1 // Disable if on last page
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouritePlayersPagerPreview() {
    val players = listOf(
        "Player 1", "Player 2", "Player 3", "Player 4", "Player 5",
        "Player 6", "Player 7", "Player 8", "Player 9", "Player 10",
        "Player 11", "Player 12", "Player 13", "Player 14", "Player 15",
        "Player 16", "Player 17", "Player 18", "Player 19", "Player 20",
        "Player 21", "Player 22", "Player 23", "Player 24", "Player 25",
        "Player 26", "Player 27", "Player 28", "Player 29", "Player 30",
        "Player 31", "Player 32"
    )
    FavouritePlayersPager(players)
}