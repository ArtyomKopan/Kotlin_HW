import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PerformedCommandStorageTest {

    @ParameterizedTest
    @MethodSource("addTestInputData")
    fun `test storage`(expected: MutableList<Int>, firstArg: MutableList<Triple<String, Int?, Int?>>) {
        assertEquals(expected, commandProcessing(firstArg))
    }

    companion object {
        @JvmStatic
        fun addTestInputData() = listOf(
            Arguments.of(
                mutableListOf(4, 2, 1),
                mutableListOf(
                    Triple("ADD_HEAD", 1, null),
                    Triple("ADD_TAIL", 2, null),
                    Triple("ADD_TAIL", 3, null),
                    Triple("CANCEL", null, null),
                    Triple("ADD_TAIL", 4, null),
                    Triple("SWAP", 0, 2)
                )
            ),
            Arguments.of(
                mutableListOf(1),
                mutableListOf(
                    Triple("CANCEL", null, null),
                    Triple("ADD_HEAD", 1, null)
                )
            ),
            Arguments.of(
                mutableListOf(2, 3, 4, 5),
                mutableListOf(
                    Triple("ADD_HEAD", 1, null),
                    Triple("CANCEL", null, null),
                    Triple("CANCEL", null, null),
                    Triple("ADD_TAIL", 2, null),
                    Triple("ADD_TAIL", 3, null),
                    Triple("ADD_TAIL", 4, null),
                    Triple("ADD_TAIL", 5, null),
                    Triple("ADD_TAIL", 6, null),
                    Triple("CANCEL", null, null)
                )
            )
        )
    }
}
