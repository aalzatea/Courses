package com.aalzatea.courses.linkedinlearning.challenges.kotlin.files

import java.io.File

class FileReader {

    companion object {
        fun readContentFileFromResources(filePath: String): List<String>? =
            FileReader::class.java
                .getResourceAsStream(filePath)
                ?.bufferedReader()
                ?.readLines()

        fun readContentFileUsingUseLines(filePath: String) =
            File(filePath).useLines { it.toList() }

        fun readContentFileUsingReadLines(filePath: String) =
            File(filePath).readLines();
    }
}

fun main(args: Array<String>) {
    /*print("Enter file path: ")
    val filePath: String = readLine() ?: ""
    val fileContent = FilesReader.readFileContentUsingReadLines(filePath)
    println("Content file: $fileContent")*/

    val filePath1 = "/files/sales.txt"
    val fileContent1 = FileReader.readContentFileFromResources(filePath1)
    println("Content file 1: ${fileContent1!!.filter { it.toDoubleOrNull() != null }}")

    val filePath2 = "./files/sales.txt"
    val fileContent2 = FileReader.readContentFileUsingUseLines(filePath2)
    val sales = mutableListOf<Double>()
    for (value in fileContent2) {
        val sale = value.toDoubleOrNull()
        if (sale != null) {
            sales.add(sale)
        }
    }
    println("Content file 2: $sales")

    val filePath3 = args[0]
    val fileContent3 = FileReader.readContentFileUsingReadLines(filePath3)
    println("Content file 3: $fileContent3")
}