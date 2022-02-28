import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EratosphenTest {

    @Test
    fun testNegative() {
        assertEquals(mutableListOf<Int>(), getPrimeNumbers(-10))
    }

    @Test
    fun testZero() {
        assertEquals(mutableListOf<Int>(), getPrimeNumbers(0))
    }

    @Test
    fun testOne() {
        assertEquals(mutableListOf<Int>(), getPrimeNumbers(1))
    }

    @Test
    fun testTwo() {
        assertEquals(mutableListOf(2), getPrimeNumbers(2))
    }

    @Test
    fun testPrime() {
        assertEquals(mutableListOf(2, 3, 5, 7, 11), getPrimeNumbers(11))
    }

    @Test
    fun testComposite() {
        assertEquals(mutableListOf(2, 3, 5, 7, 11, 13, 17, 19), getPrimeNumbers(20))
    }
}