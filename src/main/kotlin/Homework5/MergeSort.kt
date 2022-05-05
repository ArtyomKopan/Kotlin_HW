package homework.five

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MergeSort<K : Comparable<K>>(val array: Array<K>) {
    private fun merge(left: Int, right: Int) {
        var leftPointer = 0
        var rightPointer = 0
        val middle = (left + right) / 2
        val sortedArray = mutableListOf<K>()

        while (left + leftPointer < middle && middle + rightPointer < right) {
            if (array[left + leftPointer] <= array[middle + rightPointer]) {
                sortedArray.add(array[left + leftPointer])
                leftPointer++
            } else {
                sortedArray.add(array[middle + rightPointer])
                rightPointer++
            }
        }
        while (left + leftPointer < middle) {
            sortedArray.add(array[left + leftPointer])
            leftPointer++
        }
        while (middle + rightPointer < right) {
            sortedArray.add(array[middle + rightPointer])
            rightPointer++
        }
        (0 until right - left).forEach { array[left + it] = sortedArray[it] }
    }

    fun coroutineMergeSort(left: Int, right: Int) {
        runBlocking {
            if (left + 1 < right) {
                val middle = (left + right) / 2
                val leftSorting = launch {
                    coroutineMergeSort(left, middle)
                }
                leftSorting.join()
                val rightSorting = launch {
                    coroutineMergeSort(middle, right)
                }
                rightSorting.join()
                merge(left, right)
            }
        }
    }
}
