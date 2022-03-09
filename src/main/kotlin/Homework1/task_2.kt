import kotlin.math.ceil
import kotlin.math.sqrt

fun getPrimeNumbers(n: Int): List<Int> {
    require(n > 0) { "Граница должна быть положительным числом!" }

    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false
    for (possiblyPrime in 2..ceil(sqrt(n.toDouble())).toInt()) {
        if (isPrime[possiblyPrime]) {
            for (x in (possiblyPrime * possiblyPrime)..n step possiblyPrime) {
                isPrime[x] = false
            }
        }
    }

    return isPrime.mapIndexed { index, value -> if (value) index else null }.filterNotNull()
}

fun main() {
    print("Введите верхнюю границу: ")
    val n = readLine()?.toIntOrNull()
    if (n == null) {
        println("Введённое значение некорректно. Попробуйте ещё раз")
    } else if (n <= 0) {
        println("Верхняя граница должна быть положительным числом!")
    } else {
        val primes = getPrimeNumbers(n)
        print(primes.joinToString(separator = " "))
    }
}
