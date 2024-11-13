package com.elTohamy.flushy.presentation.animations.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elTohamy.flushy.utils.BoxWithLayout
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import com.valentinilk.shimmer.unclippedBoundsInWindow

@Composable
fun TournamentActivityShimmer(
) {
    TournamentShimmerScrollableContent()
}

@Composable
fun TournamentShimmerScrollableContent() {
    Scaffold(
        modifier = Modifier.shimmer(),
        topBar = {
            TournamentComponentHeader()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TournamentComponent()
            TournamentComponent()
            TournamentComponent()
            TournamentComponent()
            TournamentComponent()
        }
    }
}

@Composable
fun TournamentComponentHeader(
    modifier: Modifier = Modifier
) {
    val shimmerInstance = rememberShimmer(ShimmerBounds.Custom)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                // Util function included in the library
                val position = layoutCoordinates.unclippedBoundsInWindow()
                shimmerInstance.updateBounds(position)
            }
            .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .background(color = Color.Gray.copy(0.8f))
            )
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 20.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = Color.LightGray.copy(0.8f))
                )
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(shape = RoundedCornerShape(2.dp))
                        .background(color = Color.LightGray.copy(0.8f))
                )
            }
        }
        BoxWithLayout {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(5f)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = modifier
                        .weight(1f)
                        .size(100.dp),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray.copy(0.8f)
                    )
                ) {
                }
                Column(
                    modifier = modifier.weight(4f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 200.dp, height = 25.dp)
                            .clip(shape = RectangleShape)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 75.dp, height = 20.dp)
                            .clip(shape = RectangleShape)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
            }
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 20.dp)
                    .background(color = Color.LightGray.copy(0.8f))
            )
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 20.dp)
                    .background(color = Color.LightGray.copy(0.8f))
            )
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 20.dp)
                    .background(color = Color.LightGray.copy(0.8f))
            )
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 20.dp)
                    .background(color = Color.LightGray.copy(0.8f))
            )
        }
    }
}

@Composable
fun TournamentComponent() {
    val shimmerInstance = rememberShimmer(ShimmerBounds.Custom)
    Column(
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                // Util function included in the library
                val position = layoutCoordinates.unclippedBoundsInWindow()
                shimmerInstance.updateBounds(position)
            }
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray.copy(0.8f))
                .padding(horizontal = 8.dp)
                .height(35.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(60.dp)
                    .background(color = Color.LightGray.copy(0.8f))
            )
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RectangleShape)
                    .background(color = Color.LightGray.copy(0.8f))
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.weight(5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 60.dp, height = 60.dp)
                            .clip(CircleShape)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .size(width = 75.dp, height = 20.dp)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
                Row(
                    modifier = Modifier.weight(5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .size(width = 75.dp, height = 20.dp)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
            }
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RectangleShape)
                    .background(color = Color.LightGray.copy(0.8f))
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.weight(5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 60.dp, height = 60.dp)
                            .clip(CircleShape)
                            .background(color = Color.Black)
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .size(width = 75.dp, height = 20.dp)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
                Row(
                    modifier = Modifier.weight(5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .size(width = 75.dp, height = 20.dp)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
            }
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RectangleShape)
                    .background(color = Color.LightGray.copy(0.8f))
            )
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    modifier = Modifier.weight(5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(width = 60.dp, height = 60.dp)
                            .clip(CircleShape)
                            .background(color = Color.Black)
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .size(width = 75.dp, height = 20.dp)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
                Row(
                    modifier = Modifier.weight(5f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .size(width = 75.dp, height = 20.dp)
                            .background(color = Color.LightGray.copy(0.8f))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TournamentShimmerPreview() {
    TournamentActivityShimmer()
}