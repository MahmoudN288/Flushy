package com.elTohamy.flushy.presentation.uiMode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elTohamy.flushy.presentation.ui.theme.FlushyTheme

data class RadioButtonItem(
    val id: Int,
    val title: String,
)

@Composable
fun RadioGroup(
    items: Iterable<RadioButtonItem>,
    selected: Int,
    onItemSelect: ((Int) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.selectableGroup()
    ) {
        items.forEach { item ->
            RadioGroupItem(
                item = item,
                selected = selected == item.id,
                onClick = { onItemSelect?.invoke(item.id) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun RadioGroupItem(
    item: RadioButtonItem,
    selected: Boolean,
    onClick: ((Int) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = { onClick?.invoke(item.id) },
                role = Role.RadioButton
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RadioItemPreview() {
    FlushyTheme {
        Surface(Modifier.width(256.dp)) {
            RadioGroupItem(
                item = RadioButtonItem(
                    id = 1,
                    title = "Dark Theme",
                ),
                selected = true,
                onClick = null
            )
        }
    }
}

private val radioItems = listOf(
    RadioButtonItem(
        id = 1,
        title = "Light Theme",
    ),
    RadioButtonItem(
        id = 2,
        title = "Dark Theme",
    ),
    RadioButtonItem(
        id = 3,
        title = "Auto Theme",
    ),
)

@Preview(showBackground = true)
@Composable
private fun RadioGroupPreview() {
    FlushyTheme {
        var selected by remember {
            mutableIntStateOf(2)
        }
        Surface(Modifier.width(256.dp)) {
            RadioGroup(
                items = radioItems,
                selected = selected,
                onItemSelect = { id -> selected = id }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RadioGroupDarkPreview() {
    FlushyTheme(darkTheme = true) {
        var selected by remember {
            mutableIntStateOf(2)
        }
        Surface(Modifier.width(256.dp)) {
            RadioGroup(
                items = radioItems,
                selected = selected,
                onItemSelect = { id -> selected = id }
            )
        }
    }
}