package com.elTohamy.flushy.presentation.activities.welcoming.components

import android.view.SoundEffectConstants
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Icon
import kotlinx.coroutines.launch

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    titleColor: Color =
        if (selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface.copy(0.2f),
    titleWeight: FontWeight = FontWeight.Normal,
    titleSize: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
    subTitle: String ?= null,
    subTitleColor: Color =
        if (selected) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.onSurface.copy(0.2f),
    subTitleWeight: FontWeight = FontWeight.Bold,
    subTitleSize: TextUnit = MaterialTheme.typography.headlineSmall.fontSize,
    icon: ImageVector = Icons.Filled.CheckCircle,
    iconColor: Color =
        if (selected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.onSurface.copy(0.2f),
    borderWidth: Dp = 1.dp,
    borderColor: Color =
        if (selected) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.onSurface.copy(0.2f),
    borderShape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit
) {
    //To Animate Only Check Icon
    val scaleA = remember { androidx.compose.animation.core.Animatable(initialValue = 1f) }
    //To Animate The Whole Column
    val scaleB = remember { androidx.compose.animation.core.Animatable(initialValue = 1f) }
    val clickEnabled = remember { mutableStateOf(true) }

    LaunchedEffect(key1 = selected) {
        if (selected) {
            clickEnabled.value = false
            val stepA = launch {
                scaleA.animateTo(
                    targetValue = 0.3f,
                    animationSpec = tween(durationMillis = 50)
                )
                scaleA.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            val stepB = launch {
                scaleB.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(durationMillis = 50)
                )
                scaleB.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            stepA.join()
            stepB.join()
            clickEnabled.value = true
        }
    }

    var expandedState by remember { mutableStateOf(false) }

    val view = LocalView.current
    Card(
        modifier = modifier
            .scale(scaleB.value)
            .border(width = borderWidth, color = borderColor, shape = borderShape)
            .clip(borderShape)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
            view.playSoundEffect(SoundEffectConstants.CLICK)
            onClick()
        }
    ) {
        Column{
            Row(
                modifier = modifier.padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(8f),
                    text = title,
                    style = TextStyle(
                        color = titleColor,
                        fontSize = titleSize,
                        fontWeight = titleWeight
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Visible
                )

                IconButton(
                    modifier = modifier
                        .weight(2f)
                        .scale(scaleA.value),
                    onClick = {
                        onClick()
                        expandedState = !expandedState
                    }
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Check Icon",
                        tint = iconColor
                    )
                }
            }
            if (subTitle != null && expandedState) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    text = subTitle,
                    style = TextStyle(
                        color = subTitleColor,
                        fontSize = subTitleSize,
                        fontWeight = subTitleWeight
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}