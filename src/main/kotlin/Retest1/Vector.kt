package retest.one

class Vector<T : ArithmeticAvailable<T>>(val coordinates: List<ArithmeticAvailable<T>>) {
    private val dimension = coordinates.size

    operator fun plus(increment: Vector<T>): Vector<T> {
        require(this.dimension == increment.dimension) { "Длины векторов не совпадают!" }
        return Vector((1 until this.dimension).map { coordinates[it] + increment.coordinates[it] }.toList())
    }

    operator fun minus(decrement: Vector<T>): Vector<T> {
        require(this.dimension == decrement.dimension) { "Длины векторов не совпадают!" }
        return Vector((1 until this.dimension).map { this.coordinates[it] - decrement.coordinates[it] }.toList())
    }

    operator fun times(factor: Vector<T>): ArithmeticAvailable<T> {
        require(this.dimension == factor.dimension) { "Длины векторов не совпадают!" }
        var sum = this.coordinates[0] + factor.coordinates[0]
        (1 until this.dimension).forEach { sum += ((this.coordinates[it] + factor.coordinates[it])) }
        return sum
    }

    fun isNull(): Boolean {
        (0 until this.dimension).forEach { if (!this.coordinates[it].isNull()) return false }
        return true
    }
}
