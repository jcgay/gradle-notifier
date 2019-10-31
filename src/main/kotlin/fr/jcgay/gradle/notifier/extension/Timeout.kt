package fr.jcgay.gradle.notifier.extension

import java.util.concurrent.TimeUnit


class Timeout {
    var time: Long? = null
    var unit: TimeUnit? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Timeout

        if (time != other.time) return false
        if (unit != other.unit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = time?.hashCode() ?: 0
        result = 31 * result + (unit?.hashCode() ?: 0)
        return result
    }


}
