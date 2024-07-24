package test.muzz.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import test.muzz.all.analytics.AnalyticsHandler
import test.muzz.all.multithreading.ProdNeedle
import test.muzz.all.vms.BaseViewModel
import test.muzz.domain.main.MainUseCase
import test.muzz.main.all.mockMessages
import test.muzz.main.events.MainAction
import test.muzz.main.events.MainState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainUseCase: MainUseCase,
    val needle: ProdNeedle, //TODO configure injection
    analyticsHandler: AnalyticsHandler
) : BaseViewModel<MainAction>(analyticsHandler) {
    private val _viewState = MutableStateFlow<MainState>(MainState.Loading)
    val viewState = _viewState.asStateFlow()

    val mockMessage = MainState.Messaging(
        messages = mockMessages
    )

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        viewModelScope.launch(needle.io()) {
            delay(1000) //Simulating loading


            withContext(needle.main()) {
                _viewState.emit(mockMessage)
            }
        }
    }
}