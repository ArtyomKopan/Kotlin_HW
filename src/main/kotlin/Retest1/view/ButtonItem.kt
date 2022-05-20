package view

import Button
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Suppress("FunctionNaming")
@Composable
internal fun ButtonItem(
    button: Button,
    onClick: () -> Unit
) = Row(Modifier.clickable(onClick = onClick)) {

    Text(
        buildAnnotatedString {
            append("${button.name}\n")
        },
        modifier = Modifier.padding(10.dp)
    )
}
