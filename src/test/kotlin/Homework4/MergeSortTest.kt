package homework.four

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

internal class MergeSortTest {
    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun <K : Comparable<K>> multiThreadMergeSort(array: Array<K>, maxThreadsCount: Int) {
        val ms = MergeSort(array, maxThreadsCount, 0)
        ms.multiThreadMergeSort(0, array.size)
        assertEquals(true, ms.array.contentEquals(array.sortedArray()))
    }

    companion object {
        @JvmStatic
        fun addTestInputData() = listOf(
            Arguments.of(emptyArray<Int>(), 1),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10), 1),
            Arguments.of(Array(1000) { Random.nextInt() }, 1),
            Arguments.of(emptyArray<Int>(), 2),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10), 2),
            Arguments.of(Array(1000) { Random.nextInt() }, 2),
            Arguments.of(emptyArray<Int>(), 4),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10), 4),
            Arguments.of(Array(1000) { Random.nextInt() }, 4),
            Arguments.of(emptyArray<Int>(), 8),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10), 8),
            Arguments.of(Array(1000) { Random.nextInt() }, 8),
            Arguments.of(emptyArray<Int>(), 16),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10), 16),
            Arguments.of(Array(1000) { Random.nextInt() }, 16)
        )
    }
}
