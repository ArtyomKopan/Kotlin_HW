package retest.two

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val buttons: List<Button>,
        val selectedButton: Button?,
        var text: List<String>
    )

    private fun initialState(): State = State(buttons = fetchButtons(), null, mutableListOf())

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onButtonSelect(itemId: Int) = updateState {
        copy(selectedButton = buttons.find { it.id == itemId })
        when (itemId) {
            NUMBER_BEST_QUOTES -> copy(text = getBestQuotes())
            NUMBER_NEW_QUOTES -> copy(text = getNewQuotes())
            NUMBER_RANDOM_QUOTES -> copy(text = getRandomQuote())
            else -> copy(text = state.text)
        }
    }
}
