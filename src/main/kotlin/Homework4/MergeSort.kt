package homework.four

class MergeSort<K : Comparable<K>>(
    val array: Array<K>,
    private val maxThreadsCount: Int,
    private var threadsCount: Int
) {
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

    private fun singleThreadMergeSort(left: Int, right: Int) {
        if (left + 1 < right) {
            val middle = (left + right) / 2
            singleThreadMergeSort(left, middle)
            singleThreadMergeSort(middle, right)
            merge(left, right)
        }
    }

    fun multiThreadMergeSort(left: Int, right: Int) {
        if (left + 1 < right) {
            val middle = (left + right) / 2
            if (threadsCount >= maxThreadsCount - 1) {
                singleThreadMergeSort(left, right)
            } else {
                val leftThread =
                    Thread { MergeSort(array, maxThreadsCount, threadsCount++).multiThreadMergeSort(left, middle) }
                val rightThread =
                    Thread { MergeSort(array, maxThreadsCount, threadsCount++).multiThreadMergeSort(middle, right) }
                leftThread.start()
                rightThread.start()
                leftThread.join()
                rightThread.join()
                merge(left, right)
            }
        }
    }
}
