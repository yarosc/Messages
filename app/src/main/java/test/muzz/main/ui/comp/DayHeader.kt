package test.muzz.main.ui.comp

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import test.muzz.all.date.formatTimestamp
import test.muzz.all.ui.theme.MessagesTheme
import java.time.LocalDateTime

@Composable
fun DayHeader(
    modifier: Modifier = Modifier,
    formattedDay: String
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = formattedDay)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DayHeaderPreview() {
    MessagesTheme {
        DayHeader(formattedDay = formatTimestamp(LocalDateTime.now()))
    }
}
