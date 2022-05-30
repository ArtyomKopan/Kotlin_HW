package retest.one

interface ArithmeticAvailable<T : ArithmeticAvailable<T>> {
    operator fun plus(a: T): T
    operator fun minus(a: T): T
    operator fun times(a: T): T
    fun isNull(): Boolean
}
