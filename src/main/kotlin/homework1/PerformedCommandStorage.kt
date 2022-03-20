package homework.one

import java.util.EmptyStackException

class PerformedCommandStorage {
    val actions = mutableListOf<Action>()
    val numbers = mutableListOf<Int>()

    fun addAction(action: Action) {
        action.doAction(this)
    }

    fun cancelLastAction() {
        if (actions.isEmpty()) {
            throw EmptyStackException()
        }

        val lastAction = actions.removeLast()
        lastAction.cancelAction(this)
    }
}
