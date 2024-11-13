package com.elTohamy.flushy.presentation.activities.team.tabRow.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.layoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.news.ArticlesItem
import com.elTohamy.flushy.data.state.TeamNewsState
import com.elTohamy.flushy.presentation.activities.news.NewsActivity
import com.elTohamy.flushy.presentation.activities.team.viewModel.TeamViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import java.util.Locale

@Composable
fun TeamNewsScreen(locale: Locale?, country: String?, teamName: String?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            text = stringResource(id = R.string.tournamentRecent),
            style = TextStyle(
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Start
        )
        FetchTeamNews(locale = locale, country = country, teamName = teamName)
    }
}

@Composable
fun FetchTeamNews(
    teamViewModel: TeamViewModel = hiltViewModel(),
    locale: Locale?, country: String?, teamName: String?
) {
    LaunchedEffect(key1 = teamViewModel.isTeamNewsStateInitialized.value) {
        if (!teamViewModel.isTeamNewsStateInitialized.value) {
            teamViewModel.getTeamNews(
                language = if (locale!!.layoutDirection == LayoutDirection.Rtl.ordinal)
                    "ar"
                else "en",
                country = country!!, q1 = teamName!!,
                q2 = if (locale.layoutDirection == LayoutDirection.Rtl.ordinal)
                    "كرة القدم"
                else "Soccer"
            )
            teamViewModel.isTeamNewsStateInitialized.value = true
        }
    }
    when (
        val tournamentNewsState =
            teamViewModel.teamNewsState.collectAsState().value
    ) {
        is TeamNewsState.Empty -> {}
        is TeamNewsState.Loading -> {}
        is TeamNewsState.Error -> {}
        is TeamNewsState.Success -> {
            TeamNewsItems(articles = tournamentNewsState.data.body()!!.articles)
        }
    }
}

@Composable
fun TeamNewsItems(articles: List<ArticlesItem?>?) {
    LazyColumn {
        items(6) {
            TeamNewsItem(articles?.get(it))
        }
    }
}

@Composable
fun TeamNewsItem(article: ArticlesItem?) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
        ),
        onClick = {
            val intent = Intent(context, NewsActivity::class.java)
            intent.putExtra("newsTitle", article!!.title)
            intent.putExtra("newsDescription", article.description)
            intent.putExtra("newsContent", article.content)
            intent.putExtra("newsUrl", article.url)
            intent.putExtra("newsImage", article.image)
            intent.putExtra("newsDate", article.publishedAt)
            intent.putExtra("newsSourceName", article.source!!.name)
            intent.putExtra("newsSourceUrl", article.source.url)
            context.startActivity(intent)
        }
    ) {
        BoxWithLayout {
            //val (image, sourceName, title, description) = createRefs()
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .weight(3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(1.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                    )
                ) {
                    AsyncImage(
                        modifier = Modifier.size(150.dp),
                        model = article!!.image,
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .weight(2f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = article!!.title!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier,
                        text = article.description!!,
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier.padding(end = 3.dp),
                            text = stringResource(id = R.string.tournamentNewsAt),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        )
                        Text(
                            text = article.publishedAt!!.substring(0, 10),
                            style = TextStyle(
                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp
                            ),
                            maxLines = 1
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.tournamentNewsBy),
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                )
                            )
                            Text(
                                text = article.source!!.name!!,
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                        }
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.tournamentNewsDetails),
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                )
                            )
                            Icon(
                                modifier = Modifier.size(15.dp),
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "Details",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}