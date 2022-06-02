package final

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ParsingTest {
    @Test
    fun getHtmlDocumentTest() {
        assertNotNull(getHtmlDocument("https://en.wikipedia.org/wiki/Tiger_I"))
    }

    @Test
    fun searchRefsTest() {
        assertNotNull(searchRefs(getHtmlDocument("https://en.wikipedia.org/wiki/Tiger_I")))
    }

    @Test
    fun isCorrectRefTest1() {
        assertTrue(isCorrectRef("/wiki/Tiger_I"))
    }

    @Test
    fun isCorrectRefTest2() {
        assertFalse(isCorrectRef("/wiki/Category:World_War_II"))
    }
}
