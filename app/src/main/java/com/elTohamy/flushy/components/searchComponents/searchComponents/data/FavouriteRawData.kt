package com.elTohamy.flushy.components.searchComponents.searchComponents.data

import androidx.compose.runtime.mutableStateOf
import com.elTohamy.flushy.R

object FavouriteRawData {
    var items = arrayListOf(
        FavouriteData("FIFA World Cup", "FIFA World Cup", R.drawable.goal, mutableStateOf(false)),
        FavouriteData("Euro", "FIFA World Cup", R.drawable.y_card, mutableStateOf(false)),
        FavouriteData("Premier League", "FIFA World Cup", R.drawable.r_card, mutableStateOf(false)),
        FavouriteData("La Liga", "FIFA World Cup", R.drawable.goal, mutableStateOf(false)),
        FavouriteData("Serie A", "FIFA World Cup", R.drawable.y_card, mutableStateOf(false)),
        FavouriteData("Ligue 1", "FIFA World Cup", R.drawable.r_card, mutableStateOf(false)),
        FavouriteData("Bundesliga", "FIFA World Cup", R.drawable.ic_circle_checked, mutableStateOf(false))
    )
}