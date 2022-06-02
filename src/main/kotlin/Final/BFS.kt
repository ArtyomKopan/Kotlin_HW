package final

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.LinkedList
import java.util.Queue

const val NOT_FOUND = -1
const val HITLER = "https://en.wikipedia.org/wiki/Adolf_Hitler"

// результат == true <=> найден гитлер
fun processRefs(
    articles: Queue<Pair<String, Int>>,
    ref: String,
    priority: Int,
    prev: MutableMap<String, String>
): Boolean {
    val wikipediaPages = searchRefs(getHtmlDocument(ref))
    for (page in wikipediaPages) {
        if (page !in prev) {
            prev[page] = ref
        }
        if (page == HITLER) {
            return true
        }
        articles.add(Pair(page, priority + 1))
    }
    return false
}

// возвращает число шагов, за которые можно найти гитлера, начиная со страницы startArticle
fun bfs(startArticle: String, searchDepth: Int, threadsCount: Int): Pair<Int, List<String>> = runBlocking {
    // first = ссылка, second = число шагов, за которое до неё можно дойти
    val articles: Queue<Pair<String, Int>> = LinkedList()
    val prev = mutableMapOf<String, String>()
    articles.add(Pair(startArticle, 0))
    prev[startArticle] = ""

    while (articles.isNotEmpty() && articles.peek().second < searchDepth) {
        print("#") // для прогресс-бара
        val firstRefs = (1..threadsCount).mapNotNull { articles.poll() }
        var isContinue = false
        for (element in firstRefs) {
            if (element.first == HITLER) {
                return@runBlocking Pair(element.second, getPath(HITLER, prev))
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
                    firstRefs[i].second,
                    prev
                )
            }
        }

        for (i in firstRefs.indices) {
            if (flags[i]) {
                return@runBlocking Pair(firstRefs[i].second + 1, getPath(HITLER, prev))
            }
        }
    }

    return@runBlocking Pair(NOT_FOUND, emptyList())
}

fun getPath(page: String, prev: MutableMap<String, String>): List<String> {
    val path = mutableListOf(page)
    var prevPage = prev[page]
    while (prevPage != "") {
        path.add(prevPage!!)
        prevPage = prev[prevPage]
    }
    return path.reversed()
}
