package final

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.LinkedList
import java.util.Queue

const val NOT_FOUND = -1
const val HITLER = "https://en.wikipedia.org/wiki/Adolf_Hitler"

// результат == true <=> найден гитлер
fun processRefs(articles: Queue<Pair<String, Int>>, ref: String, priority: Int): Boolean {
    val wikipediaPages = searchRefs(getHtmlDocument(ref))
    for (page in wikipediaPages) {
        if (page == HITLER) {
            return true
        }
        articles.add(Pair(page, priority + 1))
    }
    return false
}

// возвращает число шагов, за которые можно найти Гитлера, начиная со страницы startArticle
fun bfs(startArticle: String, searchDepth: Int, threadsCount: Int): Int = runBlocking {
    // first = ссылка, second = число шагов, за которое до неё можно дойти
    val articles: Queue<Pair<String, Int>> = LinkedList()
    articles.add(Pair(startArticle, 0))

    while (articles.isNotEmpty() && articles.peek().second < searchDepth) {
        val firstRefs = (1..threadsCount).mapNotNull { articles.poll() }
        var isContinue = false
        for (element in firstRefs) {
            if (element.first == HITLER) {
                return@runBlocking element.second
            }
            if (element.second == searchDepth) {
                isContinue = true
                break
            }
        }
        if (isContinue) {
            continue
        }

        val flags = BooleanArray(firstRefs.size) { false }
        for (i in firstRefs.indices) {
            flags[i] = withContext(Dispatchers.Default) {
                processRefs(
                    articles,
                    firstRefs[i].first,
                    firstRefs[i].second
                )
            }
        }

        for (i in firstRefs.indices) {
            if (flags[i]) {
                return@runBlocking firstRefs[i].second + 1
            }
        }
    }

    return@runBlocking NOT_FOUND
}
