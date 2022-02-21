package com.aalzatea.courses.linkedinlearning.challenges.java.async;

import java.util.Optional;
import java.util.Scanner;

public class Async {

    public static void main(String... args) {
        var scanner = new Scanner(System.in);

        System.out.print("Enter a file path: ");
        var filePath = Optional.ofNullable(scanner.next()).orElse("");

        var contentFile = FileReader.readContentFileAsync(filePath);
        System.out.println(contentFile);
    }
}
