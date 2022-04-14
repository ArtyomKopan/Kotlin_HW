package homework.three

import java.lang.Integer.max

const val RIGHT_REBALANCED_VALUE = 2
const val LEFT_REBALANCED_VALUE = -2

class AVLTreeNode<K : Comparable<K>, V>(var key: K, var value: V) {
    var left: AVLTreeNode<K, V>? = null
    var right: AVLTreeNode<K, V>? = null
    private var height = 0
    private val balanceFactor: Int
        get() = (right?.height ?: 0) - (left?.height ?: 0)

    private fun fixHeight() {
        height = max(left?.height ?: 0, right?.height ?: 0) + 1
    }

    private fun copy(node: AVLTreeNode<K, V>) {
        key = node.key
        value = node.value
        left = node.left
        right = node.right
        height = node.height
    }

    private fun rotateRight() {
        val newNode = left
        newNode ?: return
        left = newNode.right
        newNode.right = this
        this.fixHeight()
        newNode.fixHeight()
        this.copy(newNode)
    }

    private fun rotateLeft() {
        val newNode = right
        newNode ?: return
        right = newNode.left
        newNode.left = this
        this.fixHeight()
        newNode.fixHeight()
        this.copy(newNode)
    }

    fun balance() {
        this.fixHeight()
        if (this.balanceFactor == RIGHT_REBALANCED_VALUE) {
            if ((right?.balanceFactor ?: 0) < 0) {
                right?.rotateRight()
            }
            this.rotateLeft()
        }
        if (this.balanceFactor == LEFT_REBALANCED_VALUE) {
            if ((left?.balanceFactor ?: 0) > 0) {
                left?.rotateLeft()
            }
            this.rotateRight()
        }
    }

    fun put(key: K, value: V): V? =
        when {
            this.key == key -> {
                val oldValue = this.value
                this.value = value
                oldValue
            }
            key > this.key ->
                if (right == null) {
                    right = AVLTreeNode(key, value)
                    this.balance()
                    null
                } else {
                    right?.put(key, value)
                }
            else ->
                if (left == null) {
                    left = AVLTreeNode(key, value)
                    this.balance()
                    null
                } else {
                    left?.put(key, value)
                }
        }

    fun get(key: K): V? =
        when {
            this.key == key -> this.value
            this.key > key -> left?.get(key)
            else -> right?.get(key)
        }

    private fun removeMinimumNode(): AVLTreeNode<K, V>? {
        left ?: return right
        left = left?.removeMinimumNode()
        this.balance()
        return this
    }

    fun remove(key: K): Pair<AVLTreeNode<K, V>?, V?> =
        when {
            key < this.key -> {
                val removedNode = left?.remove(key)
                left = removedNode?.first
                this.balance()
                Pair(this, removedNode?.second)
            }
            key > this.key -> {
                val removedNode = right?.remove(key)
                right = removedNode?.first
                this.balance()
                Pair(this, removedNode?.second)
            }
            else -> {
                val removedValue = value
                val minNode = right?.removeMinimumNode()
                if (minNode == null) {
                    left?.balance()
                    Pair(left, removedValue)
                } else {
                    minNode.right = right?.right
                    minNode.left = left
                    minNode.balance()
                    Pair(minNode, removedValue)
                }
            }
        }

    fun replaceValue(key: K, newValue: V): V? =
        when {
            this.key == key -> {
                val oldValue = this.value
                this.value = newValue
                oldValue
            }
            this.key > key -> left?.replaceValue(key, newValue)
            else -> right?.replaceValue(key, newValue)
        }
}
