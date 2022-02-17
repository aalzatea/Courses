package com.aalzatea.courses.linkedinlearning.challenges.java.students;

import java.util.Comparator;

public class Main {

    public static void main(String... args) {
        var students = Student.getStudents();
        var combos = students.stream().map(student -> student.name() + " : " + student.age()).toList();
        System.out.println("Combos: " + combos);

        System.out.println("The oldest student : " + students.stream().max(Comparator.comparingInt(Student::age)).get());

        var studentsWithLongNames = students.stream().filter(student -> student.name().length() > 5).toList();
        System.out.println("Long names: " + studentsWithLongNames);

        System.out.println("The student with the shortest name : " + students.stream().min(Comparator.comparingInt(student -> student.name().length())).get());

        //Add your solution here
        var shortName = students.stream().map(Student::name).map(String::length).min(Long::compare).get();
        var studentWithShortNames = students.stream().filter(student -> student.name().length() == shortName).toList();
        System.out.println("Short names: " + studentWithShortNames);
    }
}
