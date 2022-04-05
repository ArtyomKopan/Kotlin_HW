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
        storage.addAction(addTail1)
        storage.addAction(addTail2)
        storage.addAction(move)
        assertEquals(listOf(2, 1), storage.publicNumbers)
    }

    @Test
    fun `test move when indexes is equal`() {
        val storage = PerformedCommandStorage()
        val addTail1 = AddTail(1)
        val addTail2 = AddTail(2)
        val move = Move(1, 1)
        storage.addAction(addTail1)
        storage.addAction(addTail2)
        storage.addAction(move)
        assertEquals(listOf(1, 2), storage.publicNumbers)
    }

    @Test
    fun `cancel move test`() {
        val storage = PerformedCommandStorage()
        val addTail1 = AddTail(1)
        val addTail2 = AddTail(2)
        val move = Move(0, 1)
        storage.addAction(addTail1)
        storage.addAction(addTail2)
        storage.addAction(move)
        storage.cancelLastAction()
        assertEquals(listOf(1, 2), storage.publicNumbers)
    }
}
