package com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissDirection
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.domain.model.FavouriteLeagues
import com.elTohamy.flushy.domain.network.NetworkStatus
import com.elTohamy.flushy.domain.response.FavouriteLeaguesResponse
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.viewModel.LeaguesViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.Constants
import com.elTohamy.flushy.utils.LocalTheme
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavouriteTournamentScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val flushyAuthUiClient by lazy {
        AuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = mainViewModel.lazyListStateFavourite.value)
    val scope = rememberCoroutineScope()
    mainViewModel.updateLazyListStateFavourite(remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value)
    Scaffold(
        topBar = {},
        floatingActionButton = {
            if (remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }.value != 0) {
                FloatingActionButton(
                    shape = FloatingActionButtonDefaults.smallShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    elevation = FloatingActionButtonDefaults.elevation(4.dp),
                    onClick = {
                        scope.launch { lazyListState.animateScrollToItem(0) }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(width = 25.dp, height = 25.dp),
                        imageVector = Icons.Default.KeyboardDoubleArrowUp,
                        contentDescription = "Go To First Index",
                        tint = MaterialTheme.customColorsPalette.blackToWhite
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            FavouriteTournamentsList(flushyAuthUiClient = flushyAuthUiClient, lazyListState = lazyListState)
        }
    }
}

@Composable
fun GetFavouriteLeaguesFromFirestore(
    flushyAuthUiClient: AuthUiClient,
    leaguesViewModel: LeaguesViewModel = hiltViewModel()
) {
    val db = FirebaseFirestore.getInstance()
    db.collection("favourite").document(flushyAuthUiClient.getSignedInUser()?.userId!!)
        .collection("leagues").get().addOnCompleteListener { task->
            if (task.isSuccessful) {
                val result = task.result
                val documents = result.documents
                if (documents.size > 0) {
                    leaguesViewModel.getFavouriteLeagues(flushyAuthUiClient.getSignedInUser()?.userId!!)
                }
            } else {
                Log.d(Constants.TAG, task.exception!!.message.toString())
            }
        }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouriteTournamentsList(
    flushyAuthUiClient: AuthUiClient,
    lazyListState: LazyListState,
    leaguesViewModel: LeaguesViewModel = hiltViewModel()
) {
    GetFavouriteLeaguesFromFirestore(flushyAuthUiClient = flushyAuthUiClient)
    val favouriteItems = remember { mutableStateListOf<FavouriteLeagues>() }
    when (val responseItem = leaguesViewModel.favouriteLeaguesResponse) {
        is FavouriteLeaguesResponse.Loading -> {}
        is FavouriteLeaguesResponse.LeaguesFailure -> {}
        is FavouriteLeaguesResponse.LeaguesSuccess -> {
            responseItem.data.forEach {
                favouriteItems.add(it)
            }
        }
    }
    val swipeRefreshState = rememberPullRefreshState(
        true, onRefresh = {
            25.dp
        }
    )

    LazyColumn(
        state = lazyListState,
        modifier = Modifier
            .pullRefresh(
                state = swipeRefreshState,
                enabled = true
            )
    ) {
        items(favouriteItems) { item ->
            DismissItemContainer(
                favouriteLeagues = item,
                flushyAuthUiClient = flushyAuthUiClient
            )
        }
    }
}

@Composable
fun FavouriteTournamentItem(favouriteLeagues: FavouriteLeagues) {
    val dark = LocalTheme.current.isDark
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {  }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 5.dp, bottom = 5.dp, start = 3.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.size(30.dp),
                    model = favouriteLeagues.logo,
                    contentDescription = "Tournament Logo"
                )
                Text(
                    text = favouriteLeagues.name!!,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    maxLines = 1
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.swipeToDelete),
                    style = TextStyle(
                        color = if (dark) Color.LightGray else Color.Gray,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector =
                    if (LocalLayoutDirection.current == LayoutDirection.Ltr) {
                        Icons.Default.KeyboardDoubleArrowRight
                    } else {
                        Icons.Default.KeyboardDoubleArrowLeft
                    },
                    contentDescription = "Swipe Icon",
                    tint = MaterialTheme.customColorsPalette.blackToWhite
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color(0xFFFF1744)
        DismissDirection.EndToStart -> Color(0xFF1DE9B6)
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (direction == DismissDirection.StartToEnd) Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart) Icon(
            // make sure add baseline_archive_24 resource to drawable folder
            imageVector = Icons.Default.Archive,
            contentDescription = "Archive"
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissItemContainer(
    favouriteLeagues: FavouriteLeagues,
    flushyAuthUiClient: AuthUiClient,
    leaguesViewModel: LeaguesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val state = leaguesViewModel.networkStatus.collectAsState().value
    val dismissState = rememberDismissState(
        confirmStateChange = {
            when (it) {
                DismissValue.DismissedToEnd -> {
                    when (state) {
                        NetworkStatus.Connected -> {
                            leaguesViewModel.removeFavouriteLeague(
                                favouriteLeagues.id!!,
                                flushyAuthUiClient.getSignedInUser()?.userId!!
                            )
                            show = false
                            true
                        }
                        NetworkStatus.Disconnected -> { false }
                        NetworkStatus.Unknown -> { false }
                    }
                }
                else -> { false }
            }
        }
    )
    AnimatedVisibility(
        show,exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DismissBackground(dismissState)
            },
            dismissContent = {
                FavouriteTournamentItem(favouriteLeagues = favouriteLeagues)
            }
        )
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteTournamentItemPreview() {
}