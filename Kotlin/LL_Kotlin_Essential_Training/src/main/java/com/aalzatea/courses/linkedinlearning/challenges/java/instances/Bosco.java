package com.aalzatea.courses.linkedinlearning.challenges.java.instances;

public class Bosco {

    private static int instanceCounter;

    public Bosco() {
        instanceCounter++;
    }

    public static void show() {
        System.out.printf("%d instances created.", instanceCounter);
    }
}
