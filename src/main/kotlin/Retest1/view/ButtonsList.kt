package view

import Button
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("FunctionNaming")

@Composable
fun ButtonsList(
    buttons: List<Button>,
    actionText: List<String>,
    onButtonSelect: (Int) -> Unit,
) = Box {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(buttons) {
            ButtonItem(
                it,
                onClick = { onButtonSelect(it.id) }
            )
            Divider()
        }
    }

    VerticalScrollbar(
        modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
        adapter = rememberScrollbarAdapter(scrollState = listState)
    )

    var finalText = ""
    actionText.forEach { finalText += it }

    Text(
        text = finalText,
        modifier = Modifier.padding(200.dp)
    )
}
