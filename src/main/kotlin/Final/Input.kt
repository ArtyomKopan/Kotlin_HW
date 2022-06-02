package final

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int

class Input : CliktCommand() {
    val searchDepth: Int by argument(help = "Глубина поиска").int()
    val threadsCount: Int by argument(help = "Число используемых процессоров").int()
    val start: String? by option(help = "Страница, с которой начинать поиск")

    /* Примеры использования:
        main 10 1
        main 10 1 --start=en.wikipedia.org
     */

    @Suppress("EmptyFunctionBlock")
    override fun run() {}

    fun validate() {
        require(searchDepth > 0) { "Глубина поиска должна быть положительной!" }
        require(threadsCount > 0) { "Число потоков должно быть положительным!" }
        require(start == null || "en.wikipedia.org" in start!!) { "Стартовая ссылка должна вести на Википедию!" }
        if (start != null) {
            require(getHtmlDocument(start!!) != null) { "Ссылка некорректна!" }
        }
    }
}
