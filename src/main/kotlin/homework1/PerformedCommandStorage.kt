package homework1

enum class Command {
    ADD_HEAD, ADD_TAIL, MOVE
}

interface Action {
    fun doAction(storage: PerformedCommandStorage, arguments: List<Int>)

    fun cancelAction(storage: PerformedCommandStorage)
}

class AddHead : Action {
    override fun doAction(storage: PerformedCommandStorage, arguments: List<Int>) {
        val element = arguments.first()
        storage.numbers.add(0, element)
        storage.actions.add(Command.ADD_HEAD)
    }

    override fun cancelAction(storage: PerformedCommandStorage) {
        storage.numbers.removeFirst()
    }
}

class AddTail : Action {
    override fun doAction(storage: PerformedCommandStorage, arguments: List<Int>) {
        val element = arguments.first()
        storage.numbers.add(element)
        storage.actions.add(Command.ADD_TAIL)
    }

    override fun cancelAction(storage: PerformedCommandStorage) {
        storage.numbers.removeLast()
    }
}

class Move : Action {
    override fun doAction(storage: PerformedCommandStorage, arguments: List<Int>) {
        val firstIndex = arguments.first()
        val secondIndex = arguments.last()
        val movableElement = storage.numbers[firstIndex]

        storage.numbers.removeAt(firstIndex)
        storage.numbers.add(secondIndex, movableElement)

        storage.moves.add(Pair(firstIndex, secondIndex))
        storage.actions.add(Command.MOVE)
    }

    override fun cancelAction(storage: PerformedCommandStorage) {
        val firstIndex = storage.moves.last().first
        val secondIndex = storage.moves.last().second

        doAction(storage, listOf(firstIndex, secondIndex))

        storage.moves.removeLast()
    }
}

class PerformedCommandStorage {
    val actions = mutableListOf<Command>()
    val numbers = mutableListOf<Int>()
    val moves = mutableListOf<Pair<Int, Int>>()

    fun addAction(action: Action, arguments: List<Int>) {
        action.doAction(this, arguments)
    }

    fun cancelLastAction() {
        if (actions.isEmpty()) {
            println("Список команд пуст, отменять нечего")
            return
        }

        when (actions.removeLast()) {
            Command.ADD_HEAD -> {
                val action = AddHead()
                action.cancelAction(this)
            }
            Command.ADD_TAIL -> {
                val action = AddTail()
                action.cancelAction(this)
            }
            Command.MOVE -> {
                val action = Move()
                action.cancelAction(this)
            }
        }
    }
}
