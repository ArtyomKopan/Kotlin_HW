package view

import Screen
import ViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Suppress("FunctionNaming")
@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state

    Column {
        MaterialTheme {
            when (state.screen) {
                Screen.START_GAME -> {
                    StartGame(viewModel::onStartGame)
                }
                Screen.MODE_CHOICE -> {
                    GameModeChoice(viewModel::onChoiceSingleMode, viewModel::onChoiceBotMode)
                }
                Screen.SIDE_CHOICE -> {
                    SideChoice(viewModel::onChoiceSide)
                }
                Screen.GAME_FIELD -> {
                    ButtonsList(
                        state,
                        viewModel::onButtonSelect
                    )
                }
            }
        }
    }
}
