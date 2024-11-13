package com.elTohamy.flushy.presentation.animations.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elTohamy.flushy.presentation.animations.shimmer.shimmerLoadingAnimation
import com.valentinilk.shimmer.shimmer

@Composable
fun LiveGamesShimmer() {
    LiveGamesShimmerScrollableContent()
}

@Composable
fun LiveGamesShimmerScrollableContent() {
    val isLoadingCompleted by remember { mutableStateOf(true) }
    val isLightModeActive by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .shimmer()
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 20.dp, end = 20.dp, top = 48.dp)
        ) {
            Column(
                modifier = Modifier.align(alignment = Alignment.TopCenter)
            ) {

                Column {
                    ComponentRectangle(
                        isLoadingCompleted, isLightModeActive
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    ComponentRectangleLineLong(
                        isLoadingCompleted, isLightModeActive
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }

                Row {
                    Column {
                        ComponentRectangleLineLong(
                            isLoadingCompleted, isLightModeActive
                        )
                    }
                }

                Row {
                    Column {
                        Spacer(modifier = Modifier.padding(4.dp))
                        ComponentRectangleLineLong(
                            isLoadingCompleted, isLightModeActive
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Column(
                modifier = Modifier.align(alignment = Alignment.TopCenter)
            ) {

                Column {
                    ComponentRectangle(
                        isLoadingCompleted, isLightModeActive
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    ComponentRectangleLineLong(
                        isLoadingCompleted, isLightModeActive
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }

                Row {
                    Column {
                        ComponentRectangleLineLong(
                            isLoadingCompleted, isLightModeActive
                        )
                    }
                }

                Row {
                    Column {
                        Spacer(modifier = Modifier.padding(4.dp))
                        ComponentRectangleLineLong(
                            isLoadingCompleted, isLightModeActive
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            Column(
                modifier = Modifier.align(alignment = Alignment.TopCenter)
            ) {

                Column {
                    ComponentRectangle(
                        isLoadingCompleted, isLightModeActive
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    ComponentRectangleLineLong(
                        isLoadingCompleted, isLightModeActive
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }

                Row {
                    Column {
                        ComponentRectangleLineLong(
                            isLoadingCompleted, isLightModeActive
                        )
                    }
                }

                Row {
                    Column {
                        Spacer(modifier = Modifier.padding(4.dp))
                        ComponentRectangleLineLong(
                            isLoadingCompleted, isLightModeActive
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentRectangle(
    isLoadingCompleted: Boolean,
    isLightModeActive: Boolean
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.LightGray.copy(0.6f))
            .height(40.dp)
            .fillMaxWidth()
            .shimmerLoadingAnimation(
                isLoadingCompleted, isLightModeActive
            ) // <--- Here.
    )
}

@Composable
fun ComponentRectangleLineLong(
    isLoadingCompleted: Boolean,
    isLightModeActive: Boolean
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.LightGray.copy(0.6f))
            .fillMaxWidth()
            .height(60.dp)
            .shimmerLoadingAnimation(
                isLoadingCompleted, isLightModeActive
            ) // <--- Here.
    )
}

@Preview(showBackground = true)
@Composable
fun LiveGamesShimmerPreview() {
    LiveGamesShimmer()
}