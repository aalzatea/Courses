package com.aalzatea.courses.linkedinlearning.challenges.java.functions;

import java.util.function.BiConsumer;

public class Main {

    public static void main(String... args) {
        BiConsumer<Integer, String> func = (times, message) -> {
            for (int i = 0; i < times; i++)
                System.out.println(message);
        };

        Functions.replicate(3, "* Be Cool.", func);
        Functions.replicate(5, "-> Test", func);
    }
}
