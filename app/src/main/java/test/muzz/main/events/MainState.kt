package test.muzz.main.events

import androidx.compose.runtime.toMutableStateList
import test.muzz.main.models.Message

sealed class MainState() {
    data object Loading: MainState()
    class Messaging(
        messageHistory: List<Message>
    ): MainState() {
        private val _messageList = messageHistory.toMutableStateList()
        val messageList: List<Message> = _messageList

        fun add(message: Message) {
            _messageList.add(message)
        }
    }
}
