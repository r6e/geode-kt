package dev.r6e.geode

import java.lang.Math.abs
import kotlin.test.Test
import kotlin.test.assertTrue
import dev.r6e.geode.*

class GeodeTest {
    @Test fun distance() {
        val lat1 = 0.0 // degrees
        val lon1 = 0.0
        val lat2 = 2.0
        val lon2 = 2.0
        val expectedDistance = 314.5 // km
        val epsilon = 0.0005

        val result = Geode.distance(lat1, lon1, lat2, lon2)

        assertTrue(abs(expectedDistance - result) < epsilon)
    }
}
