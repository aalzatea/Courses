package com.aalzatea.courses.linkedinlearning.challenges.java.comparator;

public final class Bigger {

    public static <T extends Comparable<T>> T getBigger(T param1, T param2) {
        var result = param1.compareTo(param2);
        return result > 0 ? param1 : param2;
    }
}
