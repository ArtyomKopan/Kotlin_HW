package retest.one

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.reflect.TypeVariable

class ArithmeticInt(private val value: Int) : ArithmeticAvailable {
    override operator fun plus(a: ArithmeticInt) = ArithmeticInt(value + a.value)
    override operator fun minus(a: ArithmeticInt) = ArithmeticInt(value - a.value)
    override operator fun times(a: ArithmeticInt) = ArithmeticInt(value * a.value)
    override fun isNull() = value == 0
}

internal class VectorTest {
    @Test
    fun `plus test`() {
        val a = Vector<ArithmeticInt>(listOf(ArithmeticInt(1), ArithmeticInt(2), ArithmeticInt(3)))
        val b = Vector<ArithmeticInt>(listOf(ArithmeticInt(4), ArithmeticInt(5), ArithmeticInt(6)))
        val c = Vector<ArithmeticInt>(listOf(ArithmeticInt(5), ArithmeticInt(7), ArithmeticInt(9)))
        assertEquals(true, c.coordinates == (a + b).coordinates)
    }
}
