package retest.one

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class VectorTest {
    @Test
    fun `plus test`() {
        val a = Vector(listOf(ArithmeticInt(1), ArithmeticInt(2), ArithmeticInt(3)))
        val b = Vector(listOf(ArithmeticInt(4), ArithmeticInt(5), ArithmeticInt(6)))
        val c = Vector(listOf(ArithmeticInt(5), ArithmeticInt(7), ArithmeticInt(9)))
        val sum = a + b
        assertEquals(sum.coordinates.map { it.value }, c.coordinates.map { it.value })
    }

    @Test
    fun `minus test`() {
        val a = Vector(listOf(ArithmeticInt(1), ArithmeticInt(2), ArithmeticInt(3)))
        val b = Vector(listOf(ArithmeticInt(10), ArithmeticInt(5), ArithmeticInt(-2)))
        val c = Vector(listOf(ArithmeticInt(-9), ArithmeticInt(-3), ArithmeticInt(5)))
        val difference = a - b
        assertEquals(difference.coordinates.map { it.value }, c.coordinates.map { it.value })
    }

    @Test
    fun `times test`() {
        val firstList = listOf(1, 2, 3)
        val secondList = listOf(4, 5, 6)
        val a = Vector(listOf(ArithmeticInt(1), ArithmeticInt(2), ArithmeticInt(3)))
        val b = Vector(listOf(ArithmeticInt(4), ArithmeticInt(5), ArithmeticInt(6)))
        val x = (0 until 3).sumOf { firstList[it] * secondList[it] }
        val product = a * b
        assertEquals(x, product.value)
    }

    @Test
    fun `null vector test`() {
        val a = Vector(listOf(ArithmeticInt(0), ArithmeticInt(0), ArithmeticInt(0)))
        assertEquals(true, a.isNull())
    }

    @Test
    fun `non-null vector test`() {
        val a = Vector(listOf(ArithmeticInt(1), ArithmeticInt(0), ArithmeticInt(0)))
        assertEquals(false, a.isNull())
    }

    @Test
    fun `non-equal dimensions test`() {
        val a = Vector(listOf(ArithmeticInt(1), ArithmeticInt(2), ArithmeticInt(3)))
        val b = Vector(listOf(ArithmeticInt(4), ArithmeticInt(5)))
        val exception = assertFailsWith<IllegalArgumentException> { val sum = a + b }
        assertEquals(exception.message, "Длины векторов не совпадают!")
    }
}
