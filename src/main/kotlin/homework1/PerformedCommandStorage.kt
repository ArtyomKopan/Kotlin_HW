package homework.one

class PerformedCommandStorage {
    private val actions = mutableListOf<Action>()
    private val numbers = mutableListOf<Int>()
    val publicNumbers: List<Int>
    get() = numbers.toList()

    fun addAction(action: Action) {
        actions.add(action)
        action.doAction(numbers)
    }

    fun cancelLastAction() {
        require(actions.isNotEmpty()) { "Список пуст, отменять нечего" }

        val lastAction = actions.removeLast()
        lastAction.cancelAction(numbers)
    }
}
