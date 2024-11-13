package com.elTohamy.flushy.presentation.activities

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import androidx.core.text.layoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.ripple
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.local.news.SavedNews
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.presentation.activities.match.tabrow.screens.lineups.PlayerOnBenchComponent
import com.elTohamy.flushy.presentation.activities.tournament.screens.PrevA
import com.elTohamy.flushy.presentation.animations.lack.ShimmerListItem
import com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.screens.FavouriteTournamentScreen
import com.elTohamy.flushy.presentation.bottomNavBar.language.LanguagePicker
import com.elTohamy.flushy.presentation.bottomNavBar.language.Languages
import com.elTohamy.flushy.presentation.bottomNavBar.liveGamesScreen.components.PullToRefreshLazyColumn
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.FlushyTheme
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.presentation.uiMode.UserSettings
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.DarkTheme
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.findActivity
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MyActivity : AppCompatActivity() {

    @Inject
    lateinit var userSettings: UserSettings

    private val flushyAuthUiClient by lazy {
        AuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var itemCount by remember { mutableIntStateOf(15) }
            var isRefreshing by remember { mutableStateOf(false) }
            val coroutineScope = rememberCoroutineScope()
            val onRefresh: () -> Unit = {
                isRefreshing = true
                coroutineScope.launch {
                    // fetch something
                    delay(1500)
                    itemCount += 5
                    isRefreshing = false
                }
            }
            //Layout Direction
            val config = resources.configuration
            //val locale = Locale("ar")
            /*Locale.setDefault(locale)
            config.setLocale(locale)
            val local = if (locale.layoutDirection == LayoutDirection.Rtl.ordinal)
                LayoutDirection.Rtl
            else LayoutDirection.Ltr*/
            //
            val locale = Resources.getSystem().configuration.getLocales().get(0)
            val local = if (getLocale()!!.layoutDirection == LayoutDirection.Rtl.ordinal)
                LayoutDirection.Rtl
            else LayoutDirection.Ltr
            val theme = userSettings.themeStream.collectAsState()
            val useDarkColors = when (theme.value) {
                AppTheme.MODE_AUTO -> DarkTheme(isSystemInDarkTheme())
                AppTheme.MODE_DAY -> DarkTheme(false)
                AppTheme.MODE_NIGHT -> DarkTheme(true)
            }
            CompositionLocalProvider(
                LocalTheme provides useDarkColors, LocalLayoutDirection provides local
            ) {
                FlushyTheme(darkTheme = LocalTheme.current.isDark) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        /*SettingsScreen(
                            navController = rememberNavController(), theme.value, onItemSelected = {
                                    theme->
                                userSettings.theme = theme
                            }
                        )*/
                        //LanguagePicker()
                        //LiveGamesShimmer()
                        /*var isLoading by remember { mutableStateOf(true) }
                        LaunchedEffect(key1 = true) {
                            delay(10000)
                            isLoading = false
                        }
                        LazyColumn {
                            items(20) {
                                ShimmerListItem(
                                    isLoading = isLoading,
                                    contentAfterLoading = {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Home,
                                                contentDescription = "Home Icon",
                                                modifier = Modifier.size(100.dp)
                                            )
                                            Spacer(modifier = Modifier.width(16.dp))
                                            Text(
                                                text =
                                                "Hello Nabil, " +
                                                        "Hello Fatima, " +
                                                        "Hello Mahmoud & Hello Ayman")
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }*/
                        /*Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            NewsList(flushyAuthUiClient)
                        }*/
                        val items = remember {
                            (1..100).map { "Item $it" }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            PullToRefreshLazyColumn(
                                items = items,
                                content = { itemTitle ->
                                    Text(
                                        text = itemTitle,
                                        modifier = Modifier
                                            .padding(16.dp)
                                    )
                                },
                                isRefreshing = isRefreshing,
                                onRefresh = onRefresh
                            )
                            Button(
                                onClick = {
                                    isRefreshing = true
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            ) {
                                Text(text = "Refresh")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsList(
    authUiClient: AuthUiClient,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val savedNewsList = remember {
        mutableStateListOf<SavedNews>()
    }
    val firestore = FirebaseFirestore.getInstance()
    firestore.collection("favourite")
        .document(authUiClient.getSignedInUser()?.userId!!)
        .collection("news").get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documentResults = task.result
                val myList = documentResults.documents
                myList.forEach {
                    val data = it.data
                    val title = data!!["title"] as String
                    val description = data["description"] as String
                    val content = data["content"] as String
                    val url = data["url"] as String
                    val image = data["image"] as String
                    val date = data["date"] as String
                    val sourceName = data["sourceName"] as String
                    val sourceUrl = data["sourceUrl"] as String
                    val savedNews =
                        SavedNews(
                            title = title, description = description,
                            content = content, url = url,
                            image = image, date = date,
                            sourceName = sourceName, sourceUrl = sourceUrl
                        )
                    savedNewsList.add(savedNews)
                }
            }
        }
    LazyColumn {
        items(
            savedNewsList,
            itemContent = { item ->
                NewsItem(
                    title = item.title,
                    description = item.description,
                    content = item.content,
                    url = item.url,
                    image = item.image,
                    date = item.date,
                    sourceName = item.sourceName,
                    sourceUrl = item.sourceUrl
                )
            }
        )
    }
}

@Composable
fun NewsItem(
    title: String, description: String, content: String,
    url: String, image: String, date: String,
    sourceName: String, sourceUrl: String
) {
    val dark = LocalTheme.current.isDark
    Card {
        ConstraintLayout {
            val (
                titleRef, desRef, contRef, urlRef, timeRef,
                imgRef, dateRef, sourceNameRef, sourceUrlRef
            ) = createRefs()
            AsyncImage(
                modifier = Modifier
                    .size(80.dp)
                    .constrainAs(imgRef) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                model = image, contentDescription = "Image"
            )
            Text(
                modifier = Modifier.constrainAs(titleRef) {
                    start.linkTo(imgRef.end, margin = 8.dp)
                    top.linkTo(imgRef.top)
                },
                text = title,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.constrainAs(desRef) {
                    start.linkTo(imgRef.end, margin = 4.dp)
                    top.linkTo(titleRef.bottom, margin = 8.dp)
                },
                text = description,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.constrainAs(dateRef) {
                    start.linkTo(imgRef.end, margin = 4.dp)
                    bottom.linkTo(imgRef.bottom, margin = 4.dp)
                },
                text = date.substring(0, 10),
                style = TextStyle(
                    color = if (dark) LightGray else Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun LanguagePicker() {
    val list = listOf(
        Languages(id = 0, title = stringResource(id = R.string.english),
            tag = "en"),
        Languages(id = 1, title = stringResource(id = R.string.deutsch),
            tag = "de"),
        Languages(id = 2, title = stringResource(id = R.string.arabic),
            tag = "ar")
    )

    val context = LocalContext.current //context
    var tag by remember { mutableStateOf("en") }
    val onClickRefreshActivityEnglish = {
        //context.findActivity() is kotlin extension function
        context.findActivity()?.runOnUiThread {
            val appLocale = LocaleListCompat.forLanguageTags(tag) //here ta is hardcoded for testing purpose,you can add users selected language code.
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }
    val listState = rememberLazyListState()
    var selectedIndex by remember{ mutableIntStateOf(-1) }
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette
                .matchDetailsCardContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        LazyColumn(state = listState, userScrollEnabled = false) {
            items(items = list) { language ->
                com.elTohamy.flushy.presentation.bottomNavBar.language.LanguageRowItem(
                    onClick = {
                        selectedIndex = if (selectedIndex != language.id)
                            language.id else -1
                        tag = language.tag
                        onClickRefreshActivityEnglish()
                    },
                    title = language.title,
                    chosen = language.id == selectedIndex
                )
            }
        }
    }
}

data class Languages(val id: Int, val title: String, val tag: String)

@Composable
fun LanguageRowItem(
    onClick: ()-> Unit, title: String, chosen: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = title,
                style = TextStyle(
                    color =
                    if (chosen) MaterialTheme.colorScheme.primary
                    else MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguagePickerPrev() {
    LanguagePicker(navController = rememberNavController()) {}
}

@Composable
@ReadOnlyComposable
fun getLocale(): Locale? {
    val configuration = LocalConfiguration.current
    return ConfigurationCompat.getLocales(configuration).get(0)
}

@Composable
fun RankingLazy() {
    val expandedState = remember(list) { list.map { false }.toMutableStateList() }
    val groupedList = list.groupBy { itemA-> itemA.listOf.groupBy { itemB-> itemB.group } }
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        groupedList.values.forEachIndexed { indexA, listOfListOfObjects ->
            listOfListOfObjects.forEachIndexed { indexB, listOfObjects->
                val expanded = expandedState[indexA]
                item { Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 3000,
                                    easing = LinearOutSlowInEasing
                                )
                            ),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
                            expandedState[indexA] = !expanded
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    expandedState[indexA] = !expanded
                                }
                                .background(
                                    MaterialTheme.customColorsPalette.standingsRowsColor
                                )
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier
                                    .width(100.dp)
                                    .padding(10.dp),
                                text = listOfObjects.listOf[0].group,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                                textAlign = TextAlign.Start,
                                maxLines = 1
                            )
                            val rotationState by animateFloatAsState(
                                targetValue = if (expanded) 180f else 0f, label = ""
                            )
                            IconButton(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(width = 20.dp, height = 20.dp)
                                    .alpha(ContentAlpha.medium)
                                    .rotate(rotationState),
                                onClick = {
                                    expandedState[indexA] = !expanded
                                }) {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Drop-Down Arrow"
                                )
                            }
                        }
                    }
                }
                if (!expanded) {
                    items(listOfObjects.listOf.size) {
                        Ranking(obj = listOfObjects.listOf[it])
                    }
                }
            }
        }
    }
}

@Composable
fun Ranking(obj: Objects) {
    BoxWithLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.customColorsPalette.standingsRowsColor)
                .shadow(elevation = 0.5.dp)
                .padding(8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = true, color = MaterialTheme.colorScheme.primary),
                    onClick = {}
                )
                .weight(10f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .weight(4f)
                    .width(100.dp),
                text = obj.nameH,
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                ),
                maxLines = 1
            )
            Row(
                modifier = Modifier.weight(6f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.width(30.dp),
                    text = obj.rank,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.width(30.dp),
                    text = obj.points,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

val list = listOf(
    ListOfObjects(
        listOf(
            Objects("groupA", "Liverpool", "1", "18"),
            Objects("groupA", "Man City", "2", "15"),
            Objects("groupA", "Arsenal", "3", "10"),
            Objects("groupA", "Tottenham", "4", "8")
        )
    ),
    ListOfObjects(
        listOf(
            Objects("groupB", "Real Madrid", "1", "18"),
            Objects("groupB", "Barcelona", "2", "15"),
            Objects("groupB", "Atletico Madrid", "3", "10"),
            Objects("groupB", "Athletic Belbao", "4", "8")
        )
    ),
    ListOfObjects(
        listOf(
            Objects("groupC", "Bayern Munich", "1", "18"),
            Objects("groupC", "Bayer Liverkusen", "2", "15"),
            Objects("groupC", "Borussia Dortmund", "3", "10"),
            Objects("groupC", "RB Leibzig", "4", "8")
        )
    ),
    ListOfObjects(
        listOf(
            Objects("groupD", "Inter Milan", "1", "18"),
            Objects("groupD", "AC Milan", "2", "15"),
            Objects("groupD", "Juventus", "3", "10"),
            Objects("groupD", "Napoli", "4", "8")
        )
    ),
    ListOfObjects(
        listOf(
            Objects("groupE", "PSG", "1", "18"),
            Objects("groupE", "Monaco", "2", "15"),
            Objects("groupE", "Lyon", "3", "10"),
            Objects("groupE", "Lens", "4", "8")
        )
    )
)

data class ListOfObjects(val listOf: List<Objects>)
data class Objects(val group: String, val nameH: String, val rank: String, val points: String)

@Composable
fun P() {
    val dark = LocalTheme.current.isDark
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "Bench",
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.5f),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Card(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer,
                contentColor = MaterialTheme.customColorsPalette.blackToWhite
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier,
                    shape = RoundedCornerShape(10.dp),
                    onClick = {},
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Image(
                                modifier = Modifier.size(width = 70.dp, height = 70.dp),
                                painter = painterResource(id = R.drawable.c_tot),
                                contentDescription = "Team"
                            )
                        }
                        Text(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            text = "Tottenham",
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(if (dark) Color.LightGray else Color.Gray)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Manager",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier,
                        onClick = {},
                        shape = RoundedCornerShape(15.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier,
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.LightGray
                                )
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(width = 72.dp, height = 72.dp),
                                    painter = painterResource(id = R.drawable.player_f),
                                    contentDescription = ""
                                )
                            }
                            Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text = "Carlo Ancelotti",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(if (dark) Color.LightGray else Color.Gray)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Substitutes",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.5f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
                for (i in 1..8) {
                    Row {
                        PlayerOnBenchComponent(
                            playerImage = "playerImage!!",
                            name = "Vinicius Junior",
                            number = 7,
                            goals = 3,
                            assists = 2,
                            playerRating = "8.6",
                            yellowCards = 1,
                            redCards = 0,
                            hasSubbed = true,
                            hasInvolved = true,
                            isCaptain = true,
                            time = 58
                        ) {}
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer,
                contentColor = MaterialTheme.customColorsPalette.blackToWhite
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Card(
                        modifier = Modifier,
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(3.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Image(
                            modifier = Modifier.size(width = 70.dp, height = 70.dp),
                            painter = painterResource(id = R.drawable.c_tot),
                            contentDescription = "Team"
                        )
                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = "Tottenham",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(if (dark) Color.LightGray else Color.Gray)
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Manager",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier
                        .clickable { }
                        .fillMaxWidth()
                        .padding(3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier,
                        onClick = {},
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Image(
                            modifier = Modifier
                                .size(width = 60.dp, height = 60.dp),
                            painter = painterResource(id = R.drawable.player_f),
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = "Carlo Ancelotti",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(if (dark) Color.LightGray else Color.Gray)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Substitutes",
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite.copy(0.5f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
                for (i in 1..8) {
                    Row {
                        PlayerOnBenchComponent(
                            playerImage = "playerImage!!",
                            name = "Vinicius Junior",
                            number = 7,
                            goals = 3,
                            assists = 2,
                            playerRating = "8.6",
                            yellowCards = 1,
                            redCards = 0,
                            hasSubbed = true,
                            hasInvolved = true,
                            isCaptain = true,
                            time = 58
                        ) {}
                    }
                }
            }
        }
    }
}

@Composable
private fun MyTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(
                width = indicatorWidth,
            )
            .offset(
                x = indicatorOffset,
            )
            .clip(
                shape = CircleShape,
            )
            .background(
                color = indicatorColor,
            ),
    )
}

@Composable
private fun MyTabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            White
        } else {
            Black
        },
        animationSpec = tween(easing = LinearEasing), label = "",
    )
    Text(
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .width(tabWidth)
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp,
            ),
        text = text,
        color = tabTextColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun CustomTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 100.dp,
    onClick: (index: Int) -> Unit,
) {
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing), label = "",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(Color.White)
                .height(intrinsicSize = IntrinsicSize.Min),
        ) {
            MyTabIndicator(
                indicatorWidth = tabWidth,
                indicatorOffset = indicatorOffset,
                indicatorColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clip(CircleShape),
            ) {
                items.mapIndexed { index, text ->
                    val isSelected = index == selectedItemIndex
                    MyTabItem(
                        isSelected = isSelected,
                        onClick = {
                            onClick(index)
                        },
                        tabWidth = tabWidth,
                        text = text,
                    )
                }
            }
        }
        when (selectedItemIndex) {
            0 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                    Text(text = "Home")
                }
            }
            1 -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Detail")
                }
            }
        }
    }
}

@Composable
fun CustomTabSample() {
    val (selected, setSelected) = remember {
        mutableIntStateOf(0)
    }

    CustomTab(
        items = listOf("MUSIC", "PODCAST"),
        selectedItemIndex = selected,
        onClick = setSelected,
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun Prev() {
    /*Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RankingLazy()
        }
    }*/
    /*val pitchLight = Color(color = 0xFF92BC4B)
    val pitchDark = Color(color = 0xFF587447)
    val gold = Color(color = 0xFFFFD700)
    val rectColors: List<Color> = listOf(
        Color.White, Color.White
    )
    val stripColors: List<Color> = listOf(
        Color.White, Color.Black
    )
    //Number
    val textMeasurer = rememberTextMeasurer()
    val textToDraw = "7"
    val style = TextStyle(
        fontSize = 15.sp,
        color = Color.Black
    )
    val textLayoutResult = remember(textToDraw) {
        textMeasurer.measure(textToDraw, style)
    }
    //Name
    val nameMeasurer = rememberTextMeasurer()
    val nameToDraw = "H. M. Son"
    val nameStyle = TextStyle(
        fontSize = 12.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
    val nameLayoutResult = remember(nameToDraw) {
        nameMeasurer.measure(nameToDraw, nameStyle)
    }
    //Image
    val logoPainter: Painter = painterResource(id = R.drawable.c_tot)
    val goalPainter: Painter = painterResource(id = R.drawable.goal_lineups)
    val assistPainter: Painter = painterResource(id = R.drawable.assist)
    Canvas(modifier = Modifier
        .background(pitchDark)
        .width(90.dp)
        .height(120.dp)) {
        drawArc(
            color = Color.Black,
            startAngle = 180f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(width = size.width / 3, height = size.height / 4),
            style = Stroke(width = 7f, cap = StrokeCap.Square),
            topLeft = Offset(x = center.x / 1.5f, y = center.y / 3)
        )
        drawArc(
            color = Color.White,
            startAngle = 180f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(width = size.width / 2.863f, height = size.height / 3.5f),
            style = Stroke(width = 4f, cap = StrokeCap.Butt),
            topLeft = Offset(x = center.x / 1.54f, y = center.y / 3.6f)
        )
        //Back Border
        drawLine(
            color = Color.Black,
            start = Offset(x = center.x / 1.5f, y = center.y / 1.8f),
            end = Offset(x = center.x * 1.325f, y = center.y / 1.8f),
            strokeWidth = 5f
        )
        drawArc(
            color = Color.Black.copy(0.4f),
            startAngle = 180f,
            sweepAngle = -180f,
            useCenter = false,
            size = Size(width = size.width / 3.2f, height = size.height / 4.065f),
            topLeft = Offset(x = center.x / 1.45f, y = center.y / 3.11f)
        )
        //Main Arc
        drawArc(
            color = Color.White,
            startAngle = 170f,
            sweepAngle = -160f,
            useCenter = false,
            style = Stroke(width = 9f, cap = StrokeCap.Square),
            size = Size(width = size.width / 2.5f, height = size.height / 2.9f),
            topLeft = Offset(x = center.x / 1.67f, y = center.y / 5.31f)
        )
        //Main Rect
        drawRect(
            brush = Brush.linearGradient(rectColors),
            size = Size(width = size.width / 3, height = size.height / 2.76f),
            topLeft = Offset(x = center.x / 1.5f, y = center.y / 1.19f)
        )
        //Right Shadowed
        drawRect(
            brush = Brush.linearGradient(stripColors),
            size = Size(width = size.width / 12, height = size.height / 2.5f),
            topLeft = Offset(x = center.x * 1.335f, y = center.y / 1.3f)
        )
        //Right Strip
        drawArc(
            color = Color.White,
            startAngle = 270f,
            sweepAngle = -45f,
            useCenter = false,
            style = Stroke(width = 5f, cap = StrokeCap.Square),
            size = Size(width = size.width / 5, height = size.height / 5),
            topLeft = Offset(x = center.x / 2.25f, y = center.y / 1.8f)
        )
        drawLine(
            color = Color.White,
            start = Offset(x = center.x * 1.5f, y = center.y / 1.61f),
            end = Offset(x = center.x * 1.64f, y = center.y / 1.30f),
            strokeWidth = 5.2f
        )
        drawLine(
            color = Color.White,
            start = Offset(x = center.x * 1.37f, y = center.y / 1.71f),
            end = Offset(x = center.x * 1.6f, y = center.y / 1.27f),
            strokeWidth = 16f
        )
        drawLine(
            color = Color.Black,
            start = Offset(x = center.x * 1.55f, y = center.y / 1.25f),
            end = Offset(x = center.x * 1.65f, y = center.y / 1.3f),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        //Left Shadowed
        drawRect(
            brush = Brush.linearGradient(stripColors),
            size = Size(width = size.width / 12, height = size.height / 2.5f),
            topLeft = Offset(x = center.x / 2, y = center.y / 1.3f)
        )
        //Left Strip
        drawArc(
            color = Color.White,
            startAngle = 270f,
            sweepAngle = 45f,
            useCenter = false,
            style = Stroke(width = 5f, cap = StrokeCap.Square),
            size = Size(width = size.width / 5, height = size.height / 5),
            topLeft = Offset(x = center.x * 1.152f, y = center.y / 1.8f)
        )
        drawLine(
            color = Color.White,
            start = Offset(x = center.x / 2, y = center.y / 1.62f),
            end = Offset(x = center.x / 2.8f, y = center.y / 1.31f),
            strokeWidth = 5.2f
        )
        drawLine(
            color = Color.White,
            start = Offset(x = center.x / 1.6f, y = center.y / 1.76f),
            end = Offset(x = center.x / 2.42f, y = center.y / 1.27f),
            strokeWidth = 14f
        )
        drawLine(
            color = Color.Black,
            start = Offset(x = center.x / 2.25f, y = center.y / 1.25f),
            end = Offset(x = center.x / 2.85f, y = center.y / 1.3f),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        //Right Comp
        drawRect(
            brush = Brush.linearGradient(rectColors),
            size = Size(width = size.width / 19.5f, height = size.height / 20),
            topLeft = Offset(x = center.x * 1.232f, y = center.y / 1.3f)
        )
        //Left Comp
        drawRect(
            brush = Brush.linearGradient(rectColors),
            size = Size(width = size.width / 21, height = size.height / 20),
            topLeft = Offset(x = center.x / 1.5f, y = center.y / 1.3f)
        )
        //Left
        val pathLeftA = Path()
        pathLeftA.moveTo(x = center.x / 1.5f, y = center.y / 1.3f)
        pathLeftA.lineTo(x = center.x / 1.7f, y = center.y / 1.5f)
        pathLeftA.lineTo(x = center.x / 2f, y = center.y / 1.3f)
        drawPath(
            path = pathLeftA,
            brush = Brush.linearGradient(stripColors)
        )
        val pathLeftB = Path()
        pathLeftB.moveTo(x = center.x / 1.5f, y = center.y / 1.3f)
        pathLeftB.lineTo(x = center.x / 1.7f, y = center.y / 1.5f)
        pathLeftB.lineTo(x = center.x / 2f, y = center.y / 1.3f)
        drawPath(
            path = pathLeftB,
            brush = Brush.linearGradient(stripColors)
        )
        //Right
        val pathRightA = Path()
        pathRightA.moveTo(x = center.x * 1.335f, y = center.y / 1.3f)
        pathRightA.lineTo(x = center.x * 1.4f, y = center.y / 1.5f)
        pathRightA.lineTo(x = center.x * 1.5f, y = center.y / 1.3f)
        drawPath(
            path = pathRightA,
            brush = Brush.linearGradient(stripColors)
        )
        val pathRightB = Path()
        pathRightB.moveTo(x = center.x * 1.335f, y = center.y / 1.3f)
        pathRightB.lineTo(x = center.x * 1.4f, y = center.y / 1.5f)
        pathRightB.lineTo(x = center.x * 1.5f, y = center.y / 1.3f)
        drawPath(
            path = pathRightB,
            brush = Brush.linearGradient(stripColors)
        )
        //Number
        drawText(
            textMeasurer = textMeasurer,
            text = textToDraw,
            style = style,
            topLeft = Offset(
                x = center.x - textLayoutResult.size.width,
                y = center.y - textLayoutResult.size.height / 1.6f,
            )
        )
        //Name
        drawText(
            textMeasurer = nameMeasurer,
            text = nameToDraw,
            style = nameStyle,
            topLeft = Offset(
                x = center.x / 2.4f,
                y = center.y * 1.6f,
            )
        )
        //Logo
        translate(
            left = center.x * 1.05f,
            top = center.y / 1.15f
        ) {
            with(logoPainter) {
                draw(size = Size(15.dp.toPx(), 15.dp.toPx()))
            }
        }
        //Goal
        translate(
            left = center.x * 1.05f,
            top = center.y / 3.5f
        ) {
            with(goalPainter) {
                draw(size = Size(15.dp.toPx(), 15.dp.toPx()))
            }
        }
        //Goal
        translate(
            left = center.x / 1.6f,
            top = center.y / 3.5f
        ) {
            with(assistPainter) {
                draw(size = Size(15.dp.toPx(), 15.dp.toPx()))
            }
        }
    }*/
    /*Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(width = 25.dp, height = 25.dp),
                        painter = painterResource(id = R.drawable.goal_lineups),
                        contentDescription = "Goals Lineups"
                    )
                    Text(
                        modifier = Modifier
                            .size(width = 8.dp, height = 8.dp)
                            .background(Color.White, CircleShape),
                        text = "3",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(width = 25.dp, height = 25.dp),
                        painter = painterResource(id = R.drawable.assist),
                        contentDescription = "Assists Lineups"
                    )
                    Text(
                        modifier = Modifier
                            .size(width = 8.dp, height = 8.dp)
                            .background(Color.White, CircleShape),
                        text = "3",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(width = 25.dp, height = 25.dp),
                        painter = painterResource(id = R.drawable.y_card),
                        contentDescription = "Assists Lineups"
                    )
                    Text(
                        modifier = Modifier
                            .size(width = 8.dp, height = 8.dp)
                            .background(Color.White, CircleShape),
                        text = "3",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(width = 25.dp, height = 25.dp),
                        painter = painterResource(id = R.drawable.r_card),
                        contentDescription = "Assists Lineups"
                    )
                    Text(
                        modifier = Modifier
                            .size(width = 8.dp, height = 8.dp)
                            .background(Color.White, CircleShape),
                        text = "3",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = CircleShape
            ) {
                Image(
                    modifier = Modifier.size(width = 90.dp, height = 90.dp),
                    painter = painterResource(id = R.drawable.vini), contentDescription = "Player Image"
                )
            }
        }
    }*/

    /*val pitchLightSection = Color(color = 0xFF268B07) //0xFF09AD3F
    val pitchDarkSection = Color(color = 0xFF136D15)
    val pitchDarkAll = Color.Black.copy(0.5f)
    val dark = LocalTheme.current.isDark
    val painterCamera = painterResource(id = R.drawable.camera_w)
    val painterVar = painterResource(id = R.drawable.var_w)
    Box(
        modifier = Modifier
            .height(1200.dp)
            .drawBehind {
                drawRect(
                    color = if (dark) pitchDarkAll
                    else pitchDarkSection.copy(0.7f)
                )
                translate(
                    25f, 10f
                ) {
                    with(painterCamera) {
                        draw(
                            size = painterCamera.intrinsicSize
                                .div(1.2f)
                        )
                    }
                }
                translate(
                    center.x / 2, 2f
                ) {
                    with(painterVar) {
                        draw(
                            size = painterCamera.intrinsicSize
                                .div(1.2f)
                        )
                    }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 30.dp)
                .fillMaxWidth()
                .height(1000.dp)
                .drawBehind {
                    //Outside
                    drawRect(
                        color = if (dark) pitchDarkAll
                        else pitchDarkSection.copy(0.7f)
                    )
                    //Main Rectangle Lines
                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        style = Stroke(width = 7f, cap = StrokeCap.Round),
                        size = Size(width = size.width, height = size.height / 1.1f),
                        topLeft = Offset(x = 0f, y = center.y / 11)
                    )
                    val lighterBlock = LightGreen
                    val darkerBlock = DarkGreen
                    val blocksNumber = 12
                    for (i in 0..blocksNumber) {
                        val blockHeight = (size.height / blocksNumber)
                        val color =
                            if (i % 2 == 0)
                                lighterBlock else darkerBlock
                        drawRect(
                            color = color,
                            topLeft = Offset(
                                0f,
                                i * blockHeight
                            ),
                            size = Size(
                                size.width,
                                blockHeight
                            )
                        )
                    }

                    //Outside Pitch Container
                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        style = Stroke(width = 10f),
                        size = Size(
                            width = size.width,
                            height = size.height
                        ),
                        topLeft = Offset(x = 0f, y = 0f)
                    )

                    //Center Field
                    drawLine(
                        color = if (dark) Color.DarkGray else Color.White,
                        start = Offset(x = 0f, y = center.y),
                        end = Offset(x = size.width, y = center.y),
                        strokeWidth = 10f
                    )

                    drawCircle(
                        color = if (dark) Color.DarkGray else Color.White,
                        radius = 10f,
                        center = Offset(size.width / 2, size.height / 2)
                    )

                    drawCircle(
                        color = if (dark) Color.DarkGray else Color.White,
                        radius = 200f,
                        center = Offset(size.width / 2, size.height / 2),
                        style = Stroke(10f)
                    )

                    //Corner Home Left
                    drawArc(
                        color = if (dark) Color.DarkGray else Color.White,
                        startAngle = 0f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(
                            size.width / 10,
                            size.height / 20
                        ),
                        topLeft = Offset(-50f, -60f),
                        style = Stroke(10f)
                    )

                    //Corner Home Right
                    drawArc(
                        color = if (dark) Color.DarkGray else Color.White,
                        startAngle = 90f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(
                            size.width / 10,
                            size.height / 20
                        ),
                        topLeft = Offset(size.width / 1.05f, -60f),
                        style = Stroke(10f)
                    )

                    //Corner Away Left
                    drawArc(
                        color = if (dark) Color.DarkGray else Color.White,
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(
                            size.width / 10,
                            size.height / 20
                        ),
                        topLeft = Offset(-50f, size.height - 60f),
                        style = Stroke(10f)
                    )

                    //Corner Away Right
                    drawArc(
                        color = if (dark) Color.DarkGray else Color.White,
                        startAngle = 180f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(
                            size.width / 10,
                            size.height / 20
                        ),
                        topLeft = Offset(
                            size.width / 1.05f,
                            size.height - 60f
                        ),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        topLeft = Offset(
                            x = size.width.div(2) - 100f,
                            y = 0f
                        ),
                        size = Size(200f, 100f),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        topLeft = Offset(
                            x = size.width.div(2) - 250f,
                            y = 0f
                        ),
                        size = Size(500f, 250f),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        topLeft = Offset(
                            size.width.div(2) - 100f,
                            size.height - 100f
                        ), // Adjusted to ensure positive size
                        size = Size(200f, 100f),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        topLeft = Offset(
                            size.width.div(2) - 250f,
                            size.height - 250f
                        ), // Adjusted to ensure positive size
                        size = Size(500f, 250f),
                        style = Stroke(10f)
                    )
                    drawCircle(
                        color = if (dark) Color.DarkGray else Color.White,
                        radius = 10f,
                        center = Offset(size.width.div(2), 200f)
                    )

                    drawCircle(
                        color = if (dark) Color.DarkGray else Color.White,
                        radius = 10f,
                        center = Offset(size.width.div(2), size.height - 200f)
                    )

                    drawArc(
                        color = if (dark) Color.DarkGray else Color.White,
                        startAngle = 0f,
                        sweepAngle = 180f,
                        useCenter = false,
                        size = Size(200f, 100f),
                        topLeft = Offset(
                            size.width
                                .div(2) - 100f, 200f
                        ),
                        style = Stroke(3.dp.toPx())
                    )

                    drawArc(
                        color = if (dark) Color.DarkGray else Color.White,
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        size = Size(200f, 100f),
                        topLeft = Offset(
                            x = size.width.div(2) - 100f,
                            y = size.height.minus(300f)
                        ),
                        style = Stroke(3.dp.toPx())
                    )
                }
        ) {
            BoxWithLayout {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .fillMaxHeight()
                        .padding(vertical = 40.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 3.dp)
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PlayerOnPitchComponent(
                                playerImage = "",
                                name = "Vini Jr.",
                                number = 7,
                                goals = 3,
                                assists = 2,
                                playerRating = "9.2",
                                yellowCards = 1,
                                redCards = 1,
                                hasSubbed = true,
                                hasInvolved = true,
                                isCaptain = true
                            )
                        }
                    }
                }
            }
        }
    }*/
    /*val pitchLightSection = Color(color = 0xFF02AC6C) //0xFF268807
    val pitchDarkSection = Color(0xFF02AC6C) //0xFF136D15
    val pitchDarkAll = Color.Black.copy(0.5f)
    val dark = LocalTheme.current.isDark
    Column(modifier = Modifier.background(Color.Black)) {
        Box(
            modifier = Modifier
                .size(width = 390.dp, height = 500.dp)
                .drawBehind {
                    //Outside
                    drawRect(
                        color = if (dark) pitchDarkAll
                        else pitchDarkSection.copy(0.7f)
                    )
                    //Main Rectangle Lines
                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        style = Stroke(width = 7f, cap = StrokeCap.Round),
                        size = Size(width = size.width, height = size.height / 1.1f),
                        topLeft = Offset(x = 0f, y = center.y / 11)
                    )
                    drawRect(
                        color = if (dark) Color.Gray else pitchLightSection
                    )

                    //Outside Pitch Container
                    drawRect(
                        color = if (dark) Color.DarkGray else Color.White,
                        style = Stroke(width = 10f),
                        size = Size(
                            width = size.width,
                            height = size.height
                        ),
                        topLeft = Offset(x = 0f, y = 0f)
                    )

                    //Center Field
                    drawLine(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        start = Offset(x = 0f, y = center.y),
                        end = Offset(x = size.width, y = center.y),
                        strokeWidth = 10f
                    )

                    drawCircle(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        radius = 10f,
                        center = Offset(size.width / 2, size.height / 2)
                    )

                    drawCircle(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        radius = 200f,
                        center = Offset(size.width / 2, size.height / 2),
                        style = Stroke(10f)
                    )
                    //Corner Home Left
                    drawArc(
                        color = Color.White.copy(0.5f),
                        startAngle = 0f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(100f, 100f),
                        topLeft = Offset(-50f, -50f),
                        style = Stroke(3.dp.toPx())
                    )

                    drawArc(
                        color = Color.White.copy(0.5f),
                        startAngle = 90f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(100f, 100f),
                        topLeft = Offset(size.width - 50f, -50f),
                        style = Stroke(3.dp.toPx())
                    )

                    drawArc(
                        color = Color.White.copy(0.5f),
                        startAngle = 180f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(100f, 100f),
                        topLeft = Offset(
                            size.width - 50f, size.height - 50f
                        ),
                        style = Stroke(3.dp.toPx())
                    )

                    drawArc(
                        color = Color.White.copy(0.5f),
                        startAngle = 270f,
                        sweepAngle = 90f,
                        useCenter = false,
                        size = Size(100f, 100f),
                        topLeft = Offset(-50f, size.height - 50f),
                        style = Stroke(3.dp.toPx())
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        topLeft = Offset(
                            x = size.width.div(2) - 100f,
                            y = 0f
                        ),
                        size = Size(200f, 100f),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        topLeft = Offset(
                            x = size.width.div(2) - 250f,
                            y = 0f
                        ),
                        size = Size(500f, 250f),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        topLeft = Offset(
                            size.width.div(2) - 100f,
                            size.height - 100f
                        ), // Adjusted to ensure positive size
                        size = Size(200f, 100f),
                        style = Stroke(10f)
                    )

                    drawRect(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        topLeft = Offset(
                            size.width.div(2) - 250f,
                            size.height - 250f
                        ), // Adjusted to ensure positive size
                        size = Size(500f, 250f),
                        style = Stroke(10f)
                    )
                    drawCircle(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        radius = 10f,
                        center = Offset(size.width.div(2), 200f)
                    )

                    drawCircle(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        radius = 10f,
                        center = Offset(size.width.div(2), size.height - 200f)
                    )

                    drawArc(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        startAngle = 0f,
                        sweepAngle = 180f,
                        useCenter = false,
                        size = Size(200f, 100f),
                        topLeft = Offset(
                            size.width
                                .div(2) - 100f, 200f
                        ),
                        style = Stroke(3.dp.toPx())
                    )

                    drawArc(
                        color = if (dark) Color.DarkGray.copy(0.5f) else Color.White.copy(0.3f),
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        size = Size(200f, 100f),
                        topLeft = Offset(
                            x = size.width.div(2) - 100f,
                            y = size.height.minus(300f)
                        ),
                        style = Stroke(3.dp.toPx())
                    )
                }
        ) {}
        Text(text = "Hello")
        Text(text = "Hello")
        Text(text = "Hello")
        Text(text = "Hello")
        Text(text = "Hello")
        Text(text = "Hello")
    }*/
    CustomTabSample()
}