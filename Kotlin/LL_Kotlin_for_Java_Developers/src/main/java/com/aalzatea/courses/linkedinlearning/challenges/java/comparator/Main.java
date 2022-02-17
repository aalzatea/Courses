package com.aalzatea.courses.linkedinlearning.challenges.java.comparator;

public class Main {

    public static void main(String... args) {
        System.out.println("******getSmaller");

        //Please add the getSmaller function above the main function
        var minInt = Smaller.getSmaller(42, 99);
        var minDouble = Smaller.getSmaller(9.807, 3.14159);
        var minString = Smaller.getSmaller("kitten", "kittens");

        System.out.printf("The min Int = %d%n", minInt);
        System.out.printf("The min Double = %f%n", minDouble);
        System.out.printf("The min String = %s%n", minString);

        System.out.println("******getBigger");

        var maxInt = Bigger.getBigger(42, 99);
        var maxDouble = Bigger.getBigger(9.807, 3.14159);
        var maxString = Bigger.getBigger("kitten", "kittens");

        System.out.printf("The max Int = %d%n", maxInt);
        System.out.printf("The max Double = %f%n", maxDouble);
        System.out.printf("The max String = %s%n", maxString);
    }
}
