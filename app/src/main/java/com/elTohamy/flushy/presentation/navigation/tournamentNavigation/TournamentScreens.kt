package com.elTohamy.flushy.presentation.navigation.tournamentNavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TournamentScreens(
    val route: String, val title: String,
    val selectedIcon: ImageVector, val unselectedIcon: ImageVector,
    val screen: @Composable ()-> Unit
)