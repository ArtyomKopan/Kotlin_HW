package homework.six

fun makeMove(buttons: List<Button>, buttonId: Int, symbol: Symbol): List<Button> {
    buttons[buttonId].symbol = symbol
    buttons[buttonId].isActivate = true
    return buttons
}

fun botChooseButton(
    buttons: List<Button>,
    unusedButtons: MutableList<Int>,
    botSide: Symbol,
    gameMode: GameMode
): List<Button> {
    val userSide = if (botSide == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
    val buttonId = when (gameMode) {
        GameMode.BOT -> unusedButtons[unusedButtons.indices.random()]
        GameMode.STRATEGY_BOT -> minimax(buttons, userSide, botSide, botSide).second
        else -> -1
    }

    unusedButtons.remove(buttonId)
    return makeMove(buttons, buttonId, botSide)
}

fun botMakeMove(
    buttons: List<Button>,
    unusedButtons: MutableList<Int>,
    userSide: Symbol,
    gameMode: GameMode
): List<Button> {
    if (gameMode == GameMode.SINGLE || unusedButtons.isEmpty()) {
        return buttons
    }
    val botSide = if (userSide == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
    return when (gameMode) {
        GameMode.BOT, GameMode.STRATEGY_BOT -> botChooseButton(buttons, unusedButtons, botSide, gameMode)
        else -> buttons
    }
}

fun makeStartMove(buttons: List<Button>, userSide: Symbol, gameMode: GameMode): List<Button> =
    when (gameMode) {
        GameMode.SINGLE -> buttons
        else -> when (userSide) {
            Symbol.CROSS -> buttons
            Symbol.NOUGHT -> botMakeMove(buttons, buttons.indices.toMutableList(), userSide, gameMode)
        }
    }
