package com.oportun.poc.testingstrategy.domain.model.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    @Test
    @DisplayName("Test age calculation from birth date and current date")
    void testGetAge() {
        var birthDate = getDateOfBirth(1990, Month.OCTOBER, 7);
        var customer = Customer.builder()
                .birthDate(birthDate)
                .build();
        var ageExpected = Period.between(LocalDate.of(1990, Month.OCTOBER, 7), LocalDate.now())
                .getYears();

        var result = customer.getAge();

        assertNotNull(result);
        assertEquals(ageExpected, result);
    }

    @Test
    @DisplayName("Test if a customer has driver license with ID type Driver License")
    void testGetHasDriverLicense() {
        var customer = Customer.builder()
                .idType(IDType.DRIVER_LICENSE)
                .build();

        var result = customer.getHasDriverLicense();

        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a customer has driver license with different ID type and set this in true")
    void testGetHasDriverLicenseWithDifferentIDTypeAndSetInTrue() {
        var customer = Customer.builder()
                .idType(IDType.SOCIAL_SECURITY)
                .hasDriverLicense(Boolean.TRUE)
                .build();

        var result = customer.getHasDriverLicense();

        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a customer doesn't have driver license with different ID type and set this in false")
    void testGetHasDriverLicenseWithDifferentIDTypeAndSetInFalse() {
        var customer = Customer.builder()
                .idType(IDType.IDENTIFICATION)
                .hasDriverLicense(Boolean.FALSE)
                .build();

        var result = customer.getHasDriverLicense();

        assertFalse(result);
    }

    private Date getDateOfBirth(int year, Month month, int day) {
        return Date.from(
            LocalDate.of(year, month, day)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
        );
    }
}