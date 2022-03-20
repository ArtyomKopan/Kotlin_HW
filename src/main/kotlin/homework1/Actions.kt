package homework.one

interface Action {
    fun doAction(storage: PerformedCommandStorage)

    fun cancelAction(storage: PerformedCommandStorage)
}

class AddHead(private val element: Int) : Action {

    override fun doAction(storage: PerformedCommandStorage) {
        storage.numbers.add(0, element)
        storage.actions.add(this)
    }

    override fun cancelAction(storage: PerformedCommandStorage) {
        storage.numbers.removeFirst()
    }
}

class AddTail(private val element: Int) : Action {

    override fun doAction(storage: PerformedCommandStorage) {
        storage.numbers.add(element)
        storage.actions.add(this)
    }

    override fun cancelAction(storage: PerformedCommandStorage) {
        storage.numbers.removeLast()
    }
}

class Move(private val from: Int, private val to: Int) : Action {

    private fun isCorrectIndex(index: Int, storage: PerformedCommandStorage): Boolean {
        return index >= 0 && index < storage.numbers.size
    }

    override fun doAction(storage: PerformedCommandStorage) {
        if (!isCorrectIndex(from, storage) || !isCorrectIndex(to, storage)) {
            throw IllegalArgumentException("Команда MOVE некорректна: один из индексов выходит за границы списка")
        }

        val movableElement = storage.numbers[from]

        storage.numbers.removeAt(from)
        storage.numbers.add(to, movableElement)

        storage.actions.add(this)
    }

    override fun cancelAction(storage: PerformedCommandStorage) {
        val movableElement = storage.numbers[to]

        storage.numbers.removeAt(to)
        storage.numbers.add(from, movableElement)
    }
}
