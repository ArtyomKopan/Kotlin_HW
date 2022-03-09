import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFailsWith

internal class EratosphenTest {

    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun `test primes`(expected: List<Int>, firstArg: Int) {
        assertEquals(expected, getPrimeNumbers(firstArg))
    }

    @Test
    fun givenInvalidNumericFormatThenThrowsException() {
        val exception = assertFailsWith<NumberFormatException> { Integer.parseInt("abcdef") }
        assertEquals(exception.message, "For input string: \"abcdef\"")
    }

    companion object {
        @JvmStatic
        fun addTestInputData() = listOf(
            Arguments.of(emptyList<Int>(), 1),
            Arguments.of(listOf(2), 2),
            Arguments.of(listOf(2, 3, 5, 7, 11), 11),
            Arguments.of(listOf(2, 3, 5, 7, 11, 13, 17, 19), 20)
        )
    }
}
