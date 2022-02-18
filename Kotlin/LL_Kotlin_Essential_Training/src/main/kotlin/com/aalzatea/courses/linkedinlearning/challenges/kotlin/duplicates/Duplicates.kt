package com.aalzatea.courses.linkedinlearning.challenges.kotlin.duplicates

fun main() {
    val animals = listOf("apple", "biscuit", "apple", "cat", "cat", "cat", "dog", "elephant", "fox", "goat", "elephant")
    val animalsSet = animals.toSet()

    for (animal in animalsSet) {
        println("Animal: $animal")
    }
}