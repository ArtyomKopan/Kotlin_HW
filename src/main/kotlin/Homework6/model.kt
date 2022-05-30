const val BUTTONS_COUNT = 9

fun fetchButtons() = (0 until BUTTONS_COUNT).map { Button(it) }.toList()

fun getWinStatus(buttons: List<Button>): WinStatus {
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

    return when {
        isHorizontalCrossWin || isVerticalCrossWin || isMainDiagonalCrossWin || isOtherDiagonalCrossWin ->
            WinStatus.CROSSES
        isHorizontalNoughtsWin || isVerticalNoughtsWin || isMainDiagonalNoughtsWin || isOtherDiagonalNoughtsWin ->
            WinStatus.NOUGHTS
        isBalance -> WinStatus.BALANCE
        else -> WinStatus.CONTINUES
    }
}
