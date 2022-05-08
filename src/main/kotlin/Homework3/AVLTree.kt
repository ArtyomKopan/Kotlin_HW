package homework.three

import java.util.LinkedList
import java.util.Queue

class AVLTree<K : Comparable<K>, V> : MutableMap<K, V> {
    override var size = 0
        private set

    override val keys: MutableSet<K>
        get() = treeRoot?.dfs()?.map { it.key }?.toMutableSet() ?: mutableSetOf()

    override val values: MutableList<V>
        get() = treeRoot?.dfs()?.map { it.value }?.toMutableList() ?: mutableListOf()

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = treeRoot?.dfs()?.map { Entry(it.key, it.value) }?.toMutableSet() ?: mutableSetOf()

    private var treeRoot: AVLTreeNode<K, V>? = null

    override fun put(key: K, value: V): V? {
        val oldValue = treeRoot?.get(key)
        treeRoot = insert(treeRoot, key, value)
        if (oldValue == null) {
            size++
        }
        return oldValue
    }

    override fun putAll(from: Map<out K, V>) = from.forEach { this[it.key] = it.value }

    override fun remove(key: K): V? {
        val removedValue = treeRoot?.get(key)
        treeRoot = delete(treeRoot, key)
        if (removedValue != null) {
            size--
        }
        return removedValue
    }

    override fun containsKey(key: K) = treeRoot?.get(key) != null

    override fun containsValue(value: V) = values.contains(value)

    override fun get(key: K) = treeRoot?.get(key)

    override fun isEmpty() = size == 0

    override fun replace(key: K, value: V): V? = treeRoot?.replaceValue(key, value)

    override fun clear() {
        treeRoot = null
        size = 0
    }

    override fun toString(): String {
        val queue: Queue<Pair<AVLTreeNode<K, V>?, Int>> = LinkedList(listOf(Pair(treeRoot, 0)))
        var prevTier = 0
        var resultString = ""
        while (queue.isNotEmpty()) {
            val currentNode = queue.peek().first
            val currentTier = queue.poll().second
            if (currentTier != prevTier) {
                resultString += "\n"
                prevTier = currentTier
            }
            if (currentNode == null) {
                resultString += "null "
            } else {
                resultString += "(${currentNode.key}, ${currentNode.value}) "
                queue.add(Pair(currentNode.left, currentTier + 1))
                queue.add(Pair(currentNode.right, currentTier + 1))
            }
        }
        return resultString
    }

    companion object {
        private fun <K : Comparable<K>, V> insert(node: AVLTreeNode<K, V>?, key: K, value: V): AVLTreeNode<K, V>? {
            node ?: return AVLTreeNode(key, value)
            when {
                key == node.key -> node.value = value
                key > node.key -> node.right = insert(node.right, key, value)
                key < node.key -> node.left = insert(node.left, key, value)
            }
            return node.balance()
        }

        private fun <K : Comparable<K>, V> delete(node: AVLTreeNode<K, V>?, key: K): AVLTreeNode<K, V>? =
            when {
                node == null -> null
                key < node.key -> {
                    node.left = delete(node.left, key)
                    node.balance()
                }
                key > node.key -> {
                    node.right = delete(node.right, key)
                    node.balance()
                }
                else -> {
                    val minNode = node.right?.findMinimumNode()
                    if (minNode == null) {
                        node.left?.balance()
                    } else {
                        minNode.right = node.right?.removeMinimumNode()
                        minNode.left = node.left
                        minNode.balance()
                    }
                }
            }
    }
}
