package com.elTohamy.flushy.components.searchComponents.searchComponents.components

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.ripple
import com.elTohamy.flushy.components.searchComponents.searchComponents.sharedPreferences.FavouriteLazyColumnSharedPreferences
import com.elTohamy.flushy.components.searchComponents.searchComponents.viewModels.ExpandableListViewModel
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.BoxWithLayout
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun FavouriteLazyColumn(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {

    val viewModel: ExpandableListViewModel = viewModel()
    val itemIds by viewModel.itemIds.collectAsState()

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
            sections.forEach { _ ->
                itemsIndexed(viewModel.items.value) {index, item->
                    val myContext = LocalContext.current
                    BoxWithLayout {
                        Card(
                            modifier = modifier
                                .weight(10f)
                                .padding(
                                    start = 8.dp, end = 8.dp, bottom = 5.dp
                                )
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = LinearOutSlowInEasing
                                    )
                                )
                                .background(MaterialTheme.customColorsPalette.matchCard)
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
                                ),
                            shape = RoundedCornerShape(15.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.customColorsPalette.mainCardContainer
                            )
                        ) {
                            Row(
                                modifier = modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween
                            ) {
                                Image(
                                    modifier = modifier
                                        .size(width = 40.dp, height = 40.dp)
                                        .weight(2f),
                                    painter = painterResource(id = item.painter),
                                    contentDescription = ""
                                )
                                Text(
                                    modifier = modifier.weight(7f),
                                    text = item.name,
                                    style = TextStyle(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                val rotateState by animateFloatAsState(
                                    targetValue = if (itemIds.contains(index)) 180f else 0f,
                                    label = ""
                                )
                                IconButton(
                                    modifier = modifier
                                        .rotate(rotateState)
                                        .weight(1f),
                                    onClick = {
                                        viewModel.onItemClicked(index)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardDoubleArrowDown,
                                        contentDescription = ""
                                    )
                                }
                            }
                            if (itemIds.contains(index)) {
                                Text(
                                    text = item.description,
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
}

@Preview(showBackground = true)
@Composable
fun FavouriteLazyColumnPreview() {
    FavouriteLazyColumn(paddingValues = PaddingValues(0.dp))
}