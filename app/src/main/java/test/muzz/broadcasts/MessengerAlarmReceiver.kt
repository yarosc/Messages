package test.muzz.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val REFRESH_SIGNAL = "REFRESH_SIGNAL"

@AndroidEntryPoint
class MessengerAlarmReceiver @Inject constructor(): BroadcastReceiver() {

    private var alarmListener: MessengerAlarmListener? = null

    fun registerListener(alarmListener: MessengerAlarmListener) {
        this.alarmListener = alarmListener
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        alarmListener?.onReceivedAlarm()
    }
}