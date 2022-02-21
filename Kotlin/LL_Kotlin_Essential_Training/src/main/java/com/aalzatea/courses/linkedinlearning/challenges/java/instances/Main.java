package com.aalzatea.courses.linkedinlearning.challenges.java.instances;

public class Main {

    private static void nop() {
        new Bosco();
    }

    public static void main(String... args) {
        var b1 = new Bosco();
        var b2 = new Bosco();
        nop();
        var b3 = new Bosco();

        Bosco.show();
    }
}
