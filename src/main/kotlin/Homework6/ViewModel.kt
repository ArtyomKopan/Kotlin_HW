import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val buttons: List<Button>,
        var currentSymbol: Symbol, // показывает, чей сейчас ход
        var screen: Screen,
        var winStatus: WinStatus,
        var userSide: Symbol, // в режиме бота показывает, за какую сторону играет игрок
        val unusedButtons: MutableList<Int>, // в режиме бота содержит № ещё не использованных кнопок
        var gameMode: GameMode,
        var moveNumber: Int
    )

    private fun initialState(): State = State(
        buttons = fetchButtons(),
        currentSymbol = Symbol.CROSS,
        screen = Screen.START_GAME,
        winStatus = WinStatus.CONTINUES,
        userSide = Symbol.CROSS,
        unusedButtons = (0 until BUTTONS_COUNT).toMutableList(),
        gameMode = GameMode.SINGLE,
        moveNumber = 0
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onButtonSelect(buttonId: Int) = updateState {
        if (moveNumber == 1 && gameMode == GameMode.BOT && userSide == Symbol.NOUGHT) {
            currentSymbol = Symbol.NOUGHT
        }
        if (!buttons[buttonId].isActivate) {
            val buttonsAfterUserMove = makeMove(buttons, buttonId, currentSymbol)
            unusedButtons.remove(buttonId)
            isWin()
            var buttonsAfterBotMove: List<Button> = buttonsAfterUserMove
            if (gameMode == GameMode.BOT && winStatus == WinStatus.CONTINUES) {
                buttonsAfterBotMove = botMakeMove(buttonsAfterUserMove, unusedButtons, userSide, gameMode)
                isWin()
            }
            if (gameMode == GameMode.SINGLE) {
                currentSymbol = if (currentSymbol == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
            }
            copy(
                currentSymbol = if (userSide == Symbol.NOUGHT) Symbol.NOUGHT else currentSymbol,
                buttons = buttonsAfterBotMove,
                winStatus = winStatus,
                moveNumber = if (gameMode == GameMode.SINGLE) moveNumber + 1 else moveNumber + 2
            )
        } else {
            copy(currentSymbol = currentSymbol)
        }
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

    fun onChoiceSide(userSide: Symbol) = updateState {
        copy(
            userSide = userSide,
            screen = Screen.GAME_FIELD,
            buttons = makeStartMove(buttons, userSide, gameMode),
            moveNumber = if (userSide == Symbol.NOUGHT) 1 else 0
        )
    }

    private fun isWin() = updateState {
        winStatus = getWinStatus(buttons)
        copy(winStatus = winStatus)
    }
}
