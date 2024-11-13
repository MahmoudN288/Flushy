package com.elTohamy.flushy.components.searchComponents.searchComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elTohamy.flushy.components.searchComponents.searchComponents.components.FavouriteLazyColumn

@Composable
fun FavouriteScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .systemBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(
                        modifier = Modifier,
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Arrow Back"
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Row{
                        //SearchBar()
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                SearchSuggestionsHeaderComponent()
            }
        }
    ) { paddingValues ->
        LazyColumnSetup(paddingValues = paddingValues)
    }
}

@Composable
fun LazyColumnSetup(paddingValues: PaddingValues) {
    FavouriteLazyColumn(paddingValues = paddingValues)
}

@Preview(showBackground = true)
@Composable
fun FavouriteScreenPreview() {
    FavouriteScreen(navController = rememberNavController())
}