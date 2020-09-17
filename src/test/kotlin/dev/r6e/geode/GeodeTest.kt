package dev.r6e.geode

import java.lang.Math.abs
import org.junit.*
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import dev.r6e.geode.*

class GeodeTest {
    @Test fun distance() {
        val origin = Pair(0.0, 0.0)
        val destination = Pair(2.0, 2.0)
        val expectedDistance = 314.5
        val epsilon = expectedDistance * 0.0005

        val result = Geode.distance(origin, destination)

        Assert.assertEquals(expectedDistance, result, epsilon)
    }

    @Test fun initialBearing() {
        val origin = Pair(0.0, 0.0)
        val destination = Pair(2.0, 2.0)
        val expectedBearing = 44.9825
        val epsilon = expectedBearing * 0.0005

        val result = Geode.initialBearing(origin, destination)

        Assert.assertEquals(expectedBearing, result, epsilon)
    }

    @Test fun finalBearing() {
        val origin = Pair(0.0, 0.0)
        val destination = Pair(2.0, 2.0)
        val expectedBearing = 45.0175
        val epsilon = expectedBearing * 0.0005

        val result = Geode.finalBearing(origin, destination)

        Assert.assertEquals(expectedBearing, result, epsilon)
    }

    @Test fun intermediatePoint() {
        val origin = Pair(0.0, 0.0)
        val destination = Pair(2.0, 2.0)
        val expectedPoint = Pair(1.5, 1.5)
        val percent = 0.75
        val epsilon = 0.00075

        val result = Geode.intermediatePoint(origin, destination, percent)

        Assert.assertEquals(expectedPoint.first, result.first, epsilon)
        Assert.assertEquals(expectedPoint.second, result.second, epsilon)
    }

    @Test fun midpoint() {
        val origin = Pair(0.0, 0.0)
        val destination = Pair(2.0, 2.0)
        val expectedPoint = Pair(1.0, 1.0)
        val epsilon = 0.0005

        val result = Geode.midpoint(origin, destination)

        Assert.assertEquals(expectedPoint.first, result.first, epsilon)
        Assert.assertEquals(expectedPoint.second, result.second, epsilon)
    }

    @Test fun destination() {
        val origin = Pair(0.0, 0.0)
        val bearing = 44.9825
        val distance = 314.5
        val expectedPoint = Pair(2.0, 2.0)
        val epsilon = 0.001

        val result = Geode.destination(origin, bearing, distance)

        Assert.assertEquals(expectedPoint.first, result.first, epsilon)
        Assert.assertEquals(expectedPoint.second, result.second, epsilon)
    }
}
