package testwork1

import java.util.TreeSet

class PriorityQueue<K : Comparable<K>, E>(Comparator: (Pair<K, E>, Pair<K, E>) -> Int) {
    private val queue = TreeSet<Pair<K, E>>(Comparator)

    fun enqueue(element: E, priority: K) = queue.add(Pair(priority, element))

    fun peek(): E = queue.first().second ?: throw IllegalArgumentException("Очередь пуста!")

    fun remove() = queue.remove(queue.first())

    fun roll(): E {
        val maxElement = queue.first().second
        queue.remove(queue.first())
        return maxElement ?: throw IllegalArgumentException("Очередь пуста!")
    }
}


