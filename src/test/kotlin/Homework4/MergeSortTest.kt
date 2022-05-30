package homework.four

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.pow
import kotlin.random.Random

internal class MergeSortTest {
    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun <K : Comparable<K>> multiThreadMergeSortTest(array: Array<K>, maxThreadsCount: Int) {
        val ms = MergeSort(array, maxThreadsCount, 0)
        ms.multiThreadMergeSort(0, array.size)
        assertTrue(ms.array.contentEquals(array.sortedArray()))
    }

    companion object {
        @JvmStatic
        fun addTestInputData(): List<Arguments> {
            val testList = mutableListOf<Arguments>()
            for (i in 0..6) {
                for (j in 0..6) {
                    testList.add(
                        Arguments.of(
                            Array(power(10, i)) { Random.nextInt() },
                            power(2, j)
                        )
                    )
                }
            }
            return testList.toList()
        }

        private fun power(a: Int, x: Int) = a.toFloat().pow(x).toInt()
    }
}
