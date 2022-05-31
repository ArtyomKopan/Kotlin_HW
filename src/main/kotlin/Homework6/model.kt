const val BUTTONS_COUNT = 9

fun fetchButtons() = (0 until BUTTONS_COUNT).map { Button(it) }.toList()

fun getWinStatus(buttons: List<Button>): WinStatus {
    val (isHorizontalCrossWin, isHorizontalNoughtsWin) = checkHorizontals(buttons)
    val (isVerticalCrossWin, isVerticalNoughtsWin) = checkVerticals(buttons)
    val (isDiagonalCrossWin, isDiagonalNoughtsWin) = checkDiagonals(buttons)
    val isBalance = checkBalance(buttons)

    return when {
        isHorizontalCrossWin || isVerticalCrossWin || isDiagonalCrossWin -> WinStatus.CROSSES
        isHorizontalNoughtsWin || isVerticalNoughtsWin || isDiagonalNoughtsWin -> WinStatus.NOUGHTS
        isBalance -> WinStatus.BALANCE
        else -> WinStatus.CONTINUES
    }
}

@Suppress("MagicNumber")
fun checkHorizontals(buttons: List<Button>): Pair<Boolean, Boolean> {
    var isHorizontalCrossWin = true
    var isHorizontalNoughtsWin = true
    for (i in 0..2) {
        if (i == 0 || (!isHorizontalCrossWin && !isHorizontalNoughtsWin)) {
            isHorizontalCrossWin = true
            isHorizontalNoughtsWin = true
            (3 * i..3 * i + 2).forEach {
                isHorizontalCrossWin = isHorizontalCrossWin and (buttons[it].symbol == Symbol.CROSS)
                isHorizontalNoughtsWin = isHorizontalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
            }
        }
    }
    return Pair(isHorizontalCrossWin, isHorizontalNoughtsWin)
}

@Suppress("MagicNumber")
fun checkVerticals(buttons: List<Button>): Pair<Boolean, Boolean> {
    var isVerticalCrossWin = true
    var isVerticalNoughtsWin = true
    for (i in 0..2) {
        if (i == 0 || (!isVerticalCrossWin && !isVerticalNoughtsWin)) {
            isVerticalCrossWin = true
            isVerticalNoughtsWin = true
            listOf(i, i + 3, i + 6).forEach {
                isVerticalCrossWin = isVerticalCrossWin and (buttons[it].symbol == Symbol.CROSS)
                isVerticalNoughtsWin = isVerticalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
            }
        }
    }
    return Pair(isVerticalCrossWin, isVerticalNoughtsWin)
}

@Suppress("MagicNumber")
fun checkDiagonals(buttons: List<Button>): Pair<Boolean, Boolean> {
    var isMainDiagonalCrossWin = true
    var isMainDiagonalNoughtsWin = true
    var isOtherDiagonalCrossWin = true
    var isOtherDiagonalNoughtsWin = true
    listOf(0, 4, 8).forEach {
        isMainDiagonalCrossWin = isMainDiagonalCrossWin and (buttons[it].symbol == Symbol.CROSS)
        isMainDiagonalNoughtsWin = isMainDiagonalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
    }
    listOf(2, 4, 6).forEach {
        isOtherDiagonalCrossWin = isOtherDiagonalCrossWin and (buttons[it].symbol == Symbol.CROSS)
        isOtherDiagonalNoughtsWin = isOtherDiagonalNoughtsWin and (buttons[it].symbol == Symbol.NOUGHT)
    }
    return Pair(
        isMainDiagonalCrossWin || isOtherDiagonalCrossWin, isMainDiagonalNoughtsWin || isOtherDiagonalNoughtsWin
    )
}

fun checkBalance(buttons: List<Button>): Boolean {
    var isBalance = true
    buttons.forEach { isBalance = isBalance and (it.symbol != null) }
    return isBalance
}
