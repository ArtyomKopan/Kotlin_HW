package view

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionNaming")
@Composable
internal fun ButtonItem(state: ViewModel.State, buttonId: Int, onClick: (Int) -> Unit) {
    val button = state.buttons[buttonId]
    var text by remember { mutableStateOf(button.symbol?.toString() ?: " ") }

    Button(
        onClick = { onClick(button.id); text = button.symbol.toString() },
        modifier = Modifier.width(200.dp).height(200.dp).padding(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0, 0, 0, 0))
    ) {
        val outputSymbol = if (text == "CROSS") "X" else if (text == "NOUGHT") "O" else " "
        val textColor = if (outputSymbol == "X") Color.Red else Color.Blue
        Text(
            outputSymbol,
            fontSize = 60.sp,
            textAlign = TextAlign.Center,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        // Тестовый вывод
        if (state.winStatus != WinStatus.CONTINUES) {
            state.screen = Screen.END_GAME
            when (state.winStatus) {
                WinStatus.CROSSES -> Text("Крестики выиграли!")
                WinStatus.NOUGHTS -> Text("Нолики выиграли!")
                WinStatus.BALANCE -> Text("Ничья!")
            }
        }
    }
}
