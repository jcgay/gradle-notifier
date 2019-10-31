package fr.jcgay.gradle.notifier


class KnownElapsedTimeClock(private val elapsedTimeMs: Long): Clock(0) {

    override fun getTimeInMs(): Long = elapsedTimeMs
}
