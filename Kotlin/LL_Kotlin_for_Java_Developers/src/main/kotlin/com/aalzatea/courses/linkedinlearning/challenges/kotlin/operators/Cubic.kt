package com.aalzatea.courses.linkedinlearning.challenges.kotlin.operators

data class Cubic constructor(val x: Int, val y: Int, val z: Int) {

    init {
        if (x <= 0 || y <= 0 || z <= 0) throw IllegalArgumentException("Value x, y, or z must not be less or equals than zero.")
    }

    operator fun plus(other: Cubic): Cubic {
        return Cubic(x + other.x, y + other.y, z + other.z)
    }

    operator fun minus(other: Cubic): Cubic {
        return Cubic(x - other.x, y - other.y, z - other.z)
    }

    override fun toString(): String {
        return "Cubic(x=$x, y=$y, z=$z)"
    }
}