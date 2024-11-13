package com.elTohamy.flushy.presentation.favouriteOnboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elTohamy.flushy.presentation.components.FavouritePlayerCard

@Composable
fun FavouritePlayersGrid(
    currentPagePlayers: List<String>,
    selectedPlayers: MutableState<Set<String>>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(75.dp),  // Two rows in the horizontal grid
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()  // Adjust height for horizontal grid
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        items(currentPagePlayers) { playerName ->
            val isSelected = playerName in selectedPlayers.value
            FavouritePlayerCard(
                playerName = playerName,
                isSelected = isSelected,
                onClick = {
                    selectedPlayers.value = if (isSelected) {
                        selectedPlayers.value - playerName // Deselect
                    } else {
                        selectedPlayers.value + playerName // Select
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteTournamentsScreenPreview() {
    FavouritePlayersGrid(
        currentPagePlayers = listOf(
            "Messi", "Ronaldo", "Salah", "Neymar",
            "Mbappe", "De Bruyne", "Lewandowski", "Pogba",
            "Mbappe", "De Bruyne", "Lewandowski", "Pogba",
            "Mbappe", "De Bruyne", "Lewandowski", "Pogba",
            "Mbappe", "De Bruyne", "Lewandowski", "Pogba"
        ),
        selectedPlayers = remember { mutableStateOf(setOf()) }
    )
}