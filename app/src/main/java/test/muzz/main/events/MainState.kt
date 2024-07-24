package test.muzz.main.events

import test.muzz.main.models.Message

sealed class MainState() {
    object Loading: MainState()
    data class Messaging(
        //unread messages
        val messages: List<Message>
    ): MainState()
}
