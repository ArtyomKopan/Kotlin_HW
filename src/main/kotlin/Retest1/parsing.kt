import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun getHtmlDocument(url: String): Document = Jsoup.connect(url).get()

fun cleanQuote(s: String): String {
    val startQuote = s.indexOf("<div>")
    val endQuote = s.lastIndexOf("</div>")
    val quote = s.substring(startQuote + "<div>".length, endQuote)
    var finalQuote = ""
    var isAdd = true // isAdd -> добавляем символ в finalQuote, !isAdd -> не добавляем
    for (i in quote.indices) {
        when (quote[i]) {
            '<' -> isAdd = false
            '>' -> isAdd = true
            else -> if (isAdd) finalQuote += quote[i]
        }
    }
    if ("class=\"quote\"\n" in finalQuote) {
        return finalQuote.substring("class=\"quote\"\n".length)
    } else {
        return finalQuote
    }
}

fun getCleanQuotes(quotes: List<String>): List<String> {
    val cleanQuotes = mutableListOf<String>()
    for (s in quotes) {
        cleanQuotes.add(cleanQuote(s))
    }
    return cleanQuotes
}

fun getBestQuotes(): List<String> {
    val doc = getHtmlDocument("http://bashorg.org/best")
    val s = doc.select(".q .quote").map { it.toString() }.toList()
    return getCleanQuotes(s)
}

fun getNewQuotes(): List<String> {
    val doc = getHtmlDocument("http://bashorg.org/")
    val s = doc.select(".q .quote").map { it.toString() }.toList()
    return getCleanQuotes(s)
}

fun getRandomQuote(): List<String> {
    val doc = getHtmlDocument("http://bashorg.org/casual")
    val s = doc.select(".q div").toString()
    return getCleanQuotes(listOf(s))
}
