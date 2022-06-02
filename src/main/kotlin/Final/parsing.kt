package final

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

val forbiddenTemplates = listOf(
    "File:",
    "Wikipedia:",
    "Help:",
    "Template:",
    "Category:",
    "Special:",
    "Portal:",
    "User:",
    "MediaWiki:",
    "Draft",
    "TimedText",
    "Module:",
    "Media:",
    "Template_talk:",
    "Talk:"
)

@Suppress("TooGenericExceptionCaught", "SwallowedException")
fun getHtmlDocument(url: String): Document? {
    val correctUrl = if ("https://" in url) url else "https://$url"
    return try {
        Jsoup.connect(correctUrl).get()
    } catch (e: Exception) {
        null
    }
}

fun searchRefs(html: Document?): List<String> {
    html ?: return emptyList()

    val correctRefs = mutableSetOf<String>() // содержит только корректные ссылки на страницы из Википедии
    val refs = html.select("[href^=/wiki/]").map { it.attr("href") }.toList()
    for (ref in refs) {
        if (isCorrectRef(ref)) {
            correctRefs.add("https://en.wikipedia.org$ref")
        }
        if ("Adolf_Hitler" in ref) { // отсечка
            break
        }
    }
    return correctRefs.toList().reversed() // чтобы ссылка с гитлером была вначале, для ускорения поиска
}

fun isCorrectRef(ref: String): Boolean {
    for (template in forbiddenTemplates) {
        if (template in ref) {
            return false
        }
    }
    return true
}
