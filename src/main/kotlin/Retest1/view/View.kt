package retest.two

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Suppress("FunctionNaming")
@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state

    Column {
        MaterialTheme {
            ButtonsList(
                state.buttons,
                state.text,
                viewModel::onButtonSelect
            )
        }
    }
}
