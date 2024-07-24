package test.muzz.main.models

data class Author(
    val name: String,
    val avatar: String? = "",
    val owner: Boolean = false
) {
}