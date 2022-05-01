package homework.three

import java.lang.Integer.max

class AVLTreeNode<K : Comparable<K>, V>(var key: K, var value: V) {
    var left: AVLTreeNode<K, V>? = null
    var right: AVLTreeNode<K, V>? = null
    private var height = 0
    private val balanceFactor: Int
        get() = (right?.height ?: 0) - (left?.height ?: 0)

    private fun fixHeight() {
        height = max(left?.height ?: 0, right?.height ?: 0) + 1
    }

    private fun rotateRight(): AVLTreeNode<K, V> {
        val newNode = left
        newNode ?: return this
        left = newNode.right
        newNode.right = this
        this.fixHeight()
        newNode.fixHeight()
        return newNode
    }

    private fun rotateLeft(): AVLTreeNode<K, V> {
        val newNode = right
        newNode ?: return this
        right = newNode.left
        newNode.left = this
        this.fixHeight()
        newNode.fixHeight()
        return newNode
    }

    fun balance(): AVLTreeNode<K, V>? {
        this.fixHeight()
        return when (this.balanceFactor) {
            RIGHT_REBALANCED_VALUE -> {
                if ((right?.balanceFactor ?: -1) < 0) {
                    right?.rotateRight()
                } else {
                    this.rotateLeft()
                }
            }
            LEFT_REBALANCED_VALUE -> {
                if ((left?.balanceFactor ?: -1) > 0) {
                    left?.rotateLeft()
                } else {
                    this.rotateRight()
                }
            }
            else -> this
        }
    }

    fun get(key: K): V? =
        when {
            this.key == key -> this.value
            this.key > key -> left?.get(key)
            else -> right?.get(key)
        }

    fun findMinimumNode(): AVLTreeNode<K, V>? {
        left ?: return this
        return left?.findMinimumNode()
    }

    fun removeMinimumNode(): AVLTreeNode<K, V>? {
        left ?: return right
        left = left?.removeMinimumNode()
        return this.balance()
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

    fun dfs(listOfNodes: MutableList<AVLTreeNode<K, V>>): MutableList<AVLTreeNode<K, V>> {
        listOfNodes.add(this)
        left?.dfs(listOfNodes)
        right?.dfs(listOfNodes)
        return listOfNodes
    }

    companion object {
        const val RIGHT_REBALANCED_VALUE = 2
        const val LEFT_REBALANCED_VALUE = -2
    }
}
