package test.muzz.main.ui.bars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import test.muzz.main.ui.comp.UserInputComponent

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    sendMessage: (String) -> Unit,
) {
    UserInputComponent(
        modifier = modifier,
        sendMessage = sendMessage,
    )
}