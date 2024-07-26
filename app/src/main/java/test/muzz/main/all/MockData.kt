package test.muzz.main.all

import test.muzz.all.date.formatTimestamp
import test.muzz.main.models.Author
import test.muzz.main.models.Message
import java.time.LocalDateTime

val messageList: List<Pair<String, String>> = listOf(
    "Me" to "Hello! How are you?",
    "Lisa" to "Hi!",
    "Lisa" to "I'm good!",
    "Lisa" to "How about you?",
    "Me" to "On vacation, enjoying the weather... Always wanted to visit Tenerife and finally I'" +
            "am here. Best choice in a while!",
    "Lisa" to "Oh, right! I know, I've been there a year ago. A nice place indeed. However I" +
            " haven't had the chance to swim in the ocean. Have you?",
    "Me" to "Yes... sort of. I swam in one of those natural pools close to the ocean. They are" +
            "filled with ocean water from the waves. ",
    "Lisa" to "That sounds lovely. What else have you seen?",
)

val simMessageList: List<Pair<String, String>> = listOf(
    "Lisa" to "Did you like it more?",
    "Lisa" to "Have you been to any special place?",
    "Lisa" to "Have you tried their local food?",
    "Lisa" to "Maybe seen or been in a Bodega?",
)

val overflowMessage = "Lisa" to "That's lovely"

fun mockMessageHistory(): List<Message> = messageList.map { mapper(it) }

fun simulatedMessages(index: Int): Message = mapper(
    if (index in simMessageList.indices) simMessageList[index]
    else overflowMessage
)

private fun mapper(pair: Pair<String, String>): Message {
    val now = LocalDateTime.now()
    return Message(
        author = Author(
            name = pair.first,
            owner = pair.first == "Me"
        ),
        body = pair.second,
        rawTimestamp = now.toString(),
        formattedTimeStamp = formatTimestamp(now)
    )
}