package homework.five.second

data class Matrix(val linesCount: Int, val columnsCount: Int, val array: Array<IntArray>) {
    init {
        require(
            linesCount == array.size && columnsCount == array[0].size
        ) { "Реальный размер матрицы не совпадает с заявленным!" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (linesCount != other.linesCount) return false
        if (columnsCount != other.columnsCount) return false
        if (!array.contentDeepEquals(other.array)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = linesCount
        result = 31 * result + columnsCount
        result = 31 * result + array.contentDeepHashCode()
        return result
    }
}
