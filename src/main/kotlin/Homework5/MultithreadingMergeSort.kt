package homework.five

import kotlin.math.ceil
import kotlin.math.floor

class MultithreadingMergeSort<K : Comparable<K>>(
    override val array: Array<K>,
    private val maxThreadsCount: Int,
    var threadsCount: Int
) : MergeSort<K>(array) {

    override fun mergeSort(left: Int, right: Int) {
        if (left + 1 < right) {
            val middle = (left + right) / 2
            if (threadsCount >= maxThreadsCount - 1) {
                singleThreadMergeSort(left, right)
            } else {
                val leftThreadsCount = ceil(maxThreadsCount.toDouble() / 2).toInt()
                val rightThreadsCount = floor(maxThreadsCount.toDouble() / 2).toInt()
                val leftThread =
                    Thread { MultithreadingMergeSort(array, leftThreadsCount, threadsCount++).mergeSort(left, middle) }
                val rightThread =
                    Thread {
                        MultithreadingMergeSort(array, rightThreadsCount, threadsCount++).mergeSort(
                            middle,
                            right
                        )
                    }
                leftThread.start()
                rightThread.start()
                leftThread.join()
                rightThread.join()

                merge(left, right)
            }
        }
    }
}
