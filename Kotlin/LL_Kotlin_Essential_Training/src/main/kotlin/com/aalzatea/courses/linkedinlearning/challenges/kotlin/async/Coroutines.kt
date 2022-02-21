package com.aalzatea.courses.linkedinlearning.challenges.kotlin.async

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.File

fun main() {
    print("Enter a file path: ")
    val filePath = readLine() ?: ""

    readContentFileAsync(filePath)
}

fun readContentFileAsync(filePath: String) = runBlocking {
    val asyncReadProcess = async {
        File(filePath).readText()
    }

    val contentFile = asyncReadProcess.await()
    println(contentFile)
}