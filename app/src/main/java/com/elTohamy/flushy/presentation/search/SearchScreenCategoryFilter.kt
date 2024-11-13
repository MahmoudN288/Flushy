package com.elTohamy.flushy.presentation.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.elTohamy.flushy.R
import com.elTohamy.flushy.utils.Dimens

/** Created by github.com/im-o on 11/18/2023. */

@Composable
fun SearchScreenCategoryFilter(
    modifier: Modifier = Modifier,
) {
    var selectedCategory by remember { mutableIntStateOf(0) }
    val categories = listOf(
        "All",
        "Championships",
        "Players",
        "Teams"
    )
    /*val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val datastore = SearchedFilterDatastore(context)*/
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp20),
        contentPadding = PaddingValues(start = Dimens.dp16, end = Dimens.dp16),
    ) {
        items(count = categories.size) { index ->
            val isSelected = selectedCategory == index
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                animationSpec = tween(durationMillis = 500),
                label = stringResource(id = R.string.search)
            )
            Row(
                modifier = modifier
                    .wrapContentWidth()
                    .height(Dimens.dp30)
                    .shadow(elevation = Dimens.dp3, shape = RoundedCornerShape(Dimens.dp20))
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(Dimens.dp20),
                    )
                    .clickable {
                        selectedCategory = index
                        /*scope.launch {
                            datastore.saveIndex(selectedCategory.toString())
                        }*/
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = modifier.width(Dimens.dp24))
                Text(
                    modifier = modifier.wrapContentWidth(), text = categories[index], style = TextStyle(
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = modifier.width(Dimens.dp24))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryFilterPreview() {
    SearchScreenCategoryFilter()
}