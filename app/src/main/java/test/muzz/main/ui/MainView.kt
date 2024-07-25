package test.muzz.main.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import test.muzz.R
import test.muzz.all.ui.theme.MessagesTheme
import test.muzz.main.all.mockMessages
import test.muzz.main.events.MainState
import test.muzz.main.ui.comp.MainBottomBar
import test.muzz.main.ui.comp.MainTopBar
import test.muzz.main.ui.comp.MessageComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier,
    state: MainState,
    sendMessage: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    val scrollPosition = remember { mutableIntStateOf(0) }

    when (state) {
        is MainState.Messaging -> scrollPosition.intValue = state.messageList.size - 1
        else -> {/*NOOP*/}
    }

    Scaffold(
        topBar = {
            MainTopBar(
                title = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            painter = painterResource(id = R.drawable.avatar),

                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Sarah")
                    }
                },
                actions = {
                    //TODO Add various actions
                }
            )
        },
        bottomBar = {
            MainBottomBar(
                sendMessage = sendMessage,
                scrollDown = {
                    scope.launch {
                        scrollState.scrollToItem(scrollPosition.intValue)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                when (state) {
                    MainState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is MainState.Messaging -> {
                        LazyColumn(
                            state = scrollState,
                            content = {
                                items(items = state.messageList) {
                                    MessageComponent(message = it)
                                }
                            }
                        )

                        LaunchedEffect(Unit) {
                            scrollState.animateScrollToItem(scrollPosition.intValue)
                        }
                    }
                }
            }
        }
    )

}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainViewPreview() {
    MessagesTheme {
        MainView(
            state = MainState.Messaging(
                messageHistory = mockMessages
            ),
            sendMessage = {}
        )
    }
}

