package homework.six

import kotlin.math.max

fun isEven(x: Int) = x % 2 == 0

fun emptyIndices(buttons: List<Button>) = buttons.filter { it.symbol == null }.map { it.id }.toMutableList()

fun minimaxScore(
    buttons: List<Button>,
    userSide: Symbol,
    botSide: Symbol,
    iterationNumber: Int
): Int {
    val status = getWinStatus(buttons)
    val score = when {
        status == WinStatus.CROSSES && userSide == Symbol.CROSS -> -10
        status == WinStatus.CROSSES && userSide == Symbol.NOUGHT -> 10
        status == WinStatus.NOUGHTS && userSide == Symbol.CROSS -> 10
        status == WinStatus.NOUGHTS && userSide == Symbol.NOUGHT -> -10
        status == WinStatus.BALANCE -> 0
        else -> null
    }
    if (score != null) {
        return score
    }

    val indices = emptyIndices(buttons)
    var newScore = -10
    for (index in indices) {
        buttons[index].symbol = if (isEven(iterationNumber)) botSide else userSide
        newScore = max(newScore, minimaxScore(buttons, userSide, botSide, iterationNumber + 1))
        buttons[index].symbol = null
    }

    return newScore
}

fun minimax(buttons: List<Button>, userSide: Symbol): Int {
    val newButtons = buttons.toMutableList()
    val botSide = if (userSide == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
    val indices = emptyIndices(buttons)
    val moves = mutableMapOf<Int, Int>() // оценка для каждой пустой клетки

    for (index in indices) {
        newButtons[index].symbol = botSide
        moves[index] = minimaxScore(newButtons, userSide, botSide, 1)
        newButtons[index].symbol = null
    }

    val maxScore = moves.maxOf { it.value }
    val bestMove = moves.filter { it.value == maxScore }.map { it.key }.toList().first()
    return bestMove
}

// для отладки
fun minimaxList(buttons: List<Button>, userSide: Symbol): List<Pair<Int, Int>> {
    val newButtons = buttons.toMutableList()
    val botSide = if (userSide == Symbol.CROSS) Symbol.NOUGHT else Symbol.CROSS
    val indices = emptyIndices(buttons)
    val moves = mutableMapOf<Int, Int>() // оценка для каждой пустой клетки

    for (index in indices) {
        newButtons[index].symbol = botSide
        moves[index] = minimaxScore(newButtons, userSide, botSide, 1)
        newButtons[index].symbol = null
    }

    val maxScore = moves.maxOf { it.value }
    val bestMove = moves.filter { it.value == maxScore }.map { Pair(it.key, it.value) }.toList()
    return bestMove
}
