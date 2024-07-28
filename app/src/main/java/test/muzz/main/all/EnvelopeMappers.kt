package test.muzz.main.all

import test.muzz.all.date.formatTimestamp
import test.muzz.main.models.Envelope
import test.muzz.main.models.Header
import test.muzz.main.models.Message
import java.time.Duration

fun encloseMessages(list: List<Message>): List<Envelope> {
    val envelopes = mutableListOf<Envelope>()

    for (i in 0..<list.size - 1) {
        val duration = Duration.between(list[i].timestamp, list[i + 1].timestamp)
        envelopes.add(list[i])
        if (duration.toHours() > 1) {
            envelopes.add(
                Header(formatTimestamp(list[i + 1].timestamp))
            )
        }
    }

    return envelopes
}