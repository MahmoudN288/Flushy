package com.elTohamy.flushy.presentation.activities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.data.repos.firebase.UserData
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.navigation.mainNavigation.BottomBarScreen
import com.elTohamy.flushy.presentation.navigation.mainNavigation.navGraph.HomeNavGraph
import com.elTohamy.flushy.presentation.ui.theme.DarkSystemBars
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.utils.LocalTheme
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNav(
    selectedTheme: AppTheme,
    userData: UserData?,
    authUiClient: AuthUiClient,
    signOut: ()-> Unit,
    onItemSelected: (AppTheme) -> Unit,
    locale: Locale?, country: String?, q: String?,
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {},
        bottomBar = {
            BottomBar(
                modifier = Modifier.navigationBarsPadding(),
                navController = navController
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            HomeNavGraph(
                navController = navController,
                selectedTheme = selectedTheme,
                onItemSelected = onItemSelected,
                userData = userData,
                authUiClient = authUiClient,
                signOut = { signOut() },
                locale = locale, country = country, q = q
            )
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier,
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.LiveGames,
        BottomBarScreen.Favourite,
        BottomBarScreen.Search,
        BottomBarScreen.Settings
    )

    val dark = LocalTheme.current.isDark
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier
            .background(if (dark) DarkSystemBars else Color.White)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) MaterialTheme.colorScheme.primary
        else Color.Transparent

    val contentColor = MaterialTheme.customColorsPalette.blackToWhite

    Box(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {


            //* if menu title : Report means we will show badge
            if(screen.title == R.string.onBoardingTitle1){ // with badge
                BadgedBox(badge = { Badge { Text("6") } }) {
                    Icon(
                        imageVector = if (selected) screen.selectedIcon else screen.icon,
                        contentDescription = "icon",
                        tint = if (selected) Color.White else contentColor
                    )
                }

            }
            else{

                Icon(
                    imageVector = if (selected) screen.selectedIcon else screen.icon,
                    contentDescription = "icon",
                    tint = if (selected) Color.White else contentColor
                )

            }

            AnimatedVisibility(visible = selected) {
                Text(
                    text = stringResource(id = screen.title),
                    color = Color.White
                )
            }
        }
    }
}