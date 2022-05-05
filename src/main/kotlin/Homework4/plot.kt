package homework.four

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleXContinuous
import java.util.Scanner
import kotlin.random.Random

@Suppress("MagicNumber")
val THREADS_COUNT_LIST = listOf(1, 2, 4, 8, 16, 32, 64)

const val NANO_IN_MILLIS = 1e6
const val MEASUREMENT_COUNT = 10
const val FIGURE_POINT_SIZE = 4.0

fun main() {
    print("Введите размер массива: ")
    val size = Scanner(System.`in`).nextInt()
    val array = Array(size) { Random.nextInt() }

    // в словаре время указано в миллисекундах
    val meanSortingTime = mutableMapOf<Int, Double>()
    for (threadsCount in THREADS_COUNT_LIST) {
        var sortingTime = 0.0
        repeat((1..MEASUREMENT_COUNT).count()) {
            val ms = MergeSort(array, threadsCount, 0)
            val startTime = System.nanoTime()
            ms.multiThreadMergeSort(0, array.size)
            val finishTime = System.nanoTime()
            sortingTime += (finishTime - startTime)
        }
        sortingTime /= MEASUREMENT_COUNT
        sortingTime /= NANO_IN_MILLIS // переводим наносекунды в миллисекунды
        meanSortingTime[threadsCount] = sortingTime
    }

    meanSortingTime.forEach { println("${it.key} ${it.value}") }

    val threadsCount = meanSortingTime.map { it.key }.toList()
    val sortingTime = meanSortingTime.map { it.value }.toList()
    val data = mapOf<String, Any>("Threads count" to threadsCount, "Sorting time (ms)" to sortingTime)

    val fig = letsPlot(data) + geomPoint(
        color = "dark-green",
        size = FIGURE_POINT_SIZE
    ) { x = "Threads count"; y = "Sorting time (ms)" } + scaleXContinuous(
        breaks = THREADS_COUNT_LIST
    )

    ggsave(fig, "plot$size.png")
}
