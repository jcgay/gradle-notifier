package fr.jcgay.gradle.notifier

import java.util.Date


open class Clock(private val startTime: Long) {

    companion object {
        const val MS_PER_MINUTE: Long = 60_000
        const val MS_PER_HOUR: Long = MS_PER_MINUTE * 60
    }

    open fun getTimeInMs(): Long = Date().time - startTime

    fun getTime(): String {
        val timeInMs = getTimeInMs()

        val result = StringBuilder()
        if (timeInMs > MS_PER_HOUR) {
            result.append(timeInMs / MS_PER_HOUR).append(" hrs ")
        }
        if (timeInMs > MS_PER_MINUTE) {
            result.append((timeInMs % MS_PER_HOUR) / MS_PER_MINUTE).append(" mins ")
        }
        result.append((timeInMs % MS_PER_MINUTE) / 1000).append(" secs")
        return result.toString()
    }
}
