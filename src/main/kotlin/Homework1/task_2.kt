import java.util.InputMismatchException
import kotlin.math.ceil
import kotlin.math.sqrt

fun getPrimeNumbers(n: Int): List<Int> {
    require(n > 0) { "Граница должна быть положительным числом!" }

    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false
    for (p in 2..ceil(sqrt(n.toDouble())).toInt()) {
        if (isPrime[p]) {
            for (x in (p * p)..n step p) {
                isPrime[x] = false
            }
        }
    }

    return isPrime.mapIndexed { index, value -> if (value) index else null }.filterNotNull()
}

val scan = java.util.Scanner(System.`in`)

fun main() {
    print("Введите верхнюю границу: ")
    try {
        val n = scan.nextInt()
        val primes = getPrimeNumbers(n)
        print(primes.joinToString(separator = " "))
    } catch (e: InputMismatchException) {
        println("Введённое значение некорректно. Попробуйте ещё раз")
    }
}
