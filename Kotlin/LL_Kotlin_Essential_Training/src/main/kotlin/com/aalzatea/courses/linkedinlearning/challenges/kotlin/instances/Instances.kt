package com.aalzatea.courses.linkedinlearning.challenges.kotlin.instances

class Bosco {
    companion object {
        private var instanceCounter: Int = 0;

        fun show(): Unit {
            println("$instanceCounter instances created.")
        }
    }

    init {
        instanceCounter++
    }
}

fun nop() {
    Bosco()
}

fun main() {
    val b1 = Bosco()
    val b2 = Bosco()
    nop()
    val b3 = Bosco()

    Bosco.show()
}