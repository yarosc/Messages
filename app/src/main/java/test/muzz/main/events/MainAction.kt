package test.muzz.main.events

sealed class MainAction {
    data class SendMessage(val message: String) : MainAction()
}