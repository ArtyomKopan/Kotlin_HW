package homework.one

interface Action {
    fun doAction(numbers: MutableList<Int>)
    fun cancelAction(numbers: MutableList<Int>)
}

class AddHead(private val element: Int) : Action {
    override fun doAction(numbers: MutableList<Int>) {
        numbers.add(0, element)
    }

    override fun cancelAction(numbers: MutableList<Int>) {
        numbers.removeFirst()
    }
}

class AddTail(private val element: Int) : Action {
    override fun doAction(numbers: MutableList<Int>) {
        numbers.add(element)
    }

    override fun cancelAction(numbers: MutableList<Int>) {
        numbers.removeLast()
    }
}

class Move(private val from: Int, private val to: Int) : Action {
    private fun isCorrectIndex(index: Int, numbers: List<Int>) = index >= 0 && index < numbers.size

    override fun doAction(numbers: MutableList<Int>) {
        require(
            isCorrectIndex(from, numbers) && isCorrectIndex(to, numbers)
        ) { "Команда MOVE некорректна: один из индексов выходит за границы списка" }

        val movableElement = numbers[from]
        numbers.removeAt(from)
        numbers.add(to, movableElement)
    }

    override fun cancelAction(numbers: MutableList<Int>) {
        val movableElement = numbers[to]
        numbers.removeAt(to)
        numbers.add(from, movableElement)
    }
}
