import kotlin.math.ceil
import kotlin.math.sqrt

val scan = java.util.Scanner(System.`in`)

fun getPrimeNumbers(n: Int): MutableList<Int> {
    if (n <= 0) {
        print("Граница должна быть положительным числом!")
        return mutableListOf()
    }
    val primes = Array(n + 1) { true }
    primes[0] = false
    primes[1] = false
    for (p in 2..ceil(sqrt(n.toDouble())).toInt())
        if (primes[p]) {
            for (x in (p * p)..n)
                if (x % p == 0)
                    primes[x] = false
        }
    val primeNumbers = mutableListOf<Int>()
    for (i in 2..n)
        if (primes[i])
            primeNumbers.add(i)
    return primeNumbers
}

fun main() {
    print("Введите верхнюю границу: ")
    val n = scan.nextInt()

    val primes = getPrimeNumbers(n)
    for (i in primes)
        print("$i ")
}
