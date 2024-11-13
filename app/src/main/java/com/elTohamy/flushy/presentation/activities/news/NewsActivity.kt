package com.elTohamy.flushy.presentation.activities.news

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.layoutDirection
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.local.news.SavedNews
import com.elTohamy.flushy.data.repos.firebase.AuthUiClient
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.ui.theme.DarkSystemBars
import com.elTohamy.flushy.presentation.ui.theme.FlushyTheme
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.presentation.uiMode.UserSettings
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.DarkTheme
import com.elTohamy.flushy.utils.LocalTheme
import com.elTohamy.flushy.utils.getLocale
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "NewsActivity"
@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

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

        val extras = intent.extras
        val title = extras!!.getString("newsTitle")
        val description = extras.getString("newsDescription")
        val content = extras.getString("newsContent")
        val url = extras.getString("newsUrl")
        val image = extras.getString("newsImage")
        val date = extras.getString("newsDate")
        val sourceName = extras.getString("newsSourceName")
        val sourceUrl = extras.getString("newsSourceUrl")
        //
        setContent {
            val dark = LocalTheme.current.isDark
            val view = LocalView.current
            val statusBarLight = Color.White.toArgb()
            val statusBarDark = DarkSystemBars.toArgb()
            val navigationBarLight = Color.White.toArgb()
            val navigationBarDark = DarkSystemBars.toArgb()
            DisposableEffect(dark) {
                val activity = view.context as Activity
                activity.window.statusBarColor =
                    if(dark){statusBarDark} else {statusBarLight}
                activity.window.navigationBarColor =
                    if(dark){navigationBarDark} else {navigationBarLight}

                WindowCompat.getInsetsController(activity.window, activity.window.decorView).apply {
                    isAppearanceLightStatusBars = !dark
                    isAppearanceLightNavigationBars = !dark
                }

                onDispose { }
            }
            val local = if (getLocale()!!.layoutDirection == LayoutDirection.Rtl.ordinal)
                LayoutDirection.Rtl
            else LayoutDirection.Ltr
            val theme = userSettings.themeStream.collectAsState()
            val useDarkColors = when (theme.value) {
                AppTheme.MODE_AUTO -> DarkTheme(isSystemInDarkTheme())
                AppTheme.MODE_DAY -> DarkTheme(false)
                AppTheme.MODE_NIGHT -> DarkTheme(true)
            }
            CompositionLocalProvider(LocalTheme provides useDarkColors, LocalLayoutDirection provides local) {
                FlushyTheme(darkTheme = LocalTheme.current.isDark) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold(
                            topBar = {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RectangleShape,
                                    elevation = CardDefaults.cardElevation(8.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        IconButton(
                                            onClick = {
                                                finish()
                                            }
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(20.dp),
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Arrow Back"
                                            )
                                        }
                                        Image(
                                            modifier = Modifier.size(60.dp),
                                            painter = painterResource(id = R.drawable.flushy_logo_t),
                                            contentDescription = "Flushy Logo"
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
                                                text = stringResource(id = R.string.newsDetailsHeader),
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
                        ) {
                            NewsDetails(
                                paddingValues = it,
                                title = title!!,
                                description = description!!,
                                content = content!!,
                                url = url!!,
                                image = image!!,
                                date = date!!,
                                sourceName = sourceName!!,
                                sourceUrl = sourceUrl!!,
                                authUiClient = flushyAuthUiClient
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsDetails(
    paddingValues: PaddingValues,
    title: String,
    description: String,
    content: String,
    url: String,
    image: String,
    date: String,
    sourceName: String,
    sourceUrl: String,
    mainViewModel: MainViewModel = hiltViewModel(),
    authUiClient: AuthUiClient
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f, label = ""
    )
    var isSaved by rememberSaveable {
        mutableStateOf(mainViewModel.isNewsSaved(title))
    }
    val dark = LocalTheme.current.isDark
    val context = LocalContext.current
    val firestore = FirebaseFirestore.getInstance()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BoxWithLayout {
            Row(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .weight(4f)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.Title,
                        contentDescription = "New Title",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = title,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (!isSaved) {
                            isSaved = true
                            val savedNews =
                                SavedNews(
                                    title = title,
                                    description = description,
                                    content = content,
                                    url = url,
                                    image = image,
                                    date = date,
                                    sourceName = sourceName,
                                    sourceUrl = sourceUrl
                                )
                            mainViewModel.addOnSavedNews(savedNews)
                            firestore.collection("favourite").document(authUiClient.getSignedInUser()?.userId!!)
                                .collection("news").document(title).set(
                                    savedNews
                                )
                            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                        } else {
                            isSaved = false
                            val savedNews =
                                SavedNews(
                                    title = title,
                                    description = description,
                                    content = content,
                                    url = url,
                                    image = image,
                                    date = date,
                                    sourceName = sourceName,
                                    sourceUrl = sourceUrl
                                )
                            mainViewModel.deleteOneSavedNews(savedNews)
                            firestore.collection("favourite").document(authUiClient.getSignedInUser()?.userId!!)
                                .collection("news").document(title).delete()
                            Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector =
                        if (isSaved) Icons.Default.BookmarkAdded
                        else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        val uriHandler = LocalUriHandler.current
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            ),
            onClick = { uriHandler.openUri(url) }
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.AdsClick,
                        contentDescription = "Click Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = R.string.newsClick),
                        style = TextStyle(
                            color = if (dark) Color.LightGray else Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        ),
                        maxLines = 1
                    )
                }
                AsyncImage(
                    modifier = Modifier
                        .size(300.dp)
                        .padding(5.dp),
                    model = image,
                    contentDescription = "News Photo",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Publish Date Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = date.substring(0, 10),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Publish Date Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = date.substring(11, 16),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            BoxWithLayout {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .weight(7f),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.weight(1.5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Create,
                            contentDescription = "Description Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(id = R.string.newsContent),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            ),
                            maxLines = 1
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(5.5f)
                            .padding(4.dp),
                        text = description,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        maxLines = 3,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 500, easing = LinearOutSlowInEasing
                    )
                )
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            ),
            onClick = { isExpanded = !isExpanded }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Newspaper,
                            contentDescription = "Content Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = stringResource(id = R.string.newsDetails),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            ),
                            maxLines = 1
                        )
                    }
                    IconButton(
                        modifier = Modifier.rotate(rotationState),
                        onClick = { isExpanded = !isExpanded }
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.KeyboardDoubleArrowDown,
                            contentDescription = "Drop Down Arrow"
                        )
                    }
                }
                if (isExpanded) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = content,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .padding(horizontal = 4.dp),
                        imageVector = Icons.Default.Book,
                        contentDescription = "Description Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(id = R.string.newsMoreInfo),
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        ),
                        maxLines = 1
                    )
                }
                BoxWithLayout {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(3f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                imageVector = Icons.Default.Link,
                                contentDescription = "Source Icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.newsSource),
                                style = TextStyle(
                                    color = if (dark) Color.LightGray else Color.Gray,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                ),
                                maxLines = 1
                            )
                        }
                        Row(
                            modifier = Modifier.weight(2f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Card(
                                modifier = Modifier.padding(4.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                onClick = { uriHandler.openUri(sourceUrl) }
                            ) {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = sourceName,
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}