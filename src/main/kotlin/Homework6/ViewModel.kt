import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val buttons: List<Button>,
        val selectedButton: Button?,
        var currentSymbol: Symbol, // показывает, чей сейчас ход
        var screen: Screen,
        var winStatus: WinStatus
    )

    private fun initialState(): State = State(
        buttons = fetchButtons(),
        selectedButton = null,
        currentSymbol = Symbol.CROSS,
        screen = Screen.START_GAME,
        winStatus = WinStatus.CONTINUES
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onButtonSelect(buttonId: Int) = updateState { // ход на игровом поле
        copy(selectedButton = buttons[buttonId])
        if (!buttons[buttonId].isActivate) {
            buttons[buttonId].symbol = currentSymbol
            buttons[buttonId].isActivate = true
            currentSymbol = if (currentSymbol == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
        }
        isWin()
        copy(currentSymbol = currentSymbol)
    }

    fun onStartGame() = updateState {
        copy(screen = Screen.GAME_FIELD)
    }

    fun isWin() = updateState {
        var isHorizontalCrossWin = true
        var isHorizontalNoughtsWin = true
        var isVerticalCrossWin = true
        var isVerticalNoughtsWin = true
        var isMainDiagonalCrossWin = true
        var isMainDiagonalNoughtsWin = true
        var isOtherDiagonalCrossWin = true
        var isOtherDiagonalNoughtsWin = true

        for (i in 0..2) {
            // проверяем горизонтали
            if (i == 0 || (!isHorizontalCrossWin && !isHorizontalNoughtsWin)) {
                isHorizontalCrossWin = true
                isHorizontalNoughtsWin = true
                (3 * i..3 * i + 2).forEach {
                    isHorizontalCrossWin = isHorizontalCrossWin and (buttons[it].symbol == Symbol.CROSS)
                    isHorizontalNoughtsWin = isHorizontalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
                }
            }
            // проверяем вертикали
            if (i == 0 || (!isVerticalCrossWin && !isVerticalNoughtsWin)) {
                isVerticalCrossWin = true
                isVerticalNoughtsWin = true
                listOf(i, i + 3, i + 6).forEach {
                    isVerticalCrossWin = isVerticalCrossWin and (buttons[it].symbol == Symbol.CROSS)
                    isVerticalNoughtsWin = isVerticalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
                }
            }
        }

        // проверяем диагонали
        listOf(0, 4, 8).forEach {
            isMainDiagonalCrossWin = isMainDiagonalCrossWin and (buttons[it].symbol == Symbol.CROSS)
            isMainDiagonalNoughtsWin = isMainDiagonalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
        }
        listOf(2, 4, 6).forEach {
            isOtherDiagonalCrossWin = isOtherDiagonalCrossWin and (buttons[it].symbol == Symbol.CROSS)
            isOtherDiagonalNoughtsWin = isOtherDiagonalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
        }

        // проверяем на ничью
        var isBalance = true
        buttons.forEach { isBalance = isBalance and (it.symbol != null) }

        winStatus = when {
            isHorizontalCrossWin || isVerticalCrossWin || isMainDiagonalCrossWin || isOtherDiagonalCrossWin ->
                WinStatus.CROSSES
            isHorizontalNoughtsWin || isVerticalNoughtsWin || isMainDiagonalNoughtsWin || isOtherDiagonalNoughtsWin ->
                WinStatus.NOUGHTS
            isBalance -> WinStatus.BALANCE
            else -> WinStatus.CONTINUES
        }
        copy(winStatus = winStatus)
    }
}
