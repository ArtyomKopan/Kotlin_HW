package homework.six

fun makeMove(buttons: List<Button>, buttonId: Int, symbol: Symbol): List<Button> {
    buttons[buttonId].symbol = symbol
    buttons[buttonId].isActivate = true
    return buttons
}

fun botChooseButton(buttons: List<Button>, unusedButtons: MutableList<Int>, symbol: Symbol): List<Button> {
    val buttonId = unusedButtons[unusedButtons.indices.random()]
    unusedButtons.remove(buttonId)
    return makeMove(buttons, buttonId, symbol)
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
        GameMode.BOT -> botChooseButton(buttons, unusedButtons, botSide)
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