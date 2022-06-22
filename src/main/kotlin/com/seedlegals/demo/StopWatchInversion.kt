package com.seedlegals.demo

import java.time.Clock
import java.time.Instant

fun main() {
    val clock = Clock.systemUTC()
    val stopWatch = StopWatchInversion(clock)

    println("StartTime: ${stopWatch.start()}")
    Thread.sleep(1000) // wait approx 1 second

    println("StopTime: ${stopWatch.stop()}")

    println("Difference: ${stopWatch.difference()}")
}

class StopWatchInversion(
    private val clock: Clock // Inject a clock that has control
) {
    private var maybeStartTime: Instant? = null
    private var maybeEndTime: Instant? = null

    fun start(): Instant {
        val now = clock.instant() // StopWatch passing control to injected clock function

        maybeStartTime = now // Store the time

        return now // Return the time
    }

    fun stop(): Instant {
        val now = clock.instant() // StopWatch passing control to injected clock function

        maybeEndTime = now // Store the time

        return now // Return the time
    }

    fun difference(): Long? =
        maybeStartTime?.let { startTime -> // if there is a value for maybeStartTime
            maybeEndTime?.let { endTime -> // if there is a value for maybeEndTime
                endTime.toEpochMilli() - startTime.toEpochMilli() // calculate difference
            }
        }
}
