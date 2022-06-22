package com.seedlegals.demo

import java.time.Clock
import java.time.Instant

fun main() {
    val stopWatch = StopWatch()

    println("StartTime: ${stopWatch.start()}")
    Thread.sleep(1000) // wait approx 1 second

    println("StopTime: ${stopWatch.stop()}")

    println("Difference: ${stopWatch.difference()}")
}

class StopWatch {
    private val clock: Clock = Clock.systemUTC() // construct a clock internally as StopWatch has control

    private var maybeStartTime: Instant? = null
    private var maybeEndTime: Instant? = null

    fun start(): Instant {
        val now = clock.instant() // StopWatch class has control over time here

        maybeStartTime = now // Store the time

        return now // Return the time
    }

    fun stop(): Instant {
        val now = clock.instant() // StopWatch class has control over time here

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
