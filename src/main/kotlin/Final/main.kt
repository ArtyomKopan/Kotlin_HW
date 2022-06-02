package final

const val RANDOM_PAGE = "https://en.wikipedia.org/wiki/Special:Random"

fun main(args: Array<String>) {
    val input = Input()
    input.main(args)
    input.validate()

    var startArticle = input.start ?: RANDOM_PAGE
    if ("https://" !in startArticle) {
        startArticle = "https://$startArticle"
    }

    val (pathLength, path) = bfs(startArticle, input.searchDepth, input.threadsCount)
    if (pathLength == NOT_FOUND) {
        println("С этой страницы нельзя прийти к Гитлеру за ${input.searchDepth} шагов!")
    } else {
        println("С этой страницы можно прийти к Гитлеру за $pathLength шагов!")
        path.forEach { println(it) }
    }
}
