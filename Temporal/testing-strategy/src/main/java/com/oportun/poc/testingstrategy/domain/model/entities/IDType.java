package com.oportun.poc.testingstrategy.domain.model.entities;

public enum IDType {
    IDENTIFICATION("Identification Card"),
    SOCIAL_SECURITY("Social Security"),
    DRIVER_LICENSE("Driver License");

    private final String name;

    IDType(String name) {
        this.name = name;
    }

    /*@Override
    public String toString() {
        return this.name;
    }*/
}
