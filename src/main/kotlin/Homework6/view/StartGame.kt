package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("FunctionNaming")
@Composable
fun StartGame(onClick: () -> Unit) = Column(modifier = Modifier.fillMaxWidth()) {
    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        Button(
            onClick,
            modifier = Modifier.width(300.dp).height(180.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
            Text(
                "Начать игру",
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}
