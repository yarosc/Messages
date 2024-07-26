package test.muzz.all.vms

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import test.muzz.all.analytics.AnalyticsHandler

abstract class BaseViewModel<A>(
    private val analyticsHandler: AnalyticsHandler
) : ViewModel(), DefaultLifecycleObserver {

    private val actions = MutableSharedFlow<A>()
    private val jobList = mutableListOf<Job>()

    init {
        listenToActions()
    }

    fun emitAction(action: A) = viewModelScope.launch {
        actions.emit(action)
    }

    fun registerJob(vararg jobs: Job) = jobList.addAll(jobs)

    open fun handleAction(action: A) = analyticsHandler.trackAction()

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)

        for (job in jobList) job.cancel()
    }

    private fun listenToActions() = viewModelScope.launch {
        actions.collectLatest { action ->
            handleAction(action)
        }
    }
}