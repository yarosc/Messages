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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test.muzz.R
import test.muzz.all.ui.theme.MessagesTheme
import test.muzz.main.all.mockMessages
import test.muzz.main.events.MainState
import test.muzz.main.ui.comp.MainTopBar
import test.muzz.main.ui.comp.MessageComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier,
    mainState: MainState
) {

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
                    //TODO ADD ... button
                }
            )
        },
        bottomBar = {
            //TODO BottomBar
        },
        content = { paddingValues ->
            Column(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                when (mainState) {
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
                            content = {
                                items(items = mockMessages) {
                                    MessageComponent(message = it)
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

