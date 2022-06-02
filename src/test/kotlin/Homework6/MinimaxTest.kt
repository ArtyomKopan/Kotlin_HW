package homework.six

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MinimaxTest {
    @Test
    fun minimaxTest1() {
        val buttons = (0 until BUTTONS_COUNT).map { Button(it) }.toList()
        listOf(1, 4, 5, 8).forEach { buttons[it].symbol = Symbol.CROSS }
        listOf(2, 3, 7).forEach { buttons[it].symbol = Symbol.NOUGHT }
        val botSide = Symbol.NOUGHT
        val userSide = Symbol.CROSS
        assertEquals(0, minimax(buttons, userSide, botSide, botSide).second)
    }

    @Test
    fun minimaxTest2() {
        val buttons = (0 until BUTTONS_COUNT).map { Button(it) }.toList()
        listOf(1, 2, 8).forEach { buttons[it].symbol = Symbol.CROSS }
        listOf(0, 4).forEach { buttons[it].symbol = Symbol.NOUGHT }
        val botSide = Symbol.NOUGHT
        val userSide = Symbol.CROSS
        assertEquals(3, minimax(buttons, userSide, botSide, botSide).second)
    }

    @Test
    fun minimaxTest3() {
        val buttons = (0 until BUTTONS_COUNT).map { Button(it) }.toList()
        listOf(1, 2).forEach { buttons[it].symbol = Symbol.CROSS }
        listOf(4, 5).forEach { buttons[it].symbol = Symbol.NOUGHT }
        val botSide = Symbol.CROSS
        val userSide = Symbol.NOUGHT
        assertEquals(0, minimax(buttons, userSide, botSide, botSide).second)
    }
}
