package homework.one

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MoveTest {
    @Test
    fun `test move when indexes is not equal`() {
        val storage = PerformedCommandStorage()
        val addTail1 = AddTail(1)
        val addTail2 = AddTail(2)
        val move = Move(0, 1)
        addTail1.doAction(storage)
        addTail2.doAction(storage)
        move.doAction(storage)
        assertEquals(mutableListOf(2, 1), storage.numbers)
    }

    @Test
    fun `test move when indexes is equal`() {
        val storage = PerformedCommandStorage()
        val addTail1 = AddTail(1)
        val addTail2 = AddTail(2)
        val move = Move(1, 1)
        addTail1.doAction(storage)
        addTail2.doAction(storage)
        move.doAction(storage)
        assertEquals(mutableListOf(1, 2), storage.numbers)
    }

    @Test
    fun `cancel move test`() {
        val storage = PerformedCommandStorage()
        val addTail1 = AddTail(1)
        val addTail2 = AddTail(2)
        val move = Move(0, 1)
        addTail1.doAction(storage)
        addTail2.doAction(storage)
        move.doAction(storage)
        storage.cancelLastAction()
        assertEquals(mutableListOf(1, 2), storage.numbers)
    }
}
