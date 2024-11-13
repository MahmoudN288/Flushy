package com.elTohamy.flushy.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.LocalTheme

@Composable
fun NotAvailable(
    @DrawableRes icon: Int,
    @StringRes shortText: Int,
    modifier: Modifier = Modifier,
    longText: String? = null
) {
    val dark = LocalTheme.current.isDark
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp),
                painter = painterResource(id = icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(
                    if (longText != null) 5.dp else 0.dp
                ),
                text = stringResource(id = shortText),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.customColorsPalette.blackToWhite,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            if (longText != null) {
                Text(
                    text = longText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.customColorsPalette.blackToWhite.copy(
                        if (dark) 0.9f else 0.7f
                    ),
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotAvailablePreview() {
    NotAvailable(
        icon = R.drawable.no_stats,
        shortText = R.string.noStandings,
        longText = "Standings not available for this tournament"
    )
}