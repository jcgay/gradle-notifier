package fr.jcgay.gradle.notifier.extension

import fr.jcgay.gradle.notifier.Clock
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.NANOSECONDS


class TimeThreshold(var time: Long = -1, var unit: TimeUnit = NANOSECONDS): Comparable<TimeThreshold> {

    constructor(timer: Clock) : this(timer.getTimeInMs(), MILLISECONDS)

    override fun compareTo(other: TimeThreshold): Int {
        return when {
            this == other -> 0
            unit == other.unit -> time.compareTo(other.time)
            else -> unit.toNanos(time).compareTo(other.unit.toNanos(other.time))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TimeThreshold

        if (time != other.time) return false
        if (unit != other.unit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = time.hashCode()
        result = 31 * result + unit.hashCode()
        return result
    }

    companion object {
        fun of(stopwatch: Clock): TimeThreshold = TimeThreshold(stopwatch.getTimeInMs(), MILLISECONDS)
    }
}
