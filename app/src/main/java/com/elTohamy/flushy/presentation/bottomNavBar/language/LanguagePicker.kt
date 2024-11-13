package com.elTohamy.flushy.presentation.bottomNavBar.language

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elTohamy.flushy.R
import com.elTohamy.flushy.presentation.customTheme.customColorsPalette
import com.elTohamy.flushy.utils.findActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagePicker(
    navController: NavHostController, onDismiss: ()-> Unit
) {
    val list = listOf(
        Languages(id = 0, title = stringResource(id = R.string.english),
            tag = "en"),
        Languages(id = 1, title = stringResource(id = R.string.deutsch),
            tag = "de"),
        Languages(id = 2, title = stringResource(id = R.string.arabic),
            tag = "ar")
    )

    val context = LocalContext.current //context
    var tag by remember { mutableStateOf("en") }
    val onClickRefreshActivityEnglish = {
        //context.findActivity() is kotlin extension function
        context.findActivity()?.runOnUiThread {
            val appLocale = LocaleListCompat.forLanguageTags(tag) //here ta is hardcoded for testing purpose,you can add users selected language code.
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }
    val listState = rememberLazyListState()
    var selectedIndex by remember{ mutableIntStateOf(-1) }

    BasicAlertDialog(
        modifier = Modifier.background(Color.Transparent)
            .clip(RoundedCornerShape(15.dp)),
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier
                .background(
                    Color.Transparent
                )
        ) {
            Card(
                modifier = Modifier,
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.customColorsPalette
                        .matchDetailsCardContainer
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                LazyColumn(state = listState, userScrollEnabled = false) {
                    items(items = list) { language ->
                        LanguageRowItem(
                            onClick = {
                                selectedIndex = if (selectedIndex != language.id)
                                    language.id else -1
                                onDismiss()
                                tag = language.tag
                                onClickRefreshActivityEnglish()
                            },
                            title = language.title,
                            chosen = language.id == selectedIndex
                        )
                    }
                }
            }
        }
    }
}

data class Languages(val id: Int, val title: String, val tag: String)

@Composable
fun LanguageRowItem(
    onClick: ()-> Unit, title: String, chosen: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = title,
                style = TextStyle(
                    color =
                    if (chosen) MaterialTheme.colorScheme.primary
                    else MaterialTheme.customColorsPalette.blackToWhite,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguagePickerPrev() {
    LanguagePicker(navController = rememberNavController()) {}
}