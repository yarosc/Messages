package test.muzz.broadcasts

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import javax.inject.Inject

const val THRESHOLD = 3_600_000L
const val TEST_THRESHOLD = 12_000L

class MessengerAlarmManager @Inject constructor() {

    private lateinit var alarmManager: AlarmManager
    private lateinit var context: Context

    fun initServiceFromContext(context: Context) {
        this.context = context
        alarmManager = context.getSystemService(AlarmManager::class.java)
    }

    fun schedule() {
        val intent = Intent(context, MessengerAlarmReceiver::class.java).apply {
            putExtra(REFRESH_SIGNAL, REFRESH_SIGNAL)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC,
            THRESHOLD,
            THRESHOLD,
            PendingIntent.getBroadcast(
                context,
                REFRESH_SIGNAL.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        )
    }

    fun cancel() = alarmManager.cancel(
        PendingIntent.getBroadcast(
            context,
            REFRESH_SIGNAL.hashCode(),
            Intent(context, MessengerAlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    )

}