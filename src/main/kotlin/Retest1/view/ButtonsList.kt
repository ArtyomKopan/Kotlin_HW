package retest.two

import Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

    var finalText = ""
    actionText.forEach { finalText += it }

    Text(
        text = finalText,
        modifier = Modifier.padding(180.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 40,
        textAlign = TextAlign.Justify,
    )
}
