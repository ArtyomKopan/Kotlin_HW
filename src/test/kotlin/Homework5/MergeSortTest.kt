package homework.five

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

internal class MergeSortTest {
    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun <K : Comparable<K>> multiThreadMergeSort(array: Array<K>) {
        val ms = MergeSort(array)
        ms.coroutineMergeSort(0, array.size)
        assertEquals(true, ms.array.contentEquals(array.sortedArray()))
    }

    companion object {
        @JvmStatic
        fun addTestInputData() = listOf(
            Arguments.of(emptyArray<Int>()),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10)),
            Arguments.of(Array(1000) { Random.nextInt() }),
            Arguments.of(emptyArray<Int>()),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10)),
            Arguments.of(Array(1000) { Random.nextInt() }),
            Arguments.of(emptyArray<Int>()),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10)),
            Arguments.of(Array(1000) { Random.nextInt() }),
            Arguments.of(emptyArray<Int>()),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10)),
            Arguments.of(Array(1000) { Random.nextInt() }),
            Arguments.of(emptyArray<Int>()),
            Arguments.of(arrayOf(2, 5, 4, 3, 1, 8, 4, 9, 7, 10)),
            Arguments.of(Array(1000) { Random.nextInt() })
        )
    }
}
