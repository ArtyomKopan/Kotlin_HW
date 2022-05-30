package homework.five

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomPoint
import jetbrains.letsPlot.label.labs
import jetbrains.letsPlot.letsPlot
import jetbrains.letsPlot.scale.scaleXContinuous
import java.util.Scanner
import kotlin.random.Random

@Suppress("MagicNumber")
val THREADS_COUNT_LIST = (0..7).map { power(2, it) }.toList()

const val NANO_IN_MILLIS = 1e6
const val MEASUREMENT_COUNT = 10
const val FIGURE_POINT_SIZE = 4.0

fun main() {
    print("Введите размер массива: ")
    val size = Scanner(System.`in`).nextInt()
    val array = Array(size) { Random.nextInt() }

    // в словаре время указано в миллисекундах
    val meanMultithreadingSortingTime = mutableMapOf<Int, Double>()
    val meanCoroutineSortingTime = mutableMapOf<Int, Double>()
    for (threadsCount in THREADS_COUNT_LIST) {
        var multithreadingSortingTime = 0.0
        var coroutineSortingTime = 0.0
        repeat((1..MEASUREMENT_COUNT).count()) {
            val multithreadingSortObject = MultithreadingMergeSort(array, threadsCount, 0)
            var startTime = System.nanoTime()
            multithreadingSortObject.mergeSort(0, array.size)
            var finishTime = System.nanoTime()
            multithreadingSortingTime += (finishTime - startTime)

            val coroutineSortObject = CoroutineMergeSort(array, threadsCount, 0)
            startTime = System.nanoTime()
            coroutineSortObject.mergeSort(0, array.size)
            finishTime = System.nanoTime()
            coroutineSortingTime += (finishTime - startTime)
        }

        multithreadingSortingTime /= MEASUREMENT_COUNT
        multithreadingSortingTime /= NANO_IN_MILLIS // переводим наносекунды в миллисекунды
        meanMultithreadingSortingTime[threadsCount] = multithreadingSortingTime

        coroutineSortingTime /= MEASUREMENT_COUNT
        coroutineSortingTime /= NANO_IN_MILLIS
        meanCoroutineSortingTime[threadsCount] = coroutineSortingTime
    }

    println("Время сортировки многопоточным способом: ")
    meanMultithreadingSortingTime.forEach { println("${it.key} ${it.value}") }
    println()
    println("Время сортировки с помощью корутин: ")
    meanCoroutineSortingTime.forEach { println("${it.key} ${it.value}") }

    // строим график для многопоточной сортировки
    val threadsCount = meanMultithreadingSortingTime.map { it.key }.toList()
    val multithreadingSortingTime = meanMultithreadingSortingTime.map { it.value }.toList()
    val multithreadingData =
        mapOf<String, Any>("Threads count" to threadsCount, "Sorting time (ms)" to multithreadingSortingTime)

    val multithreadingSortFigure =
        letsPlot(multithreadingData) + labs(title = "Multithreading merge sort time (array size = $size)") + geomPoint(
            color = "dark-green",
            size = FIGURE_POINT_SIZE
        ) { x = "Threads count"; y = "Sorting time (ms)" } + scaleXContinuous(
            breaks = THREADS_COUNT_LIST
        )

    ggsave(multithreadingSortFigure, "multithreadingPlot$size.png")

    // строим график корутинной сортировки
    val coroutineSortingTime = meanCoroutineSortingTime.map { it.value }.toList()
    val coroutineData =
        mapOf<String, Any>("Coroutines count" to threadsCount, "Sorting time (ms)" to coroutineSortingTime)
    val coroutineSortFigure =
        letsPlot(coroutineData) + labs(title = "Coroutine merge sort time (array size = $size)") + geomPoint(
            color = "dark-green",
            size = FIGURE_POINT_SIZE
        ) { x = "Coroutines count"; y = "Sorting time (ms)" } + scaleXContinuous(
            breaks = THREADS_COUNT_LIST
        )

    ggsave(coroutineSortFigure, "coroutinePlot$size.png")
}
