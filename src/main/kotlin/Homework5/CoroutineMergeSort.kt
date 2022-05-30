package homework.five

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.ceil
import kotlin.math.floor

class CoroutineMergeSort<K : Comparable<K>>(
    override var array: Array<K>,
    private val maxCoroutinesCount: Int,
    var coroutinesCount: Int
) : MergeSort<K>(array) {

    override fun mergeSort(left: Int, right: Int) {
        runBlocking {
            if (left + 1 < right) {
                if (coroutinesCount >= maxCoroutinesCount - 1) {
                    singleThreadMergeSort(left, right)
                } else {
                    val middle = (left + right) / 2
                    val leftCoroutinesCount = ceil(maxCoroutinesCount.toDouble() / 2).toInt()
                    val rightCoroutinesCount = floor(maxCoroutinesCount.toDouble() / 2).toInt()
                    val leftSorting = launch {
                        CoroutineMergeSort(array, leftCoroutinesCount, coroutinesCount++).mergeSort(left, middle)
                    }
                    leftSorting.join()
                    val rightSorting = launch {
                        CoroutineMergeSort(array, rightCoroutinesCount, coroutinesCount++).mergeSort(middle, right)
                    }
                    rightSorting.join()
                    merge(left, right)
                }
            }
        }
    }
}
