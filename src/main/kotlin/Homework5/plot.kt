package homework.five

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomLine
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleXContinuous
import kotlin.random.Random

@Suppress("MagicNumber")
val ARRAY_SIZE_LIST = (1..100).map { it * 100 }.toList()

const val NANO_IN_MILLIS = 1e6
const val MEASUREMENT_COUNT = 10
const val FIGURE_LINE_SIZE = 1.0

fun main() {
    // ключ - размер массива, значение - время сортировки в миллисекундах
    val meanSortingTime = mutableMapOf<Int, Double>()
    for (arraySize in ARRAY_SIZE_LIST) {
        var sortingTime = 0.0
        repeat((1..MEASUREMENT_COUNT).count()) {
            val array = Array(arraySize) { Random.nextInt() }
            val ms = MergeSort(array)
            val startTime = System.nanoTime()
            ms.coroutineMergeSort(0, array.size)
            val finishTime = System.nanoTime()
            sortingTime += (finishTime - startTime)
        }
        sortingTime /= MEASUREMENT_COUNT
        sortingTime /= NANO_IN_MILLIS // переводим наносекунды в миллисекунды
        meanSortingTime[arraySize] = sortingTime
    }

    meanSortingTime.forEach { println("${it.key} ${it.value}") }

    val arraysSize = meanSortingTime.map { it.key }.toList()
    val sortingTime = meanSortingTime.map { it.value }.toList()
    val data = mapOf<String, Any>("Array size" to arraysSize, "Sorting time (ms)" to sortingTime)

    val fig = letsPlot(data) + geomLine(
        color = "dark-green",
        size = FIGURE_LINE_SIZE
    ) { x = "Array size"; y = "Sorting time (ms)" } + scaleXContinuous(
        breaks = ARRAY_SIZE_LIST
    )

    ggsave(fig, "plot.png")
}
