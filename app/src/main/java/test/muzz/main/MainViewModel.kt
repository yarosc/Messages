package test.muzz.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import test.muzz.all.analytics.AnalyticsHandler
import test.muzz.all.date.formatTimestamp
import test.muzz.all.multithreading.Needle
import test.muzz.all.vms.BaseViewModel
import test.muzz.domain.main.MainUseCase
import test.muzz.main.all.mockMessageHistory
import test.muzz.main.all.simulatedMessages
import test.muzz.main.events.MainAction
import test.muzz.main.events.MainState
import test.muzz.main.models.Message
import test.muzz.main.models.OWNER
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val needle: Needle,
    analyticsHandler: AnalyticsHandler
) : BaseViewModel<MainAction>(analyticsHandler) {
    private val _viewState = MutableStateFlow<MainState>(MainState.Loading)
    val viewState = _viewState.asStateFlow()

    private var simMessageIndex = 0

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch(needle.io()) {
            mainUseCase.saveMessages(mockMessageHistory())
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        if (viewState.value is MainState.Loading) initializeMessageHistory()

        listenToMessageUpdates()
        simulateIterlocutor()
    }

    override fun handleAction(action: MainAction) {
        super.handleAction(action)

        when (action) {
            is MainAction.SendMessage -> sendMessage(action.message)
        }
    }

    private fun simulateIterlocutor() {
        val simJob = viewModelScope.launch(needle.io()) {
            while (isActive) {
                delay(3_000)
                val currentState = viewState.value as? MainState.Messaging
                currentState?.let {
                    if (it.messageList.last().author.owner) {
                        mainUseCase.save(simulatedMessages(simMessageIndex++))
                    }
                }
            }
        }

        registerJob(simJob)
    }

    private fun listenToMessageUpdates() {
        registerJob(
            viewModelScope.launch(needle.main()) {
                mainUseCase.getLatest().flowOn(needle.io()).collect { message ->
                    (viewState.value as? MainState.Messaging)?.let {
                        val currentState = _viewState.value as MainState.Messaging
                        currentState.add(message)
                    }
                }
            }
        )
    }

    private fun initializeMessageHistory() {
        viewModelScope.launch(needle.io()) {
            val messages = mainUseCase.getMessageHistory()

            withContext(needle.main()) {
                _viewState.emit(MainState.Messaging(messages))
            }
        }
    }

    private fun sendMessage(messageBody: String) {
        viewModelScope.launch(needle.io()) {
            val timestamp = LocalDateTime.now()
            val message = Message(
                OWNER,
                body = messageBody,
                rawTimestamp = timestamp.toString(),
                formattedTimeStamp = formatTimestamp(timestamp)
            )

            mainUseCase.save(message)
        }
    }
}