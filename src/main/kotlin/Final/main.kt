package final

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val RANDOM_PAGE = "https://en.wikipedia.org/wiki/Special:Random"

suspend fun main(args: Array<String>) {
    val input = Input()
    input.main(args)
    input.validate()

    var startArticle = input.start ?: RANDOM_PAGE
    if ("https://" !in startArticle) {
        startArticle = "https://$startArticle"
    }

    println("Выполняем подсчёт...")

    val (pathLength, path) = bfs(startArticle, input.searchDepth, input.threadsCount)

    println()
    if (pathLength == NOT_FOUND) {
        println("С этой страницы нельзя прийти к Гитлеру за ${input.searchDepth} шагов!")
    } else {
        println("С этой страницы можно прийти к Гитлеру за $pathLength шагов!")
        path.forEach { println(it) }
    }
}
