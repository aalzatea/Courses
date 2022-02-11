package com.aalzatea.courses.linkedinlearning.challenges.fizzbuzz;

import java.util.stream.IntStream;

public class FizzBuzzApp {

    public static void main(String... args) {
        System.out.println("Fizz Buzz");

        // Create a loop to display the numbers 1 to 105
        // after the number
        //  print "fizz" if the number is divisible by 3
        //  print "buzz" if the number is divisible by 5
        //  print "fizz buzz" if the number is divisible by 3 and 5

        // Add your solution here
        IntStream.rangeClosed(1, 105)
            .forEach(number -> {
                    if (number % 15 == 0) {
                        System.out.printf("Number %d is fizz buzz%n", number);
                    } else if (number % 3 == 0) {
                        System.out.printf("Number %d is fizz%n", number);
                    } else if (number % 5 == 0) {
                        System.out.printf("Number %d is buzz%n", number);
                    } else {
                        System.out.printf("Number %d is nothing", number);
                    }
            });
    }
}