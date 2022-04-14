package homework.three

import java.util.LinkedList
import java.util.Queue

class AVLTree<K : Comparable<K>, V> : MutableMap<K, V> {
    override var size = 0
    override val keys = mutableSetOf<K>()
    override val values = mutableListOf<V>()
    override val entries = mutableSetOf<MutableMap.MutableEntry<K, V>>()

    private var treeRoot: AVLTreeNode<K, V>? = null

    override fun put(key: K, value: V): V? {
        val oldValue = treeRoot?.get(key)
        if (oldValue != null) {
            entries.remove(Entry(key, oldValue))
        }
        if (treeRoot == null) {
            treeRoot = AVLTreeNode(key, value)
        } else {
            treeRoot?.put(key, value)
        }
        treeRoot?.balance()
        size++
        keys.add(key)
        values.add(value)
        entries.add(Entry(key, value))
        return oldValue
    }

    override fun putAll(from: Map<out K, V>) = from.forEach { this[it.key] = it.value }

    override fun remove(key: K): V? {
        val removedNode = treeRoot?.remove(key)
        treeRoot = removedNode?.first
        val removedValue = removedNode?.second
        if (removedValue != null) {
            size--
            keys.remove(key)
            values.remove(removedValue)
            entries.remove(Entry(key, removedValue))
        }
        return removedValue
    }

    override fun containsKey(key: K) = treeRoot?.get(key) != null

    override fun containsValue(value: V) = values.contains(value)

    override fun get(key: K) = treeRoot?.get(key)

    override fun isEmpty() = treeRoot == null

    override fun replace(key: K, value: V): V? {
        val oldValue = treeRoot?.replaceValue(key, value)
        if (oldValue != null) {
            entries.remove(Entry(key, oldValue))
            entries.add(Entry(key, value))
            values.remove(oldValue)
            values.add(value)
        }
        return oldValue
    }

    override fun clear() {
        treeRoot = null
        keys.clear()
        values.clear()
        entries.clear()
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
}
