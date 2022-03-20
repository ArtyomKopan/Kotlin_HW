package homework.one

class PerformedCommandStorage {
    val actions = mutableListOf<Action>()
    val numbers = mutableListOf<Int>()

    fun addAction(action: Action) {
        action.doAction(this)
    }

    fun cancelLastAction() {
        if (actions.isEmpty()) {
            throw IllegalArgumentException("Список пуст, отменять нечего")
        }

        val lastAction = actions.removeLast()
        lastAction.cancelAction(this)
    }
}
