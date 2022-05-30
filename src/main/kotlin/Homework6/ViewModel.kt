import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import view.CheckWin

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val buttons: List<Button>,
        var currentSymbol: Symbol, // показывает, чей сейчас ход
        var screen: Screen,
        var winStatus: WinStatus,
        var userSide: Symbol?, // в режиме бота показывает, за какую сторону играет игрок
        val unusedButtons: MutableList<Int>, // в режиме бота содержит № ещё не использованных кнопок
        var gameMode: GameMode
    )

    private fun initialState(): State = State(
        buttons = fetchButtons(),
        currentSymbol = Symbol.CROSS,
        screen = Screen.START_GAME,
        winStatus = WinStatus.CONTINUES,
        userSide = null,
        unusedButtons = (0 until BUTTONS_COUNT).toMutableList(),
        gameMode = GameMode.SINGLE
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onButtonSelect(buttonId: Int) = updateState { // ход на игровом поле
        if (!buttons[buttonId].isActivate) {
            buttons[buttonId].symbol = currentSymbol
            buttons[buttonId].isActivate = true
            currentSymbol = if (currentSymbol == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
            unusedButtons.remove(buttonId)
            isWin()
        }
        // isWin()
        copy(currentSymbol = currentSymbol)
    }

    fun onStartGame() = updateState {
        copy(screen = Screen.MODE_CHOICE)
    }

    fun onChoiceSingleMode() = updateState {
        gameMode = GameMode.SINGLE
        copy(screen = Screen.GAME_FIELD)
    }

    fun onChoiceBotMode() = updateState {
        gameMode = GameMode.BOT
        copy(screen = Screen.SIDE_CHOICE)
    }

    fun onChoiceCrosses() = updateState {
        screen = Screen.GAME_FIELD
        copy(userSide = Symbol.CROSS)
    }

    fun onChoiceNoughts() = updateState {
        screen = Screen.GAME_FIELD
        copy(userSide = Symbol.NOUGHT)
    }

    private fun isWin() = updateState {
        winStatus = getWinStatus(buttons)
        copy(winStatus = winStatus)
    }
}
