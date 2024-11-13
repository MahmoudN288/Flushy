package com.elTohamy.flushy.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette

@Composable
fun NotAvailableScreen() {
    val dark = LocalTheme.current.isDark
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                modifier = Modifier.size(width = 90.dp, height = 70.dp),
                painter = if (dark) painterResource(id = R.drawable.not_found_w) else painterResource(id = R.drawable.not_found_b),
                contentDescription = "Not Found"
            )
            Text(
                modifier = Modifier,
                text = "No data for this title",
                style = TextStyle(
                    color = MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            )
        }
    }
}