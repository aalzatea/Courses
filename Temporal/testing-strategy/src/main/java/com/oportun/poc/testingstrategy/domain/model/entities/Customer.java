package com.oportun.poc.testingstrategy.domain.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class Customer {

    private final IDType idType;

    private final String identification;

    private final String name;

    private final Date birthDate;

    private final Boolean hasDriverLicense;

    public Integer getAge() {
        var localDateNow = LocalDate.now();
        var localDateBirthDate = LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());

        return Period.between(localDateBirthDate, localDateNow)
                .getYears();
    }

    public Boolean getHasDriverLicense() {
        if (IDType.DRIVER_LICENSE.equals(idType)) {
            return true;
        }

        return hasDriverLicense;
    }
}