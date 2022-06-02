import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import retest.two.cleanQuote
import retest.two.getBestQuotes
import retest.two.getHtmlDocument
import retest.two.getNewQuotes
import retest.two.getRandomQuote

internal class ParsingTest {
    @Test
    fun getHtmlDocument() {
        assertNotNull(getHtmlDocument("http://bashorg.org"))
    }

    @Test
    fun cleanQuote() {
        val s = "<div>class=\"quote\"\nХорошая <br>цитата</div>"
        assertEquals("Хорошая цитата", cleanQuote(s))
    }

    @Test
    fun getBestQuotesTest() {
        assertNotNull(getBestQuotes())
    }

    @Test
    fun getNewQuotesTest() {
        assertNotNull(getNewQuotes())
    }

    @Test
    fun getRandomQuoteTest() {
        assertNotNull(getRandomQuote())
    }
}
