package test.muzz.main.ui.comp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    sendMessage: (String) -> Unit,
    scrollDown: () -> Unit,
) {
    UserInputComponent(
        modifier = modifier,
        sendMessage = sendMessage,
        scrollDown = scrollDown,
    )
}