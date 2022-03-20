package homework.one

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AddHeadTest {

    @Test
    fun `test add head when list is empty`() {
        val storage = PerformedCommandStorage()
        val action = AddHead(1)
        action.doAction(storage)
        assertEquals(mutableListOf(1), storage.numbers)
    }

    @Test
    fun `test add head when list is not empty`() {
        val storage = PerformedCommandStorage()
        val action1 = AddHead(1)
        val action2 = AddHead(2)
        action1.doAction(storage)
        action2.doAction(storage)
        assertEquals(mutableListOf(2, 1), storage.numbers)
    }

    @Test
    fun `cancel add head test`() {
        val storage = PerformedCommandStorage()
        val action = AddHead(1)
        action.doAction(storage)
        storage.cancelLastAction()
        assertEquals(emptyList<Int>(), storage.numbers)
    }
}
