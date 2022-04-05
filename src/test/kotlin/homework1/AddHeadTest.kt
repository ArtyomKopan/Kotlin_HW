package homework.one

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AddHeadTest {

    @Test
    fun `test add head when list is empty`() {
        val storage = PerformedCommandStorage()
        val action = AddHead(1)
        storage.addAction(action)
        assertEquals(listOf(1), storage.publicNumbers)
    }

    @Test
    fun `test add head when list is not empty`() {
        val storage = PerformedCommandStorage()
        val action1 = AddHead(1)
        val action2 = AddHead(2)
        storage.addAction(action1)
        storage.addAction(action2)
        assertEquals(listOf(2, 1), storage.publicNumbers)
    }

    @Test
    fun `cancel add head test`() {
        val storage = PerformedCommandStorage()
        val action = AddHead(1)
        storage.addAction(action)
        storage.cancelLastAction()
        assertEquals(emptyList<Int>(), storage.publicNumbers)
    }
}
