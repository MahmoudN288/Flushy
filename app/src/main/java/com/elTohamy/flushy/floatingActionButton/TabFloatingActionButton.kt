package com.elTohamy.flushy.floatingActionButton

class TabFloatingActionButton {
}


/*
* val sections = listOf("Recent", "Suggested", "Teams")
        val painterA = painterResource(id = R.drawable.fwc_usa)
        val painterB = painterResource(id = R.drawable.euro)
        val painterC = painterResource(id = R.drawable.p_league)
        val painterD = painterResource(id = R.drawable.la_liga)
        val painterE = painterResource(id = R.drawable.serie_a)
        val painterF = painterResource(id = R.drawable.ligue1)
        val painterG = painterResource(id = R.drawable.bundes_ligue)
        val teamsList = remember {
            mutableListOf(
                SearchedChampionshipsData("FIFA World Cup", painterA),
                SearchedChampionshipsData("Euro", painterB),
                SearchedChampionshipsData("Premier League", painterC),
                SearchedChampionshipsData("La Liga", painterD),
                SearchedChampionshipsData("Serie A", painterE),
                SearchedChampionshipsData("Ligue 1", painterF),
                SearchedChampionshipsData("Bundesliga", painterG)
            )
        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            sections.forEach { section ->
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when(section) {
                            sections[0] -> {
                                Icon(
                                    modifier = Modifier
                                        .padding(7.dp),
                                    imageVector = Icons.Default.History,
                                    contentDescription = "Recent")
                            }
                            sections[1] -> {
                                Icon(
                                    modifier = Modifier
                                        .padding(7.dp),
                                    imageVector = Icons.Default.LocalOffer,
                                    contentDescription = "Suggested")
                            }
                            sections[2] -> {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favourite")
                            }
                        }
                        Text(
                            text = section,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                items(1) {
                    teamsList.forEach { team ->
                        BoxWithLayout {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(10f)
                                    .padding(5.dp)
                                    .background(
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = ripple(),
                                        onClick = {}
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .weight(2f)
                                        .size(50.dp),
                                    painter = team.painter,
                                    contentDescription = "Image"
                                )
                                Column(
                                    modifier = Modifier
                                        .weight(8f)
                                        .background(Color.Transparent)
                                ) {
                                    Text(text = team.name)
                                    Text(text = "")
                                }
                            }
                        }
                    }
                }
            }
        }*/





/*
* import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Scaffold
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.SearchedTeamsData
import com.elTohamy.flushy.screens.subScreens.BoxWithLayout

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchedClubsScreen() {
    val painterA = painterResource(id = R.drawable.club_a)
    val painterB = painterResource(id = R.drawable.club_b)
    val painterC = painterResource(id = R.drawable.club_c)
    val painterD = painterResource(id = R.drawable.club_d)
    val painterE = painterResource(id = R.drawable.club_e)
    val teamsList = remember {
        mutableListOf(
            SearchedTeamsData("ATM", painterA),
            SearchedTeamsData("Inter", painterB),
            SearchedTeamsData("ATM", painterC),
            SearchedTeamsData("Chelsea", painterD),
            SearchedTeamsData("Liverpool", painterE)
        )
    }

    Scaffold {
        val sections = listOf("Most Followed", "All Teams")
        LazyColumn(modifier = Modifier.padding()) {
            sections.forEach { section ->
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when(section) {
                            sections[0] -> {
                                Icon(
                                    modifier = Modifier
                                        .padding(7.dp),
                                    imageVector = Icons.AutoMirrored.Default.TrendingUp,
                                    contentDescription = "Most Followed")
                            }
                            sections[1] -> {
                                Icon(
                                    modifier = Modifier
                                        .padding(7.dp),
                                    imageVector = Icons.Default.Sports,
                                    contentDescription = "All Teams")
                            }
                        }
                        Text(
                            text = section,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
                items(teamsList.size) {
                    teamsList.forEach { team ->
                        BoxWithLayout {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(10f)
                                    .padding(5.dp)
                                    .shadow(
                                        elevation = 3.dp,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier.weight(2f)
                                        .padding(10.dp),
                                    painter = team.painter,
                                    contentDescription = "Image"
                                )
                                Column(
                                    modifier = Modifier.weight(8f)
                                ) {
                                    Text(text = team.name)
                                    Text(text = "Description")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchedClubsScreenPreview() {
    SearchedClubsScreen()
}*/






/*
* //Shared Preferences for Holding Lazy Column current index
    val context = LocalContext.current
    val prefs by lazy {
        context.applicationContext
            .getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }
    val scrollPosition =
        prefs.getInt("scroll_position", 0)
    val sharedPreferences =
        FavouriteLazyColumnSharedPreferences(context)
    val currentScrollIndex =
        sharedPreferences.getIndex("current_index", 0)
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = currentScrollIndex
    )
    //
    val data = remember { mutableStateOf(List(100) { "Item $it" }) }
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        data.value = data.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    })
    val sections = listOf("Favourite", "B", "C")
    Column(
        Modifier
            .fillMaxWidth()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.KeyboardArrowUp,
            null,
            Modifier.graphicsLayer {
                // Hide the icon if we cannot scroll backward (we are the start of the list)
                // We use graphicsLayer here to control the alpha so that we only redraw when this
                // value changes, instead of recomposing
                alpha = if (state.listState.canScrollBackward) 1f else 0f
            },
            Color.Red
        )
        val items = (1..40).toList()
        LaunchedEffect(state) {
            snapshotFlow {
                state.listState.firstVisibleItemIndex
            }
                .debounce(500L)
                .collectLatest { index->
                    sharedPreferences
                        .setIndex("current_index", index)
                    //prefs.edit().putInt("scroll_position", index)
                    //                            .apply()
                }
        }
        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .reorderable(state)
                .detectReorderAfterLongPress(state),
            state.listState
        ) {
            sections.forEach {
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.background
                            )
                            .padding(
                                start = 2.dp,
                                end = 2.dp,
                                bottom = 5.dp
                            )
                            .shadow(
                                elevation = 1.dp,
                                shape = RoundedCornerShape(2.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(2.dp)
                            )
                    ) {
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = it
                        )
                    }
                }
                items(data.value, { it }) { item->
                    ReorderableItem(
                        reorderableState = state,
                        key = item
                    ) { isDragging->
                        val elevation =
                            animateDpAsState(
                                targetValue =
                                    if (isDragging) 16.dp else 0.dp,
                                label = ""
                            )
                        BoxWithLayout {
                            Row(
                                modifier = Modifier
                                    .weight(10f)
                                    .padding(
                                        start = 4.dp, end = 4.dp, bottom = 5.dp
                                    )
                                    .shadow(
                                        elevation = elevation.value,
                                        shape = RoundedCornerShape(2.dp)
                                    )
                                    .background(LocalCustomColorsPalette.current.championshipCard)
                                    .clickable(
                                        onClick = {},
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = ripple(
                                            color = MaterialTheme.colorScheme.primary.copy(0.2f),
                                            bounded = true
                                        )
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ) {
                                Image(
                                    modifier = Modifier
                                        .weight(2f)
                                        .size(width = 40.dp, height = 40.dp),
                                    painter = painterResource(id = R.drawable.ic_heart),
                                    contentDescription = ""
                                )
                                Text(
                                    modifier = Modifier.weight(8f),
                                    text = "Item is $it"
                                )
                            }
                        }
                    }
                }
            }
        }
        Icon(
            Icons.Filled.KeyboardArrowDown,
            null,
            Modifier.graphicsLayer {
                // Hide the icon if we cannot scroll forward (we are the end of the list)
                // We use graphicsLayer here to control the alpha so that we only redraw when this
                // value changes, instead of recomposing
                alpha = if (state.listState.canScrollForward) 1f else 0f
            },
            Color.Red
        )
    }*/






/*
* package com.elTohamy.flushy.components.searchComponents.lazyColumns

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.ripple
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.FavouriteData
import com.elTohamy.flushy.myTheme.LocalCustomColorsPalette
import com.elTohamy.flushy.screens.subScreens.BoxWithLayout
import com.elTohamy.flushy.sharedPreferences.FavouriteLazyColumnSharedPreferences
import com.elTohamy.flushy.viewModels.LazyColumnStateViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun FavouriteLazyColumn(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    expanded: Boolean = false,
    onExpandChanged: (Boolean) -> Unit,
    leading: @Composable (RowScope.() -> Unit)? = null,
    title: @Composable (RowScope.() -> Unit)? = null,
    expand: @Composable (RowScope.(modifier: Modifier) -> Unit)? = null,
    content: @Composable () -> Unit
) {
    //Animation Logic for Expanding
    val expandedStateA by remember { mutableStateOf(false) }
    val expandedStateB by remember { mutableStateOf(false) }
    val expandedStateC by remember { mutableStateOf(false) }
    val expandedStateD by remember { mutableStateOf(false) }
    val expandedStateE by remember { mutableStateOf(false) }
    val expandedStateF by remember { mutableStateOf(false) }
    val expandedStateG by remember { mutableStateOf(false) }

    val rotationStateA by animateFloatAsState(
        targetValue = if (expandedStateA) 180f else 0f, label = ""
    )
    val rotationStateB by animateFloatAsState(
        targetValue = if (expandedStateB) 180f else 0f, label = ""
    )
    val rotationStateC by animateFloatAsState(
        targetValue = if (expandedStateC) 180f else 0f, label = ""
    )
    val rotationStateD by animateFloatAsState(
        targetValue = if (expandedStateD) 180f else 0f, label = ""
    )
    val rotationStateE by animateFloatAsState(
        targetValue = if (expandedStateE) 180f else 0f, label = ""
    )
    val rotationStateF by animateFloatAsState(
        targetValue = if (expandedStateF) 180f else 0f, label = ""
    )
    val rotationStateG by animateFloatAsState(
        targetValue = if (expandedStateG) 180f else 0f, label = ""
    )

    //
    val painterA = painterResource(id = R.drawable.fwc_usa)
    val painterB = painterResource(id = R.drawable.euro)
    val painterC = painterResource(id = R.drawable.p_league)
    val painterD = painterResource(id = R.drawable.la_liga)
    val painterE = painterResource(id = R.drawable.serie_a)
    val painterF = painterResource(id = R.drawable.ligue1)
    val painterG = painterResource(id = R.drawable.bundes_ligue)
    val clickStateA = remember { mutableStateOf(false) }
    val clickStateB = remember { mutableStateOf(false) }
    val clickStateC = remember { mutableStateOf(false) }
    val clickStateD = remember { mutableStateOf(false) }
    val clickStateE = remember { mutableStateOf(false) }
    val clickStateF = remember { mutableStateOf(false) }
    val clickStateG = remember { mutableStateOf(false) }
    val favouriteList = remember {
        mutableListOf(
            FavouriteData(
                "FIFA World Cup", "FIFA World Cup",
                painterA, clickStateA.value, expandedStateA, rotationStateA),
            FavouriteData(
                "Euro", "FIFA World Cup",
                painterB, clickStateB.value, expandedStateB, rotationStateB),
            FavouriteData(
                "Premier League", "FIFA World Cup",
                painterC, clickStateC.value, expandedStateC, rotationStateC),
            FavouriteData(
                "La Liga", "FIFA World Cup",
                painterD, clickStateD.value, expandedStateD, rotationStateD),
            FavouriteData(
                "Serie A", "FIFA World Cup",
                painterE, clickStateE.value, expandedStateE, rotationStateE),
            FavouriteData(
                "Ligue 1", "FIFA World Cup",
                painterF, clickStateF.value, expandedStateF, rotationStateF),
            FavouriteData(
                "Bundesliga", "FIFA World Cup",
                painterG, clickStateG.value, expandedStateG, rotationStateG)
        )
    }

    //Shared Preferences for Holding Lazy Column current index
    val context = LocalContext.current
    /*val prefs by lazy {
        context.applicationContext
            .getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }
    val scrollPosition =
        prefs.getInt("scroll_position", 0)*/
    val sharedPreferences =
        FavouriteLazyColumnSharedPreferences(context)
    val currentScrollIndex =
        sharedPreferences.getIndex("current_index", 0)
    val state = rememberLazyListState(
        initialFirstVisibleItemIndex = currentScrollIndex
    )
    val sections = listOf("Favourite")
    Column(
        modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Filled.KeyboardArrowUp,
            null,
            modifier.graphicsLayer {
                // Hide the icon if we cannot scroll backward (we are the start of the list)
                // We use graphicsLayer here to control the alpha so that we only redraw when this
                // value changes, instead of recomposing
                alpha = if (state.canScrollBackward) 1f else 0f
            },
            Color.Red
        )
        //val items = (1..40).toList()
        LaunchedEffect(state) {
            snapshotFlow {
                state.firstVisibleItemIndex
            }
                .debounce(500L)
                .collectLatest { index->
                    sharedPreferences
                        .setIndex("current_index", index)
                    //prefs.edit().putInt("scroll_position", index)
                    //                            .apply()
                }
        }
        LazyColumn(
            modifier
                .weight(1f)
                .fillMaxWidth(),
            state
        ) {
            sections.forEach {
                items(favouriteList.size, { it }) {
                    val myContext = LocalContext.current
                    BoxWithLayout {
                        Column(
                            modifier = modifier
                                .weight(10f)
                                .padding(
                                    start = 4.dp, end = 4.dp, bottom = 5.dp
                                )
                                .shadow(
                                    elevation = 1.dp,
                                    shape = RoundedCornerShape(2.dp)
                                )
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = LinearOutSlowInEasing
                                    )
                                )
                                .border(
                                    width = if (favouriteList[it].isClicked) 1.dp else 0.dp,
                                    color = if (favouriteList[it].isClicked) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    shape = RoundedCornerShape(2.dp)
                                )
                                .background(LocalCustomColorsPalette.current.championshipCard)
                                .clickable(
                                    onClick = {
                                        Toast
                                            .makeText(myContext, "Details", Toast.LENGTH_SHORT)
                                            .show()
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple(
                                        color = MaterialTheme.colorScheme.primary.copy(0.2f),
                                        bounded = true
                                    )
                                )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ) {
                                Image(
                                    modifier = modifier
                                        .weight(2f)
                                        .size(width = 40.dp, height = 40.dp),
                                    painter = favouriteList[it].painter,
                                    contentDescription = "Image Description"
                                )
                                Text(
                                    modifier = modifier.weight(7f),
                                    text = favouriteList[it].name,
                                    style = TextStyle(
                                        color = if (favouriteList[it].isClicked) MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.onSurface.copy(0.2f),
                                        fontSize = 12.sp,
                                        fontWeight = if (favouriteList[it].isClicked) FontWeight.Bold else FontWeight.Normal
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                IconButton(
                                    modifier = modifier
                                        .rotate(favouriteList[it].rotationalState)
                                        .weight(1f),
                                    onClick = {
                                        favouriteList[it].isClicked = !favouriteList[it].isClicked
                                        favouriteList[it].expandableState = !favouriteList[it].expandableState
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardDoubleArrowDown,
                                        contentDescription = ""
                                    )
                                }
                            }
                            if (favouriteList[it].expandableState) {
                                println("Hello")
                                Text(
                                    text = favouriteList[it].description,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.secondary,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
        Icon(
            Icons.Filled.KeyboardArrowDown,
            null,
            modifier.graphicsLayer {
                // Hide the icon if we cannot scroll forward (we are the end of the list)
                // We use graphicsLayer here to control the alpha so that we only redraw when this
                // value changes, instead of recomposing
                alpha = if (state.canScrollForward) 1f else 0f
            },
            Color.Red
        )
    }
}*/







/*
* val scope = rememberCoroutineScope()
    val scaffoldBottomState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    val context = LocalContext.current
    val currentCat = "Home"
    var isExpanded = false
    BottomSheetScaffold(
        sheetContent = {
            var selected1 by remember { mutableStateOf(false) }
            var selected2 by remember { mutableStateOf(false) }
            var selected3 by remember { mutableStateOf(false) }
            var selected4 by remember { mutableStateOf(false) }
            Scaffold { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                            verticalArrangement = Arrangement.Top) {
                            Row(modifier = Modifier.padding(bottom = 15.dp)) {
                                Icon(
                                    tint = DarkGreen,
                                    imageVector = Icons.Filled.AttachMoney,
                                    contentDescription = "Pick Your Package"
                                )
                                Text(
                                    text = "Pick Your Package",
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                                    )
                                )
                                Icon(
                                    modifier = Modifier.padding(start = 5.dp),
                                    tint = Yellow,
                                    imageVector = Icons.Filled.Movie,
                                    contentDescription = "Pick Your Package"
                                )
                            }
                            SelectableItem(
                                selected = selected1,
                                title = "Android Developer: Mahmoud Nabil",
                                onClick = {
                                    selected1 = !selected1
                                }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            SelectableItem(
                                selected = selected2,
                                title = "Web Developer: Ayman Nabil",
                                subTitle = "My name is Ayman Nabil My name is Ayman Nabil" +
                                        "My name is Ayman Nabil My name is Ayman Nabil",
                                onClick = {
                                    selected2 = !selected2
                                }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            SelectableItem(
                                selected = selected3,
                                title = "Web Developer: Ayman Nabil",
                                subTitle = "My name is Ayman Nabil My name is Ayman Nabil" +
                                        "My name is Ayman Nabil My name is Ayman Nabil",
                                onClick = {
                                    selected3 = !selected3
                                }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            SelectableItem(
                                selected = selected4,
                                title = "Web Developer: Ayman Nabil",
                                subTitle = "My name is Ayman Nabil My name is Ayman Nabil" +
                                        "My name is Ayman Nabil My name is Ayman Nabil",
                                onClick = {
                                    selected4 = !selected4
                                }
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    scaffoldBottomState.bottomSheetState.hide()
                                }
                            }
                        ) {
                            Text(text = "Skip To Free Use")
                        }
                    }
                }
            }
        },
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldBottomState,
        sheetShape = BottomSheetDefaults.ExpandedShape,
    ) { paddingValues ->
        ModalNavigationDrawer(
            modifier = Modifier.systemBarsPadding(),
            drawerState = drawerState,
            drawerContent = {
                Column(modifier = Modifier.padding(15.dp)) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                /*navController.popBackStack()
                                navController.navigate(NavigationItem.Home.path)*/
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home")

                        Text(text = "Home")
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                //navController.navigate(NavigationItem.DetailScreen.path)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Mail,
                            contentDescription = "Mail")

                        Text(text = "Mail")
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        "Shop",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Shop,
                            contentDescription = "Shop")

                        Text(text = "Shop")
                    }
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        "Movie",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Movie,
                            contentDescription = "Movie")

                        Text(text = "Movie")
                    }
                }
            },
            scrimColor = DrawerDefaults.scrimColor
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                topBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    scope.launch {
                                        scaffoldBottomState.bottomSheetState.expand()
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Cat: $currentCat",
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                                )
                            )
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Drop Down Arrow"
                            )
                        }
                    }
                }
            ) { scaffoldPadding ->
                Box(modifier = Modifier.padding(scaffoldPadding))
            }
        }
    }*/