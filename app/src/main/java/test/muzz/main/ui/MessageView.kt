package test.muzz.main.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
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
import test.muzz.common.ui.theme.MessagesTheme
import test.muzz.common.ui.theme.chatBubbleShape
import test.muzz.common.ui.theme.ownerBubbleShape
import test.muzz.main.models.Message

@Composable
fun MessageView(
    modifier: Modifier = Modifier,
    message: Message
) {

    val bubbleAlign: Alignment.Horizontal
    val bubbleShape: Shape
    val bubbleContainerColor: Color
    val bubbleContentColor: Color
    if (message.author.name == "Me") {
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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
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
                    text = "10:11 PM",
                    style = MaterialTheme.typography.bodySmall
                )


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
        MessageView(
            message = mockMessages.last()
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
fun MessageView1Preview() {
    MessagesTheme {
        MessageView(
            message = mockMessages[1]
        )
    }
}

