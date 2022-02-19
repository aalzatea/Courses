package com.aalzatea.courses.linkedinlearning.challenges.java.functions;

import java.util.function.BiConsumer;

public final class Functions {

    private Functions() {}

    public static void replicate(Integer times, String message, BiConsumer<Integer, String> function) {
        function.accept(times, message);
    }
}
