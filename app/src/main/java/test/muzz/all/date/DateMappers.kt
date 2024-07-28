package test.muzz.all.date

import java.time.LocalDateTime


/**
 * Capitalization should be managed properly with Locale but due to lack of time I've skipped this step
 */
fun dayOfTheWeekFormatted(date: LocalDateTime) =
    StringBuilder(
        date.dayOfWeek.toString().lowercase()
    ).also {
        it[0] = it[0].uppercaseChar()
    }.toString()

fun formatTimestamp(date: LocalDateTime): String =
    "${dayOfTheWeekFormatted(date)}, ${date.hour}:${date.minute}"

fun convertTimestamp(timestamp: String): LocalDateTime = LocalDateTime.parse(timestamp)