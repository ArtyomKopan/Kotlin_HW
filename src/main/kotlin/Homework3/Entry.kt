package homework.three

class Entry<K : Comparable<K>, V>(override var key: K, override var value: V) : MutableMap.MutableEntry<K, V> {
    override fun setValue(newValue: V): V {
        val oldValue = value
        value = newValue
        return oldValue
    }
}
