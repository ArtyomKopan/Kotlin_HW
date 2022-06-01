package retest.one

class ArithmeticInt(val value: Int) : ArithmeticAvailable<ArithmeticInt> {
    override operator fun plus(a: ArithmeticInt) = ArithmeticInt(value + a.value)
    override operator fun minus(a: ArithmeticInt) = ArithmeticInt(value - a.value)
    override operator fun times(a: ArithmeticInt) = ArithmeticInt(value * a.value)
    override fun isNull() = value == 0
}
