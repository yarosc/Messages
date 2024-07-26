package test.muzz.main.models

/**
 * Author details
 *
 * can have additional fields like avatar link or status (busy, available, etc)
 */

val OWNER = Author(name = "Me", owner = true)

data class Author(
    val name: String,
    val owner: Boolean = false,
)