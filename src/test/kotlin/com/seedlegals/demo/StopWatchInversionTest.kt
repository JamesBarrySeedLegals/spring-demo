package com.seedlegals.demo

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant

internal class StopWatchInversionTest {

    @Test
    fun testStart() {
        val startTimeInstant = Instant.MIN
        val stopTimeInstant = startTimeInstant.plusSeconds(1)

        val mockClock = mockk<Clock>().apply {
            every { instant() } returnsMany listOf(startTimeInstant, stopTimeInstant)
        }

        val unit = StopWatchInversion(mockClock)

        val result = unit.start()

        assertEquals(startTimeInstant, result)
    }

    @Test
    fun testEnd() {
        val startTimeInstant = Instant.MIN
        val stopTimeInstant = startTimeInstant.plusSeconds(1)

        val mockClock = mockk<Clock>().apply {
            every { instant() } returnsMany listOf(startTimeInstant, stopTimeInstant)
        }

        val unit = StopWatchInversion(mockClock)

        unit.start()

        val result = unit.stop()

        assertEquals(stopTimeInstant, result)
    }

    @Test
    fun testDifference() {
        val startTimeInstant = Instant.now()
        val stopTimeInstant = startTimeInstant.plusSeconds(1)

        val mockClock = mockk<Clock>().apply {
            every { instant() } returnsMany listOf(startTimeInstant, stopTimeInstant)
        }

        val unit = StopWatchInversion(mockClock)

        unit.start()
        unit.stop()

        val result = unit.difference()

        assertEquals(1000L, result)
    }
}