package homework.three

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AVLTreeTest {
    @Test
    fun `put test when tree is empty`() {
        val tree = AVLTree<Int, String>()
        tree[1] = "Jon Snow"
        assertEquals(mutableSetOf(1), tree.keys)
    }

    @Test
    fun `put test when key not in tree`() {
        val tree = AVLTree<Int, String>()
        tree[1] = "Jon Snow"
        tree[2] = "Tyrion Lannister"
        tree[3] = "Arya Stark"
        tree[4] = "Teon Grayjoy"
        tree[5] = "Samvell Tarley"
        assertEquals(mutableSetOf(1, 2, 3, 4, 5), tree.keys)
    }

    @Test
    fun `put test when key in tree`() {
        val tree = AVLTree<Int, String>()
        tree[2] = "Tyrion Lannister"
        tree[3] = "Arya Stark"
        tree[4] = "Teon Grayjoy"
        tree[5] = "Samvell Tarley"
        tree[10] = "Jon Snow"
        assertEquals(mutableSetOf(2, 3, 4, 5, 10), tree.keys)
    }

    @Test
    fun `putAll test`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        assertEquals(mutableSetOf(1, 2, 3, 4, 5), tree.keys)
    }

    @Test
    fun `remove test when tree is empty`() {
        val tree = AVLTree<Int, String>()
        val removedValue = tree.remove(6)
        assertEquals(removedValue, null)
    }

    @Test
    fun `remove test when key in tree - check value`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        val removedValue = tree.remove(3)
        assertEquals("Arya Stark", removedValue)
    }

    @Test
    fun `remove test when key in tree - check keys`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        tree.remove(4)
        assertEquals(mutableSetOf(1, 2, 3, 5), tree.keys)
    }

    @Test
    fun `contains key test when tree is empty`() {
        val tree = AVLTree<Int, String>()
        assertEquals(tree.containsKey(10), false)
    }

    @Test
    fun `contains key test when key in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        assertEquals(tree.containsKey(2), true)
    }

    @Test
    fun `contains key test when key not in tree`() {
        val tree = AVLTree<Int, String>()
        tree[1] = "Jon Snow"
        tree[2] = "Tyrion Lannister"
        assertEquals(tree.containsKey(3), false)
    }

    @Test
    fun `contains value test when value in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        assertEquals(tree.containsValue("Jon Snow"), true)
    }

    @Test
    fun `contains value test when value not in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        assertEquals(tree.containsValue("Melisandra"), false)
    }

    @Test
    fun `get test when key in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        assertEquals("Jon Snow", tree[1])
    }

    @Test
    fun `get test when key not in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
            4 to "Teon Grayjoy",
            5 to "Samvell Tarley"
        )
        tree.putAll(from)
        assertEquals(tree[6], null)
    }

    @Test
    fun `replace test when key in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
        )
        tree.putAll(from)
        tree.replace(2, "Stannis Barateon")
        assertEquals(mutableListOf("Jon Snow", "Stannis Barateon", "Arya Stark").toSet(), tree.values.toSet())
    }

    @Test
    fun `replace test when key not in tree`() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
        )
        tree.putAll(from)
        tree.replace(6, "Stannis Barateon")
        assertEquals(mutableListOf("Jon Snow", "Tyrion Lannister", "Arya Stark"), tree.values)
    }

    @Test
    fun clear() {
        val tree = AVLTree<Int, String>()
        val from = mapOf(
            1 to "Jon Snow",
            2 to "Tyrion Lannister",
            3 to "Arya Stark",
        )
        tree.putAll(from)
        tree.clear()
        assertEquals(tree.isEmpty(), true)
    }
}
