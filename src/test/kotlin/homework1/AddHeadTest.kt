package homework1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AddHeadTest {

    @Test
    fun `test add head when list is empty`() {
        val storage = PerformedCommandStorage()
        val action = AddHead()
        action.doAction(storage, listOf(1))
        assertEquals(mutableListOf(1), storage.numbers)
    }

    @Test
    fun `test add head when list is not empty`() {
        val storage = PerformedCommandStorage()
        val action = AddHead()
        action.doAction(storage, listOf(1))
        action.doAction(storage, listOf(2))
        assertEquals(mutableListOf(2, 1), storage.numbers)
    }

    @Test
    fun `cancel add head test`() {
        val storage = PerformedCommandStorage()
        val action = AddHead()
        action.doAction(storage, listOf(1))
        storage.cancelLastAction()
        assertEquals(emptyList<Int>(), storage.numbers)
    }
}
