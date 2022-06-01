package homework.five.second

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun multipleMatrix(matrixA: Matrix, matrixB: Matrix): Matrix {
    require(
        matrixA.columnsCount == matrixB.linesCount
    ) { "Количество столбцов в первой матрице должно быть равно количеству строк во второй матрице!" }

    val result =
        Matrix(matrixA.linesCount, matrixB.columnsCount, Array(matrixA.linesCount) { IntArray(matrixB.columnsCount) })
    for (i in 0 until matrixA.linesCount) {
        for (j in 0 until matrixB.columnsCount) {
            result.array[i][j] = (0 until matrixA.columnsCount).sumOf {
                matrixA.array[i][it] * matrixB.array[it][j]
            }
        }
    }
    return result
}

fun coroutineMultipleMatrix(matrixA: Matrix, matrixB: Matrix): Matrix {
    require(
        matrixA.columnsCount == matrixB.linesCount
    ) { "Количество столбцов в первой матрице должно быть равно количеству строк во второй матрице!" }

    val result =
        Matrix(matrixA.linesCount, matrixB.columnsCount, Array(matrixA.linesCount) { IntArray(matrixB.columnsCount) })

    runBlocking {
        for (i in 0 until matrixA.linesCount) {
            for (j in 0 until matrixB.columnsCount) {
                launch {
                    result.array[i][j] = (0 until matrixA.columnsCount).sumOf {
                        matrixA.array[i][it] * matrixB.array[it][j]
                    }
                }
            }
        }
    }

    return result
}
