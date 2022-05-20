package retest.one

interface ArithmeticAvailable<T: ArithmeticAvailable<T>> {
    operator fun plus(a: T): ArithmeticAvailable<T>
    operator fun minus(a: T): ArithmeticAvailable<T>
    operator fun times(a: T): ArithmeticAvailable<T>
    fun isNull(): Boolean
}
