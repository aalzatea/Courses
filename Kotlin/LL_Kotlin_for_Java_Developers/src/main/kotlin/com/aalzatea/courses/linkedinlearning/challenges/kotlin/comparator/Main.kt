package com.aalzatea.courses.linkedinlearning.challenges.kotlin.comparator

fun main() {
    println("******getSmaller")

    //Please add the getSmaller function above the main function
    val minInt: Int = Smaller.getSmaller(42, 99)
    val minDouble = Smaller.getSmaller<Double>(9.807, 3.14159)
    val minString: String = Smaller.getSmaller("kitten", "kittens")

    println("The min Int = $minInt")
    println("The min Double = $minDouble")
    println("The min String = $minString")

    println("******getBigger")

    val maxInt: Int = Bigger.getBigger(42, 99)
    val maxDouble = Bigger.getBigger<Double>(9.807, 3.14159)
    val maxString: String = Bigger.getBigger("kitten", "kittens")

    println("The max Int = $maxInt")
    println("The max Double = $maxDouble")
    println("The max String = $maxString")
}