package test.muzz.all.vms

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import test.muzz.all.analytics.AnalyticsHandler

abstract class BaseViewModel<A>(
    private val analyticsHandler: AnalyticsHandler
) : ViewModel(), DefaultLifecycleObserver {

    private val actions = MutableSharedFlow<A>()

    init {
        listenToActions()
    }

    fun emitAction(action: A) = viewModelScope.launch {
        actions.emit(action)
    }

    open fun handleAction(action: A) {
        analyticsHandler.trackAction()
    }

    private fun listenToActions() = viewModelScope.launch {
        actions.collectLatest { action ->
            handleAction(action)
        }
    }
}