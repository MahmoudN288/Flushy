package com.elTohamy.flushy.presentation.activities.match.tabrow.screens.detailsScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReduceCapacity
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material3.Icon
import coil.compose.AsyncImage
import com.elTohamy.flushy.R
import com.elTohamy.flushy.data.remote.dto.venues.VenuesResponse
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.data.state.VenuesByIdState
import com.elTohamy.flushy.presentation.activities.match.viewModel.MatchViewModel
import com.elTohamy.flushy.utils.BoxWithLayout
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun FetchStadiumInfo(
    onDismiss: ()-> Unit,
    onConfirm:()->Unit,
    matchViewModel: MatchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        if (!matchViewModel.isVenuesByIdInitialized.value) {
            matchViewModel.getVenuesById(matchViewModel.venueId.value)
            matchViewModel.isVenuesByIdInitialized.value = true
        }
    }
    when (val state = matchViewModel.venuesByIdState.collectAsState().value) {
        is VenuesByIdState.Empty -> {}
        is VenuesByIdState.Loading -> {}
        is VenuesByIdState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
        is VenuesByIdState.Success -> {
            CustomDialog(
                onDismiss = { onDismiss() },
                onConfirm = { onConfirm() },
                venuesResponse = state.data.body()!!.response!![0]!!
            )
        }
    }
}

@Composable
fun CustomDialog(
    onDismiss: ()-> Unit,
    onConfirm:()->Unit,
    venuesResponse: VenuesResponse
) {
    val dark = LocalTheme.current.isDark
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(
                    2.dp, color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(15.dp)
                )
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = venuesResponse.name!!,
                    style = TextStyle(
                        color = MaterialTheme.customColorsPalette.blackToWhite,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Card(
                    modifier = Modifier.wrapContentWidth()
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = venuesResponse.image,
                        contentDescription = "Stadium"
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    BoxWithLayout {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(0.8f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = "Surface",
                                    style = TextStyle(
                                        color = MaterialTheme.customColorsPalette.blackToWhite,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Card {
                                    Image(
                                        modifier = Modifier
                                            .clickable { onConfirm() }
                                            .size(width = 120.dp, height = 200.dp),
                                        painter = painterResource(id = R.drawable.grass),
                                        contentDescription = "Stadium"
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1.2f)
                                    .width(IntrinsicSize.Max)
                                    .height(IntrinsicSize.Max),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.spacedBy(15.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Stadium Information",
                                        style = TextStyle(
                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 13.sp
                                        )
                                    )
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                        elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier,
                                            verticalArrangement = Arrangement.SpaceBetween,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            BoxWithLayout {
                                                Row(
                                                    modifier = Modifier
                                                        .weight(2f)
                                                        .padding(10.dp)
                                                ) {
                                                    Text(
                                                        modifier = Modifier.weight(1f),
                                                        text = "City",
                                                        style = TextStyle(
                                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                                            fontWeight = FontWeight.Normal,
                                                            fontSize = 12.sp
                                                        )
                                                    )
                                                    Text(
                                                        modifier = Modifier.weight(1f),
                                                        text = venuesResponse.city!!,
                                                        style = TextStyle(
                                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                                            fontWeight = FontWeight.SemiBold,
                                                            fontSize = 12.sp
                                                        )
                                                    )
                                                }
                                            }
                                            BoxWithLayout {
                                                Row(
                                                    modifier = Modifier
                                                        .weight(2f)
                                                        .padding(10.dp)
                                                ) {
                                                    Text(
                                                        modifier = Modifier.weight(1f),
                                                        text = "Country",
                                                        style = TextStyle(
                                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                                            fontWeight = FontWeight.Normal,
                                                            fontSize = 12.sp
                                                        )
                                                    )
                                                    Text(
                                                        modifier = Modifier.weight(1f),
                                                        text = venuesResponse.country!!,
                                                        style = TextStyle(
                                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                                            fontWeight = FontWeight.SemiBold,
                                                            fontSize = 12.sp
                                                        )
                                                    )
                                                }
                                            }
                                            BoxWithLayout {
                                                Row(
                                                    modifier = Modifier
                                                        .weight(2f)
                                                        .padding(10.dp)
                                                ) {
                                                    Text(
                                                        modifier = Modifier.weight(1f),
                                                        text = "Capacity",
                                                        style = TextStyle(
                                                            color = MaterialTheme.customColorsPalette.blackToWhite,
                                                            fontWeight = FontWeight.Normal,
                                                            fontSize = 12.sp
                                                        )
                                                    )
                                                    Row(
                                                        modifier = Modifier.weight(1f),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Text(
                                                            modifier = Modifier,
                                                            text = venuesResponse.capacity.toString(),
                                                            style = TextStyle(
                                                                color = MaterialTheme.customColorsPalette.blackToWhite,
                                                                fontWeight = FontWeight.SemiBold,
                                                                fontSize = 12.sp
                                                            )
                                                        )
                                                        Icon(
                                                            modifier = Modifier
                                                                .padding(horizontal = 7.dp)
                                                                .size(width = 20.dp, height = 20.dp),
                                                            imageVector = Icons.Default.ReduceCapacity,
                                                            contentDescription = "Crowd",
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
                    }

                }

            }
        }
    }
}