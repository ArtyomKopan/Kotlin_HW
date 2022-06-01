package homework.five.second

import java.util.Scanner
import kotlin.random.Random

const val NANO_IN_MILLIS = 1e6

fun main() {
    val scan = Scanner(System.`in`)

    print("Введите INPUT, если хотите ввести матрицы вручную. ")
    print("Введите RANDOM, если хотите заполнить их случайными числами: ")
    val modeIsRandom = scan.next().uppercase() == "RANDOM"

    print("Введите число строк первой матрицы: ")
    val linesCountA = scan.nextInt()
    print("Введите число столбцов первой матрицы: ")
    val columnsCountA = scan.nextInt()
    val arrayA = Array(linesCountA) { IntArray(columnsCountA) }
    if (!modeIsRandom) {
        println("Введите первую матрицу: ")
    }
    for (i in 0 until linesCountA) {
        for (j in 0 until linesCountA) {
            arrayA[i][j] = if (modeIsRandom) Random.nextInt() else scan.nextInt()
        }
    }

    print("Введите число строк второй матрицы: ")
    val linesCountB = scan.nextInt()
    print("Введите число столбцов второй матрицы: ")
    val columnsCountB = scan.nextInt()
    val arrayB = Array(linesCountB) { IntArray(columnsCountB) }
    if (!modeIsRandom) {
        println("Введите вторую матрицу: ")
    }
    for (i in 0 until linesCountB) {
        for (j in 0 until linesCountB) {
            arrayB[i][j] = if (modeIsRandom) Random.nextInt() else scan.nextInt()
        }
    }

    val matrixA = Matrix(linesCountA, columnsCountA, arrayA)
    val matrixB = Matrix(linesCountB, columnsCountB, arrayB)

    val startTime1 = System.nanoTime()
    var result = multipleMatrix(matrixA, matrixB)
    val endTime1 = System.nanoTime()

    val startTime2 = System.nanoTime()
    result = coroutineMultipleMatrix(matrixA, matrixB)
    val endTime2 = System.nanoTime()

    println("Время умножения без использования корутин = ${(endTime1 - startTime1).toDouble() / NANO_IN_MILLIS} мс")
    println("Время умножения с использованием корутин = ${(endTime2 - startTime2).toDouble() / NANO_IN_MILLIS} мс")

    if (!modeIsRandom) {
        println("Результат умножения: ")
        for (i in 0 until result.linesCount) {
            for (j in 0 until result.columnsCount) {
                print("${result.array[i][j]} ")
            }
            println()
        }
    }
}
