package com.aalzatea.courses.linkedinlearning.challenges.java.files;

import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;

public class Main {

    public static void main(String... args) {
        /*System.out.print("Enter file path: ");
        var scanner = new Scanner(System.in);
        var filePath = scanner.nextLine();
        var contentFile = FileReader.readContentFileUsingFilesNIO(filePath);
        System.out.printf("Content file: %s%n", contentFile);*/

        var filePath1 = "files/sales.txt";
        var contentFile1 = FileReader.readContentFileFromResources(filePath1)
                .stream()
                .filter(value -> Pattern.compile("-?\\d+(\\.\\d+)?").matcher(value).matches())
                .toList();
        System.out.printf("Content file 1: %s%n", contentFile1);

        var filePath2 = "./files/sales.txt";
        var contentFile2 = FileReader.readContentFileUsingFilesNIO(filePath2);
        var sales = new LinkedList<>();
        for (String value : contentFile2) {
            var sale = toDoubleOrNull(value);
            if (Objects.nonNull(sale)) {
                sales.add(sale);
            }
        }

        System.out.printf("Content file 2: %s%n", sales);

        var filePath3 = args[0];
        var contentFile3 = FileReader.readContentFileUsingFilesNIO(filePath3);
        System.out.printf("Content file 3: %s%n", contentFile3);
    }

    private static Double toDoubleOrNull(String value) {
        try {
            return Double.parseDouble(value);
        } catch(NumberFormatException nfe) {
            return null;
        }
    }
}
