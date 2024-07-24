package test.muzz.all.analytics

import javax.inject.Inject

//Analytics aggregator
class AnalyticsHandler @Inject constructor(
//    val isDebug: Boolean
) {

    fun trackAction() {
        // Google Analytics
        // Plum Analytics
        // ...
        // Any other analytics tool
    }

    fun trackEvent() {

    }

}