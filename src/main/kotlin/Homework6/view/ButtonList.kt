package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionNaming", "MagicNumber")
@Composable
fun ButtonsList(
    state: ViewModel.State,
    onButtonSelect: (Int) -> Unit,
) = Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {

    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Row(modifier = Modifier.fillMaxWidth().height(250.dp).padding(20.dp)) {
            ButtonItem(state, 0, onClick = onButtonSelect)
            ButtonItem(state, 1, onClick = onButtonSelect)
            ButtonItem(state, 2, onClick = onButtonSelect)
        }
        Row(modifier = Modifier.fillMaxWidth().height(250.dp).padding(20.dp)) {
            ButtonItem(state, 3, onClick = onButtonSelect)
            ButtonItem(state, 4, onClick = onButtonSelect)
            ButtonItem(state, 5, onClick = onButtonSelect)
        }
        Row(modifier = Modifier.fillMaxWidth().height(250.dp).padding(20.dp)) {
            ButtonItem(state, 6, onClick = onButtonSelect)
            ButtonItem(state, 7, onClick = onButtonSelect)
            ButtonItem(state, 8, onClick = onButtonSelect)
        }
    }
}
