package view

import ViewModel
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Suppress("FunctionNaming")
@Composable
fun CheckWin(state: ViewModel.State) {
    if (state.winStatus != WinStatus.CONTINUES) {
        state.screen = Screen.END_GAME
        when (state.winStatus) {
            WinStatus.CROSSES -> Text("Крестики выиграли!", color = Color.Black)
            WinStatus.NOUGHTS -> Text("Нолики выиграли!", color = Color.Black)
            WinStatus.BALANCE -> Text("Ничья!", color = Color.Black)
        }
    }
}
