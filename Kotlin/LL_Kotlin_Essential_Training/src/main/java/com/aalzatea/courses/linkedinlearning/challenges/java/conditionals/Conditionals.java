package com.aalzatea.courses.linkedinlearning.challenges.java.conditionals;

public class Conditionals {

    public static void main(String... args) {
        var cardPoints = 7_000;

        String cardLevel;
        if (cardPoints >= 0 && cardPoints < 1000) {
            cardLevel = "pearl";
        } else if (cardPoints >= 1000 && cardPoints < 5_000) {
            cardLevel = "silver";
        } else if (cardPoints >= 5_000 && cardPoints < 10_000) {
            cardLevel = "gold";
        } else {
            cardLevel = "platinum";
        }

        var plural = cardPoints > 1 || cardPoints == 0 ? "s" : "";
        System.out.printf("You have %d point%s and are at the %s level.", cardPoints, plural, cardLevel);
    }
}
