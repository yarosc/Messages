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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.muzz.R
import test.muzz.all.extensions.penultimate
import test.muzz.all.ui.theme.MessagesTheme
import test.muzz.main.all.mockMessageHistory
import test.muzz.main.events.MainState
import test.muzz.main.models.Header
import test.muzz.main.models.Message
import test.muzz.main.ui.bars.MainBottomBar
import test.muzz.main.ui.bars.MainTopBar
import test.muzz.main.ui.comp.DayHeader
import test.muzz.main.ui.comp.MessageComponent
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier,
    state: MainState,
    sendMessage: (String) -> Unit
) {
    val scrollState = rememberLazyListState()
    var lastSpaced by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

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
                    IconButton(
                        onClick = {
                            scope.launch {
                                //DO Something here
                            }
                            //Or maybe here
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.mainview_content_desc_menu)
                        )
                    }
                }
            )
        },
        bottomBar = {
            MainBottomBar(sendMessage = sendMessage)
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

                        LaunchedEffect(state.messageList.size) {
                            state.messageList.apply {
                                val penultimate = this.penultimate()
                                val last = this.last()
                                if (
                                    penultimate is Message &&
                                    last is Message &&
                                    penultimate.author == last.author
                                ) {
                                    lastSpaced = true
                                    delay(20.seconds)
                                    lastSpaced = false
                                } else lastSpaced = false
                            }
                        }

                        LazyColumn(
                            state = scrollState,
                            content = {
                                itemsIndexed(items = state.messageList) {  index, envelope ->
                                    when (envelope) {
                                        is Header -> DayHeader(formattedDay = envelope.formattedDate)
                                        is Message -> MessageComponent(
                                            message = envelope,
                                            isSpaced = if (state.messageList.lastIndex == index) lastSpaced else false
                                        )
                                    }
                                }
                            }
                        )

                        LaunchedEffect(state.messageList.size) {
                            scrollState.animateScrollToItem(state.messageList.size)
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
                messageHistory = mockMessageHistory()
            ),
            sendMessage = {}
        )
    }
}

