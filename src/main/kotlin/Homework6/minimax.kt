package homework.six

const val WIN_SCORE = 10
const val FAIL_SCORE = -10
const val BALANCE_SCORE = 0

fun emptyIndices(buttons: List<Button>) = buttons.filter { it.symbol == null }.map { it.id }.toMutableList()

fun score(buttons: List<Button>, botSide: Symbol): Int {
    val status = getWinStatus(buttons)
    return when {
        botSide == Symbol.CROSS && status == WinStatus.CROSSES -> WIN_SCORE
        botSide == Symbol.NOUGHT && status == WinStatus.NOUGHTS -> WIN_SCORE
        botSide == Symbol.CROSS && status == WinStatus.NOUGHTS -> FAIL_SCORE
        botSide == Symbol.NOUGHT && status == WinStatus.CROSSES -> FAIL_SCORE
        else -> BALANCE_SCORE
    }
}

fun minimax(
    buttons: List<Button>,
    userSide: Symbol,
    botSide: Symbol,
    currentSymbol: Symbol
): Pair<Int, Int> {
    // currentSymbol - показывает, чей сейчас ход
    if (getWinStatus(buttons) != WinStatus.CONTINUES) {
        return Pair(score(buttons, botSide), -1)
    }

    val nextSymbol = if (currentSymbol == userSide) botSide else userSide

    val moves = mutableMapOf<Int, Int>() // key == move, value == score

    for (move in emptyIndices(buttons)) {
        buttons[move].symbol = nextSymbol
        val score = minimax(buttons, userSide, botSide, nextSymbol).first
        moves[move] = score
        buttons[move].symbol = null
    }

    return if (currentSymbol == botSide) {
        val maxScore = moves.maxOf { it.value }
        val maxScoreIndex = moves.filter { it.value == maxScore }.map { it.key }.first()
        Pair(maxScore, maxScoreIndex)
    } else {
        val minScore = moves.minOf { it.value }
        val minScoreIndex = moves.filter { it.value == minScore }.map { it.key }.first()
        Pair(minScore, minScoreIndex)
    }
}
