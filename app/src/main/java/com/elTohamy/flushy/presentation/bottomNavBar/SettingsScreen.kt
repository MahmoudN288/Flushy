package com.elTohamy.flushy.presentation.bottomNavBar

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Icon
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.repos.firebase.UserData
import com.elTohamy.flushy.presentation.activities.MyActivity
import com.elTohamy.flushy.presentation.bottomNavBar.language.LanguagePicker
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.presentation.uiMode.AppTheme
import com.elTohamy.flushy.presentation.uiMode.RadioButtonItem
import com.elTohamy.flushy.presentation.uiMode.RadioGroup
import com.elTohamy.flushy.presentation.viewModel.MainViewModel
import com.elTohamy.flushy.utils.LocalTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SettingsScreen(navController: NavHostController,
                   userDate: UserData?,
                   signOut: ()-> Unit,
                   selectedTheme: AppTheme = AppTheme.MODE_AUTO,
                   onItemSelected: (AppTheme) -> Unit,
                   modifier: Modifier = Modifier,
                   mainViewModel: MainViewModel = hiltViewModel()
) {
    val dark = LocalTheme.current.isDark
    if (mainViewModel.isDialogLanguageShown) {
        LanguagePicker(navController = rememberNavController()) {
            mainViewModel.onDismissLanguage()
        }
    }
    val themeItems = listOf(
        RadioButtonItem(
            id = AppTheme.MODE_DAY.ordinal,
            title = stringResource(id = R.string.light_theme),
        ),
        RadioButtonItem(
            id = AppTheme.MODE_NIGHT.ordinal,
            title = stringResource(id = R.string.dark_theme),
        ),
        RadioButtonItem(
            id = AppTheme.MODE_AUTO.ordinal,
            title = stringResource(id = R.string.auto_theme),
        ),
    )

    Scaffold(
        topBar = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainAppBar
                )
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.customColorsPalette.mainAppBar
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (userDate?.profilePictureUrl != null) {
                            Card(
                                modifier = Modifier,
                                shape = CircleShape,
                                elevation = CardDefaults.cardElevation(2.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                )
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(width = 50.dp, height = 50.dp),
                                    model = userDate.profilePictureUrl,
                                    contentDescription = "Profile",
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            if (userDate?.userName != null) {
                                Text(
                                    text = userDate.userName,
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            /*Text(
                                text = "mahmoudnabil288@hotmail.com",
                                style = TextStyle(
                                    color = MaterialTheme.customColorsPalette.blackToWhite,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 13.sp
                                ),
                                maxLines = 1
                            )*/
                        }
                        Card(
                            modifier = Modifier,
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(2.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            onClick = { signOut() }
                        ) {
                            Text(
                                modifier = Modifier
                                    .width(60.dp)
                                    .padding(5.dp),
                                text = "Sign Out",
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 13.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.padding(15.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.choose_your_theme),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(14.dp),
                    )
                    RadioGroup(
                        items = themeItems,
                        selected = selectedTheme.ordinal,
                        onItemSelect = { id -> onItemSelected(AppTheme.fromOrdinal(id)) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.other_settings),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(14.dp),
                    )
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        ),
                        onClick = {
                            mainViewModel.onDialogLanguageOpened()
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = modifier.size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.Default.Language,
                                    contentDescription = "Icon Language",
                                    tint = if (dark) Color.White else Color.Black
                                )
                                Text(
                                    text = stringResource(id = R.string.pick_language),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            IconButton(onClick = {
                                mainViewModel.onDialogLanguageOpened()
                                /*val intent = Intent(context, MyActivity::class.java)
                                context.startActivity(intent)*/
                            }) {
                                Icon(
                                    modifier = modifier
                                        .size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.AutoMirrored.Filled.MenuOpen,
                                    contentDescription = "Icon Language",
                                    tint = if (dark) Color.White else Color.Black
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        ),
                        onClick = {
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = modifier.size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.Default.NotificationsActive,
                                    contentDescription = "Notification",
                                    tint = if (dark) Color.White else Color.Black
                                )
                                Text(
                                    text = stringResource(id = R.string.notify),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    modifier = modifier
                                        .size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Icon Notification",
                                    tint = if (dark) Color.White else Color.Black
                                )
                            }
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, bottom = 15.dp),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = modifier
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(R.string.others),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(14.dp),
                    )
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        ),
                        onClick = {
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = modifier.size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Icon Share",
                                    tint = if (dark) Color.White else Color.Black
                                )
                                Text(
                                    text = stringResource(id = R.string.share),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    modifier = modifier
                                        .size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Icon Share",
                                    tint = if (dark) Color.White else Color.Black
                                )
                            }
                        }
                    }
                    val context = LocalContext.current
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                        ),
                        onClick = {
                            val intent = Intent(context, MyActivity::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    modifier = modifier.size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.Default.StarRate,
                                    contentDescription = "Rate",
                                    tint = if (dark) Color.White else Color.Black
                                )
                                Text(
                                    text = stringResource(id = R.string.rate),
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 15.sp
                                    )
                                )
                            }
                            IconButton(onClick = {
                            }) {
                                Icon(
                                    modifier = modifier
                                        .size(width = 20.dp, height = 20.dp),
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Icon Rate",
                                    tint = if (dark) Color.White else Color.Black
                                )
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
fun PickerPreview() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.customColorsPalette.mainAppBar
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.customColorsPalette.mainAppBar
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier,
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        modifier = Modifier.size(width = 50.dp, height = 50.dp),
                        painter = painterResource(id = R.drawable.player_f),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Mahmoud Nabil",
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                    )
                    /*Text(
                        text = "mahmoudnabil288@hotmail.com",
                        style = TextStyle(
                            color = MaterialTheme.customColorsPalette.blackToWhite,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp
                        ),
                        maxLines = 1
                    )*/
                }
                Card(
                    modifier = Modifier,
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = {  }
                ) {
                    Text(
                        modifier = Modifier
                            .width(60.dp)
                            .padding(5.dp),
                        text = "Sign Out",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}