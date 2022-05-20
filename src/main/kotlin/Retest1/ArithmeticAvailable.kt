package retest.one

interface ArithmeticAvailable {
    operator fun plus(a: ArithmeticAvailable<T>): ArithmeticAvailable<T>
    operator fun minus(a: ArithmeticAvailable<T>): ArithmeticAvailable<T>
    operator fun times(a: ArithmeticAvailable<T>): ArithmeticAvailable<T>
    fun isNull(): Boolean
}
