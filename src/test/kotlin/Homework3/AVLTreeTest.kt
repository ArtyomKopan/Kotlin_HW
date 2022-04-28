package homework.three

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class AVLTreeTest {
    @ParameterizedTest
    @MethodSource("addPutTestInputData")
    fun `put test`(expected: MutableSet<Int>, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        source.forEach { tree[it.key] = it.value }
        assertEquals(expected, tree.keys)
    }

    @ParameterizedTest
    @MethodSource("addPutTestInputData")
    fun `putAll test`(expected: MutableSet<Int>, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        tree.putAll(source)
        assertEquals(expected, tree.keys)
    }

    @ParameterizedTest
    @MethodSource("addGetTestInputData")
    fun `get test`(expectedValue: String?, checkedKey: Int, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        tree.putAll(source)
        assertEquals(expectedValue, tree[checkedKey])
    }

    @ParameterizedTest
    @MethodSource("addContainsKeyTestInputData")
    fun `contains key test`(expected: Boolean, checkedKey: Int, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        tree.putAll(source)
        assertEquals(expected, tree.containsKey(checkedKey))
    }

    @ParameterizedTest
    @MethodSource("addContainsValueTestInputData")
    fun `contains value test`(expected: Boolean, checkedValue: String, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        tree.putAll(source)
        assertEquals(expected, tree.containsValue(checkedValue))
    }

    @ParameterizedTest
    @MethodSource("addGetTestInputData")
    fun `remove test`(expectedValue: String?, removedKey: Int, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        tree.putAll(source)
        assertEquals(expectedValue, tree.remove(removedKey))
    }

    @ParameterizedTest
    @MethodSource("addReplaceTestInputData")
    fun `replace test`(expectedValue: String?, key: Int, newValue: String, source: Map<Int, String>) {
        val tree = AVLTree<Int, String>()
        tree.putAll(source)
        assertEquals(expectedValue, tree.replace(key, newValue))
    }

    @Test
    fun `clear test`() {
        val tree = AVLTree<Int, String>()
        tree.putAll(
            mapOf(
                1 to "Jon Snow",
                2 to "Tyrion Lannister",
                3 to "Arya Stark",
                4 to "Teon Grayjoy",
                5 to "Samvell Tarley"
            )
        )
        tree.clear()
        assertEquals(true, tree.isEmpty())
    }

    companion object {
        private val defaultMap = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )

        @JvmStatic
        fun addPutTestInputData() = listOf(
            Arguments.of(mutableSetOf(1), mapOf(1 to "Jon Snow")),
            Arguments.of(
                mutableSetOf(1, 2, 3, 4, 5), defaultMap
            ),
            Arguments.of(
                mutableSetOf(1, 2, 3, 4), mapOf(
                    1 to "Jon Snow",
                    2 to "Tyrion Lannister",
                    3 to "Arya Stark",
                    4 to "Teon Grayjoy",
                    1 to "Samvell Tarley"
                )
            )
        )

        @JvmStatic
        fun addGetTestInputData() = listOf(
            Arguments.of(null, 1, emptyMap<Int, String>()),
            Arguments.of("Jon Snow", 1, defaultMap),
            Arguments.of(null, 6, defaultMap)
        )

        @JvmStatic
        fun addContainsKeyTestInputData() = listOf(
            Arguments.of(false, 10, emptyMap<Int, String>()),
            Arguments.of(true, 2, defaultMap),
            Arguments.of(false, 10, defaultMap)
        )

        @JvmStatic
        fun addContainsValueTestInputData() = listOf(
            Arguments.of(true, "Jon Snow", defaultMap),
            Arguments.of(false, "Melisandra", defaultMap)
        )

        @JvmStatic
        fun addReplaceTestInputData() = listOf(
            Arguments.of("Teon Grayjoy", 4, "Stannis Barateon", defaultMap),
            Arguments.of(null, 6, "Dayeneris Targarien", defaultMap)
        )
    }
}
