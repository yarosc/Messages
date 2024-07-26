package test.muzz.main.models

data class Message(
    val author: Author,
    val body: String,
    val rawTimestamp: String,
    val formattedTimeStamp: String,
)
