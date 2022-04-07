package homework.one

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AddTailTest {
    @Test
    fun `test add tail when list is empty`() {
        val storage = PerformedCommandStorage()
        val action = AddTail(1)
        storage.addAction(action)
        assertEquals(listOf(1), storage.publicNumbers)
    }

    @Test
    fun `test add tail when list is not empty`() {
        val storage = PerformedCommandStorage()
        val action1 = AddTail(1)
        val action2 = AddTail(2)
        storage.addAction(action1)
        storage.addAction(action2)
        assertEquals(listOf(1, 2), storage.publicNumbers)
    }

    @Test
    fun `cancel add tail test`() {
        val storage = PerformedCommandStorage()
        val action = AddTail(1)
        storage.addAction(action)
        storage.cancelLastAction()
        assertEquals(emptyList<Int>(), storage.publicNumbers)
    }
}
