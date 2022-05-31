package view

import ViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionNaming", "MagicNumber")
@Composable
fun ButtonsList(state: ViewModel.State, onButtonSelect: (Int) -> Unit) =
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            for (i in 0..2) {
                Row(modifier = Modifier.fillMaxWidth().height(250.dp).padding(20.dp)) {
                    for (j in 0..2) {
                        ButtonItem(state, state.buttons[3 * i + j], onClick = { onButtonSelect(3 * i + j) })
                    }
                }
            }
        }
    }

@Suppress("FunctionNaming", "OPT_IN_IS_NOT_ENABLED", "MagicNumber")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowWinDialogWindow(state: ViewModel.State) {
    if (state.winStatus == WinStatus.CONTINUES)
        return

    val winMessage = generateWinMessage(state)

    AlertDialog(
        onDismissRequest = { },
        title = null,
        text = {
            Text(
                text = winMessage,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(78, 169, 124),
                textAlign = TextAlign.Center
            )
        },
        buttons = { },
        modifier = Modifier.background(color = Color(0, 0, 0, 0))
    )
}

fun generateWinMessage(state: ViewModel.State): String =
    if (state.gameMode == GameMode.SINGLE) {
        when (state.winStatus) {
            WinStatus.CROSSES -> "Крестики выиграли!"
            WinStatus.NOUGHTS -> "Нолики выиграли!"
            WinStatus.BALANCE -> "Ничья!"
            else -> ""
        }
    } else {
        when {
            state.winStatus == WinStatus.CROSSES && state.userSide == Symbol.CROSS -> "Вы выиграли!"
            state.winStatus == WinStatus.NOUGHTS && state.userSide == Symbol.NOUGHT -> "вы выиграли!"
            state.winStatus == WinStatus.BALANCE -> "Ничья!"
            else -> "Вы проиграли!"
        }
    }
