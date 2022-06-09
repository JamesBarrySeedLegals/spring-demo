package com.seedlegals.demo

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.Instant

fun main() {
    val context: ApplicationContext = AnnotationConfigApplicationContext(StopWatchConfiguration::class.java)

    val stopWatch = context.getBean(StopWatchSpring::class.java)

    println("StartTime: ${stopWatch.start()}")
    Thread.sleep(1000) // wait approx 1 second

    println("StopTime: ${stopWatch.stop()}")

    println("Difference: ${stopWatch.difference()}")
}

@Configuration
open class StopWatchConfiguration {

    @Bean
    open fun clock(): Clock = Clock.systemUTC()

    @Bean
    open fun stopWatchSpring(clock: Clock) = StopWatchSpring(clock)
}


class StopWatchSpring(
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