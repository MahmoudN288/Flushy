package com.elTohamy.flushy.presentation.navigation.mainNavigation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.elTohamy.flushy.data.state.SignInState
import com.elTohamy.flushy.presentation.bottomNavBar.SignInScreen
import com.elTohamy.flushy.presentation.navigation.mainNavigation.BottomBarScreen

fun NavGraphBuilder.AuthNav(
    navController: NavHostController,
    state: SignInState, onSignInClick: ()-> Unit
) {
    navigation(
        startDestination = BottomBarScreen.LoginScreen.route,
        route = Graph.AUTH
    ){
        composable(route = BottomBarScreen.LoginScreen.route){
            SignInScreen(navHostController = navController, state = state, onSignInClick = onSignInClick)
        }
    }
}