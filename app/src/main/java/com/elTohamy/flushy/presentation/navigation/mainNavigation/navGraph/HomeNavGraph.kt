package com.elTohamy.flushy.presentation.navigation.mainNavigation.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.data.repos.firebase.UserData
import com.elTohamy.flushy.presentation.bottomNavBar.NewsScreen
import com.elTohamy.flushy.presentation.bottomNavBar.SettingsScreen
import com.elTohamy.flushy.presentation.bottomNavBar.liveGamesScreen.LiveGamesScreen
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.FavouriteTournamentsScreen
import com.elTohamy.flushy.presentation.navigation.mainNavigation.BottomBarScreen
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    selectedTheme: AppTheme,
    userData: UserData?,
    authUiClient: AuthUiClient,
    signOut: ()-> Unit,
    onItemSelected: (AppTheme) -> Unit,
    locale: Locale?, country: String?, q: String?
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.LiveGames.route
    ) {
        composable(route = BottomBarScreen.LiveGames.route) {
            LiveGamesScreen(navController = navController, authUiClient = authUiClient)
        }
        composable(route = BottomBarScreen.Favourite.route) {
            FavouriteTournamentsScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Search.route) {
            NewsScreen(navController = navController, locale, country, q)
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen(navController = navController, userData, signOut, selectedTheme, onItemSelected)
        }
        //detailsNavGraph(navController = navController)
    }
}