package dev.r6e.geode
import java.lang.Math.*

class Geode {
  companion object {
    val RADIUS_OF_EARTH = 6371.0088

    fun distance(origin: Pair<Double,Double>, destination: Pair<Double,Double>): Double {
      val φ1 = toRadians(origin.first)
      val λ1 = toRadians(origin.second)
      val φ2 = toRadians(destination.first)
      val λ2 = toRadians(destination.second)
      val Δφ = φ2 - φ1
      val Δλ = λ2 - λ1

      val a = pow(sin(Δφ / 2.0), 2.0) + cos(φ1) * cos(φ2) * pow(sin(Δλ / 2.0), 2.0)
      val c = 2.0 * atan2(sqrt(a), sqrt(1 - a))

      return RADIUS_OF_EARTH * c
    }

    fun initialBearing(origin: Pair<Double,Double>, destination: Pair<Double,Double>): Double {
      val φ1 = toRadians(origin.first)
      val λ1 = toRadians(origin.second)
      val φ2 = toRadians(destination.first)
      val λ2 = toRadians(destination.second)
      val Δλ = λ2 - λ1

      val x = cos(φ1) * sin(φ2) - sin(φ1) * cos(φ2) * cos(Δλ)
      val y = sin(Δλ) * cos(φ2)
      val θ = atan2(y, x)

      return (toDegrees(θ) + 360.0) % 360.0 // Normalize degrees
    }

    fun finalBearing(origin: Pair<Double,Double>, destination: Pair<Double,Double>): Double {
      val θ = initialBearing(destination, origin)
      return (θ + 180.0) % 360.0
    }

    fun intermediatePoint(origin: Pair<Double,Double>, destination: Pair<Double,Double>, percent: Double): Pair<Double,Double> {
      val φ1 = toRadians(origin.first)
      val λ1 = toRadians(origin.second)
      val φ2 = toRadians(destination.first)
      val λ2 = toRadians(destination.second)
      val δ = distance(origin, destination) / RADIUS_OF_EARTH

      val a = sin((1 - percent) * δ) / sin(δ)
      val b = sin(percent * δ) / sin(δ)
      val x = a * cos(φ1) * cos(λ1) + b * cos(φ2) * cos(λ2)
      val y = a * cos(φ1) * sin(λ1) + b * cos(φ2) * sin(λ2)
      val z = a * sin(φ1) + b * sin(φ2)

      val φi = atan2(z, sqrt(pow(x, 2.0) + pow(y, 2.0)))
      val λi = atan2(y, x)

      return Pair(toDegrees(φi), toDegrees(λi))
    }

    fun midpoint(origin: Pair<Double,Double>, destination: Pair<Double,Double>): Pair<Double,Double> {
      return intermediatePoint(origin, destination, 0.5)
    }

    fun destination(origin: Pair<Double,Double>, bearing: Double, distance: Double): Pair<Double,Double> {
      val φ1 = toRadians(origin.first)
      val λ1 = toRadians(origin.second)
      val θ = toRadians(bearing)
      val δ = distance / RADIUS_OF_EARTH

      val φ2 = asin(sin(φ1) * cos(δ) + cos(φ1) * sin(δ) * cos(θ))
      val λ2 = λ1 + atan2(sin(θ) * sin(δ) * cos(φ1), cos(δ) - sin(φ1) * sin(φ2))

      val normalizedΛ2 = (toDegrees(λ2) + 540) % 360 - 180

      return Pair(toDegrees(φ2), normalizedΛ2)
    }
  }
}
