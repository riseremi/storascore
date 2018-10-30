package com.rovioli.runmaboi.util

import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.schedule

class Delayer(hours: Long) {
    private val timer  = Timer()
    private val millis = TimeUnit.HOURS.toMillis(hours)
    private val locked = AtomicBoolean(false)

    fun delay() {
        locked.set(true)
        timer.schedule(millis) {
            locked.set(false)
        }
    }

    fun isDelayed() = locked.get()
}