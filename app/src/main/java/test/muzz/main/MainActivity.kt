package test.muzz.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import test.muzz.all.ui.theme.MessagesTheme
import test.muzz.broadcasts.MessengerAlarmListener
import test.muzz.broadcasts.MessengerAlarmManager
import test.muzz.broadcasts.MessengerAlarmReceiver
import test.muzz.main.ui.MainFrame
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), MessengerAlarmListener {

    private val viewModel: MainViewModel by viewModels()

    @Inject lateinit var alarmReceiver: MessengerAlarmReceiver
    @Inject lateinit var alarmManager: MessengerAlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessagesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainFrame()
                }
            }
        }

        setEvents()
        alarmManager.initServiceFromContext(this)
        alarmReceiver.registerListener(this)
    }


    override fun onResume() {
        super.onResume()
        alarmManager.schedule()
    }

    override fun onPause() {
        super.onPause()
        alarmManager.cancel()
    }

    override fun onReceivedAlarm() {
        viewModel.refreshMessagePresentation()
    }

    private fun setEvents() {
        lifecycle.addObserver(viewModel)

        lifecycleScope.launch {
            viewModel.events.collect {
                //DO Something useful
            }
        }
    }
}