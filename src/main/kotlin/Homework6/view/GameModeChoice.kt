package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun GameModeChoice(onClick1: () -> Unit, onClick2: () -> Unit) = Box(
    modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center
) {
    Column(modifier = Modifier.width(600.dp).height(800.dp)) {
        Button(
            onClick = onClick1,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Одиночная игра", textAlign = TextAlign.Center, fontSize = 35.sp)
        }

        Button(
            onClick = onClick2,
            modifier = Modifier.fillMaxWidth().padding(0.dp, 100.dp)
        ) {
            Text("Игра с ботом", textAlign = TextAlign.Center, fontSize = 35.sp)
        }
    }
}
