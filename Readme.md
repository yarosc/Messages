# Readme

# Brief description

As per requirements this is a simulation of a messaging app, with fully functional UI and DB for storing messages. It uses MVVM architecture with `UseCase`s. In this particular case `UseCase`s are for demo purposes. I have used both `Coroutines` and `Flow` to show familiarity with them. Both can be used interchangeably however is much easier to express streams of data in `Flow`  while coroutines excel in concurrent tasks (asynchronous || multithreaded).

The app also uses `Hilt` and `ROOM` libraries, also includes `lifecycle` to access `activity` lifecycle from `BaseViewModel` and descendant `VM`s. The app simulates an analytics agregator that will intercept events inside `BaseViewModel` so integrating any analytics framework becomes easy.

On the build side, I’ve used `gradle` with `kts` format and `toml` for versioning libraries. Also, I’ve included `KSP` instead of `KAPT`. 

There are no `Fragment`s. They’re unnecessary in Jetpack Compose although can be useful if integrated with legacy views.

## How the app works

At the app's launch, the **first** group of messages is saved to the DB. During the `onResume` state, the app loads all messages from the database and displays them in the UI. Once everything is visible on the UI and the user sends a message (owner), the app saves it into the DB. Any updates to the DB will trigger a new emission to the UI layer, ensuring that the latest message added to the DB is also reflected in the UI.

This approach does respect the Unidirectional Data Flow and is a Token Ring of sorts for messages. Alternatively, messages could’ve been emitted to the UI and DB at the same time nonetheless I’ve preferred “Token Ring” (UI -> ViewModel -> DB -> Flow -> UI) to demo an observable DataBase Table required in the test description.

## Other considerations

- There are no unit tests due to lack of time and no such requirements
- Alarm manager sends events to a running app. It refreshes all the messages tu add **day and time** header as per requirements. There are alternative approaches to this but it would require further development.
- I’ve created few static messages that will be triggered by the app to simulate history and an interlocutor user.




