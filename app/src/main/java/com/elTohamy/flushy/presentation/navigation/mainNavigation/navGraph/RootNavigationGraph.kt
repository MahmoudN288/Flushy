package com.elTohamy.flushy.presentation.navigation.mainNavigation.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.data.state.SignInState
import com.elTohamy.flushy.data.repos.firebase.UserData
import com.elTohamy.flushy.presentation.activities.BottomNav
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    selectedTheme: AppTheme,
    authUiClient: AuthUiClient,
    onItemSelected: (AppTheme) -> Unit,
    userData: UserData?,
    signOut: ()-> Unit,
    locale: Locale?, country: String?, q: String?,
    state: SignInState, onSignInClick: ()-> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        AuthNav(navController, state, onSignInClick)
        composable(route = Graph.HOME) {
            BottomNav(selectedTheme, userData, authUiClient, signOut, onItemSelected, locale, country, q)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val HOME = "home_graph"
}