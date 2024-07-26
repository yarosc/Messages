package test.muzz.main.ui.comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test.muzz.all.ui.theme.MessagesTheme


@Composable
fun UserInputComponent(
    modifier: Modifier = Modifier,
    sendMessage: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(64.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue())
        }

        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Max),
            shape = RoundedCornerShape(64.dp),
            value = textFieldValue,
            maxLines = 1,
            onValueChange = {
                textFieldValue = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Send
            ),
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            onClick = {
                sendMessage(textFieldValue.text.trim())
                textFieldValue = TextFieldValue()
            },
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Send,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "Send"
                )
            }
        )
    }
}

@Preview
@Composable
fun UserInputComponentPreview() {
    MessagesTheme {
        UserInputComponent(sendMessage = {})
    }
}