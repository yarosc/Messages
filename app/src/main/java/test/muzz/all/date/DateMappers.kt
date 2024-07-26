package test.muzz.all.date

import java.time.LocalDateTime


fun formatTimestamp(date: LocalDateTime): String = "${date.dayOfMonth}, ${date.hour}:${date.minute}"

fun convertTimestamp(timestamp: String): LocalDateTime = LocalDateTime.parse(timestamp)

fun convertDbStringToFormatted(timestamp: String) = formatTimestamp(convertTimestamp(timestamp))
