package final

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BFSTest {
    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun `bfs test`(expected: Int, startArticle: String, searchDepth: Int, threadsCount: Int) {
        assertEquals(expected, bfs(startArticle, searchDepth, threadsCount).first)
    }

    companion object {
        @JvmStatic
        fun addTestInputData() = listOf(
            Arguments.of(0, "https://en.wikipedia.org/wiki/Adolf_Hitler", 4, 1),
            Arguments.of(1, "https://en.wikipedia.org/wiki/Tiger_I", 4, 2),
            Arguments.of(2, "https://en.wikipedia.org/wiki/XXXXVI_Panzer_Corps", 4, 4),
            Arguments.of(2, "https://en.wikipedia.org/wiki/AVL_tree", 4, 8),
            Arguments.of(2, "https://en.wikipedia.org/wiki/Quasar", 4, 16),
            Arguments.of(3, "https://en.wikipedia.org/wiki/Haplorhini", 4, 32),
            Arguments.of(NOT_FOUND, "https://en.wikipedia.org/wiki/Haplorhini", 2, 64)
        )
    }
}
