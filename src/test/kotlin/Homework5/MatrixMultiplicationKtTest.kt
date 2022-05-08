package homework.five.second

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MatrixMultiplicationKtTest {
    @ParameterizedTest
    @MethodSource("matrixMultipleTestInputData")
    fun `matrix multiplication test`(matrixA: Matrix, matrixB: Matrix, expected: Matrix) {
        assertEquals(true, expected == multipleMatrix(matrixA, matrixB))
    }

    @ParameterizedTest
    @MethodSource("matrixMultipleTestInputData")
    fun `coroutine matrix multiplication test`(matrixA: Matrix, matrixB: Matrix, expected: Matrix) {
        assertEquals(true, expected == coroutineMultipleMatrix(matrixA, matrixB))
    }

    companion object {
        @JvmStatic
        fun matrixMultipleTestInputData() = listOf(
            Arguments.of(
                Matrix(
                    2,
                    2,
                    arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
                ),
                Matrix(
                    2,
                    2,
                    arrayOf(intArrayOf(5, 6), intArrayOf(7, 8))
                ),
                Matrix(
                    2,
                    2,
                    arrayOf(intArrayOf(19, 22), intArrayOf(43, 50))
                )
            ),
            Arguments.of(
                Matrix(
                    2,
                    3,
                    arrayOf(intArrayOf(1, 2, 0), intArrayOf(3, 4, 0))
                ),
                Matrix(
                    3,
                    1,
                    arrayOf(intArrayOf(5), intArrayOf(7), intArrayOf(0))
                ),
                Matrix(
                    2,
                    1,
                    arrayOf(intArrayOf(19), intArrayOf(43))
                )
            )
        )
    }
}
