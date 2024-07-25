package test.muzz.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import test.muzz.main.MainViewModel
import test.muzz.main.events.MainAction

@Composable
fun MainFrame(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.viewState.collectAsState()

    MainView(
        modifier,
        state,
        sendMessage = {
            viewModel.emitAction(MainAction.SendMessage(it))
        }
    )
}