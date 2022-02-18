package com.aalzatea.courses.linkedinlearning.challenges.java.duplicates;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Duplicates {

    public static void main(String... args) {
        var animals = Arrays.asList("apple", "biscuit", "apple", "cat", "cat", "cat", "dog", "elephant", "fox", "goat", "elephant");
        var animalsDistinct = animals.stream().distinct().toList();
        var animalsSet1 = animals.stream().collect(Collectors.toSet());
        var animalsSet2 = Set.copyOf(animals);

        System.out.println("******Animals Distinct");
        animalsDistinct.forEach(System.out::println);

        System.out.println("******Animals Set 1");
        animalsSet1.forEach(System.out::println);

        System.out.println("******Animals Set 2");
        for (String animal : animalsSet2) {
            System.out.println(animal);
        }
    }
}
