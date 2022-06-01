package homework.six

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class MinimaxTest {

    /*@Test
    fun minimaxScoreTest() {
        val buttons = (0 until BUTTONS_COUNT).map { Button(it) }.toList()
        listOf(0, 3, 4).forEach { buttons[it].symbol = Symbol.CROSS }
        listOf(1, 8).forEach { buttons[it].symbol = Symbol.NOUGHT }
        val userSide = Symbol.CROSS
        val botSide = Symbol.NOUGHT
        val score = minimaxScore(buttons, userSide, botSide, 1)
        assertEquals(-10, score)
    }*/


    @Test
    fun minimaxTest1() {
        val buttons = (0 until BUTTONS_COUNT).map { Button(it) }.toList()
        listOf(1, 4, 5, 8).forEach { buttons[it].symbol = Symbol.CROSS }
        listOf(2, 3, 7).forEach { buttons[it].symbol = Symbol.NOUGHT }
        val userSide = Symbol.CROSS
        val bestMove = minimax(buttons, userSide)
        assertEquals(0, bestMove)
    }

    @Test
    fun minimaxTest2() {
        val buttons = (0 until BUTTONS_COUNT).map { Button(it) }.toList()
        listOf(0, 3, 4).forEach { buttons[it].symbol = Symbol.CROSS }
        listOf(1, 8).forEach { buttons[it].symbol = Symbol.NOUGHT }
        val userSide = Symbol.CROSS
        val bestMove = minimaxList(buttons, userSide)
        bestMove.forEach { println("${it.first} ${it.second}") }
        assertEquals(4, 4)
    }
}