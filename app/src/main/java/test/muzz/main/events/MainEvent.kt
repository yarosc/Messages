package test.muzz.main.events

sealed class MainEvent {
    data object GenericEvent: MainEvent()
}