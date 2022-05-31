package view

import Symbol
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionNaming")
@Composable
fun SideChoice(onClick: (Symbol) -> Unit) = Box(
    modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center
) {
    Column(modifier = Modifier.width(600.dp).height(800.dp)) {
        Button(
            onClick = { onClick(Symbol.CROSS) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0, 0, 0, 0))
        ) {
            Text(
                text = "X",
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = { onClick(Symbol.NOUGHT) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0, 0, 0, 0))
        ) {
            Text(
                text = "O",
                fontSize = 60.sp,
                textAlign = TextAlign.Center,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
