package homework.five

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

internal class MergeSortTest {
    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun <K : Comparable<K>> multiThreadMergeSortTest(array: Array<K>, maxThreadsCount: Int) {
        val ms = MultithreadingMergeSort(array, maxThreadsCount, 0)
        ms.mergeSort(0, array.size)
        assertTrue(ms.array.contentEquals(array.sortedArray()))
    }

    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun <K : Comparable<K>> coroutineMergeSortTest(array: Array<K>, maxCoroutinesCount: Int) {
        val ms = CoroutineMergeSort(array, maxCoroutinesCount, 0)
        ms.mergeSort(0, array.size)
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
    }
}
