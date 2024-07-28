package test.muzz.main.ui.comp

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test.muzz.all.ui.theme.MessagesTheme
import test.muzz.all.ui.theme.chatBubbleShape
import test.muzz.all.ui.theme.ownerBubbleShape
import test.muzz.main.all.mockMessageHistory
import test.muzz.main.models.Message

//TODO Split in 2 functions
@Composable
fun MessageComponent(
    modifier: Modifier = Modifier,
    message: Message,
    isSpaced: Boolean = false
) {
    val bubbleAlign: Alignment.Horizontal
    val bubbleShape: Shape
    val bubbleContainerColor: Color
    val bubbleContentColor: Color

    if (message.author.owner) {
        bubbleAlign = Alignment.End
        bubbleShape = ownerBubbleShape
        bubbleContainerColor = MaterialTheme.colorScheme.primary
        bubbleContentColor = MaterialTheme.colorScheme.onPrimary
    } else {
        bubbleAlign = Alignment.Start
        bubbleShape = chatBubbleShape
        bubbleContentColor = MaterialTheme.colorScheme.secondary
        bubbleContainerColor = MaterialTheme.colorScheme.secondaryContainer
    }

    val paddingValues = if (isSpaced) PaddingValues(16.dp, 16.dp, 16.dp, 2.dp)
    else PaddingValues(16.dp, 2.dp, 16.dp, 2.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = bubbleAlign
    ) {
        Card(
            shape = bubbleShape,
            colors = CardDefaults.cardColors(
                containerColor = bubbleContainerColor,
                contentColor = bubbleContentColor
            )
        ) {
            Column(
                modifier = modifier.padding(16.dp),
            ) {
                Text(
                    text = message.author.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = message.body,
                    style = MaterialTheme.typography.bodyMedium
                )
                //TODO Add row for message state (if time allows)
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MessageViewPreview() {
    MessagesTheme {
        MessageComponent(
            message = mockMessageHistory().last(),
        )
    }
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MessageViewSpacedPreview() {
    MessagesTheme {
        MessageComponent(
            message = mockMessageHistory().last(),
            isSpaced = true
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MessageComponentPreview() {
    MessagesTheme {
        MessageComponent(
            message = mockMessageHistory()[1],
        )
    }
}

