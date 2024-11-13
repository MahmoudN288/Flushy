package com.elTohamy.flushy.presentation.bottomNavBar.allTournaments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.components.TournamentsLazyColumn
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette

@Composable
fun FavouriteTournamentsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            MaterialTheme.customColorsPalette.matchDetailsCardContainer
                        )
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(60.dp),
                        painter = painterResource(
                            id = R.drawable.flushy_logo_t
                        ),
                        contentDescription = ""
                    )
                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = " - ",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.favChamp),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            TournamentsLazyColumn()
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
fun TeamsScreenPreview() {
    FavouriteTournamentsScreen(navController = rememberNavController())
}