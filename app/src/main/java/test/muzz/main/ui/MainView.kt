package test.muzz.main.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import test.muzz.common.ui.theme.MessagesTheme
import test.muzz.main.events.MainState
import test.muzz.main.models.Author
import test.muzz.main.models.Message

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    mainState: MainState
) {

    Scaffold(
        topBar = {
            //TODO TopBar
        },
        bottomBar = {
            //TODO BottomBar
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                when(mainState) {
                    MainState.Loading -> TODO()
                    is MainState.Messaging -> {
                        LazyColumn(
                            content = {
                                items(items = mockMessages) {
                                    MessageView(message = it)
                                }
                            }
                        )
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
            mainState = MainState.Messaging(
                messages = mockMessages
            )
        )
    }
}

val mockMessages = listOf(
    Message(
        author = Author(
            name = "Me"
        ),
        body = "Hello! How are you?"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "Hi!"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "I'm good!"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "How about you?"
    ),
    Message(
        author = Author(
            name = "Me"
        ),
        body = "On vacation, enjoying the weather... Always wanted to visit Tenerife and finally I'" +
                "am here. Best choice in a while!"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "Oh, right! I know, I've been there a year ago. A nice place indeed. However I" +
                " haven't had the chance to swim in the ocean. Have you?"
    ),
    Message(
        author = Author(
            name = "Me"
        ),
        body = "Yes... sort of. I swam in one of those natural pools close to the ocean. They are" +
                "filled with ocean water from the waves. "
    ),
)