package com.aalzatea.courses.linkedinlearning.challenges.java.students;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record Student(String name, Short age) {

    public Student {
        Objects.requireNonNull(name);
        Objects.requireNonNull(age);

        if (name.isBlank() || age <= 0)
            throw new IllegalArgumentException("Value name or age must be filled.");
    }

    public static List<Student> getStudents() {
        return Arrays.asList(
                new Student("Ginger", (short) 19),
                new Student("Michael", (short) 23),
                new Student("Maria", (short) 20),
                new Student("Joe", (short) 39),
                new Student("Bob", (short) 16)
        );
    }

    @Override
    public String toString() {
        return String.format("Student(name=%s, age=%d)", name, age);
    }
}
