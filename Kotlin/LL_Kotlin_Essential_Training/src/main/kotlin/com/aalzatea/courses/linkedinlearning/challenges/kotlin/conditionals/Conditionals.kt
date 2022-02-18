package com.aalzatea.courses.linkedinlearning.challenges.kotlin.conditionals

fun main() {
    val cardPoints = 7_000

    //Replace this if with a when
    /*val cardLevel: String = if (cardPoints >= 0 && cardPoints < 1000) {
        "pearl"
    } else if (cardPoints >= 1000 && cardPoints < 5_000) {
        "silver"
    } else if (cardPoints >= 5_000 && cardPoints < 10_000) {
        "gold"
    } else {
        "platinum"
    }*/
    val cardLevel: String = when(cardPoints) {
        in 0..999 -> "pearl"
        in 1000..4999 -> "silver"
        in 5000..10000 -> "gold"
        else -> "platinum"
    }

    val plural = if(cardPoints > 1 || cardPoints == 0) "s" else ""
    println("You have $cardPoints point$plural and are at the $cardLevel level.")
}