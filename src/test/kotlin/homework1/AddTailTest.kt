package homework1

import AddTail
import PerformedCommandStorage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class AddTailTest {
    @Test
    fun `test add tail when list is empty`() {
        val storage = PerformedCommandStorage()
        val action = AddTail()
        action.doAction(storage, listOf(1))
        assertEquals(mutableListOf(1), storage.numbers)
    }

    @Test
    fun `test add tail when list is not empty`() {
        val storage = PerformedCommandStorage()
        val action = AddTail()
        action.doAction(storage, listOf(1))
        action.doAction(storage, listOf(2))
        assertEquals(mutableListOf(1, 2), storage.numbers)
    }

    @Test
    fun `cancel add tail test`() {
        val storage = PerformedCommandStorage()
        val action = AddTail()
        action.doAction(storage, listOf(1))
        storage.cancelLastAction()
        assertEquals(emptyList<Int>(), storage.numbers)
    }
}
