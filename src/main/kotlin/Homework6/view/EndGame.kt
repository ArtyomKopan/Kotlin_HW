package view

import ViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionNaming")

@Composable
fun EndGame(state: ViewModel.State) =
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
        if (state.winStatus != WinStatus.CONTINUES) {
            state.screen = Screen.END_GAME
            when (state.winStatus) {
                WinStatus.CROSSES -> Text("Крестики выиграли!", fontSize = 35.sp)
                WinStatus.NOUGHTS -> Text("Нолики выиграли!", fontSize = 35.sp)
                WinStatus.BALANCE -> Text("Ничья!", fontSize = 35.sp)
            }
        }
    }

