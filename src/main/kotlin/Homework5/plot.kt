package homework.five

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleXContinuous
import java.util.*
import kotlin.random.Random

@Suppress("MagicNumber")
val THREADS_COUNT_LIST = (0..7).map { power(2, it) }.toList()

const val NANO_IN_MILLIS = 1e6
const val MEASUREMENT_COUNT = 10
const val FIGURE_POINT_SIZE = 4.0

enum class SortingMode {
    MULTITHREADING, COROUTINE
}

fun <K : Comparable<K>> computeMeanSortingTime(array: Array<K>, mode: SortingMode): MutableMap<Int, Double> {
    val meanSortingTime = mutableMapOf<Int, Double>()
    for (threadsCount in THREADS_COUNT_LIST) {
        var sortingTime = 0.0
        repeat((1..MEASUREMENT_COUNT).count()) {
            val sortObject = when (mode) {
                SortingMode.MULTITHREADING -> MultithreadingMergeSort(array, threadsCount, 0)
                SortingMode.COROUTINE -> CoroutineMergeSort(array, threadsCount, 0)
            }
            val startTime = System.nanoTime()
            sortObject.mergeSort(0, array.size)
            val finishTime = System.nanoTime()
            sortingTime += (finishTime - startTime)
        }
        sortingTime /= MEASUREMENT_COUNT
        sortingTime /= NANO_IN_MILLIS
        meanSortingTime[threadsCount] = sortingTime
    }
    return meanSortingTime
}

fun buildPlot(meanSortingTime: MutableMap<Int, Double>, arraySize: Int): Plot {
    val threadsCount = meanSortingTime.map { it.key }.toList()
    val sortingTime = meanSortingTime.map { it.value }.toList()
    val data = mapOf<String, Any>("Threads count" to threadsCount, "Sorting time (ms)" to sortingTime)
    val sortPlot =
        letsPlot(data) + labs(title = "Multithreading merge sort time (array size = $arraySize)") + geomPoint(
            color = "dark-green", size = FIGURE_POINT_SIZE
        ) { x = "Threads count"; y = "Sorting time (ms)" } + scaleXContinuous(
            breaks = THREADS_COUNT_LIST
        )
    return sortPlot
}

fun main() {
    print("Введите размер массива: ")
    val size = Scanner(System.`in`).nextInt()
    val array = Array(size) { Random.nextInt() }

    val meanMultithreadingSortingTime = computeMeanSortingTime(array, SortingMode.MULTITHREADING)
    val meanCoroutineSortingTime = computeMeanSortingTime(array, SortingMode.COROUTINE)

    println("Время сортировки многопоточным способом: ")
    meanMultithreadingSortingTime.forEach { println("${it.key} ${it.value}") }
    println()
    println("Время сортировки с помощью корутин: ")
    meanCoroutineSortingTime.forEach { println("${it.key} ${it.value}") }

    val multithreadingSortPlot = buildPlot(meanMultithreadingSortingTime, size)
    ggsave(multithreadingSortPlot, "multithreadingPlot$size.png")

    val coroutineSortPlot = buildPlot(meanCoroutineSortingTime, size)
    ggsave(coroutineSortPlot, "coroutinePlot$size.png")
}
