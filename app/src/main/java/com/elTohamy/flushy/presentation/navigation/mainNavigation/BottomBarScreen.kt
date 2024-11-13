package com.elTohamy.flushy.presentation.navigation.mainNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.elTohamy.flushy.R

sealed class NavRoutes(
    val route: String
) {
    //Graph Routes
    data object AuthScreen : NavRoutes("AUTH_NAV_GRAPH")

    data object BottomBarScreen : NavRoutes("BOTTOM_BAR_NAV_GRAPH")
}

sealed class AuthScreen(
    val route: String
) {
    data object StartScreen : AuthScreen("start_screen")

    data object LoginScreen : AuthScreen("login_screen")

}

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    data object LiveGames : BottomBarScreen(
        route = "live_games",
        title = R.string.games,
        icon = Icons.Outlined.SportsSoccer,
        selectedIcon = Icons.Filled.SportsSoccer
    )

    data object LoginScreen : BottomBarScreen(
        route = "log_in",
        title = R.string.signIn,
        icon = Icons.AutoMirrored.Outlined.Login,
        selectedIcon = Icons.AutoMirrored.Filled.Login
    )

    data object Favourite : BottomBarScreen(
        route = "favourite",
        title = R.string.favourite,
        icon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite
    )

    data object Search : BottomBarScreen(
        route = "news",
        title = R.string.news,
        icon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search
    )

    data object Settings : BottomBarScreen(
        route = "settings",
        title = R.string.settings,
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings
    )
}