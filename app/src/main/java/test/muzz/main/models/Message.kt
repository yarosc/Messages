package test.muzz.main.models

import java.time.LocalDateTime

interface Envelope

data class Header(
    val formattedDate: String
): Envelope

data class Message(
    val author: Author,
    val body: String,
    val timestamp: LocalDateTime,
) : Envelope
