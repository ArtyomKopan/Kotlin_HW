package homework1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MoveTest {
    @Test
    fun `test move when indexes is not equal`() {
        val storage = PerformedCommandStorage()
        val addTail = AddTail()
        val move = Move()
        addTail.doAction(storage, listOf(1))
        addTail.doAction(storage, listOf(2))
        move.doAction(storage, listOf(0, 1))
        assertEquals(mutableListOf(2, 1), storage.numbers)
    }

    @Test
    fun `test move when indexes is equal`() {
        val storage = PerformedCommandStorage()
        val addTail = AddTail()
        val move = Move()
        addTail.doAction(storage, listOf(1))
        addTail.doAction(storage, listOf(2))
        move.doAction(storage, listOf(1, 1))
        assertEquals(mutableListOf(1, 2), storage.numbers)
    }

    @Test
    fun `cancel move test`() {
        val storage = PerformedCommandStorage()
        val addTail = AddTail()
        val move = Move()
        addTail.doAction(storage, listOf(1))
        addTail.doAction(storage, listOf(2))
        move.doAction(storage, listOf(0, 1))
        storage.cancelLastAction()
        assertEquals(mutableListOf(1, 2), storage.numbers)
    }
}
