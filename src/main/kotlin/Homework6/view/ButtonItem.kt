package homework.six

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
internal fun ButtonItem(state: ViewModel.State, button: Button, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.width(200.dp).height(200.dp).padding(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0, 0, 0, 0))
    ) {
        val outputSymbol = when (button.symbol) {
            Symbol.CROSS -> "X"
            Symbol.NOUGHT -> "O"
            else -> " "
        }
        val textColor = if (outputSymbol == "X") Color.Red else Color.Blue
        Text(
            outputSymbol,
            fontSize = 60.sp,
            textAlign = TextAlign.Center,
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        ShowWinDialogWindow(state)
    }
}
